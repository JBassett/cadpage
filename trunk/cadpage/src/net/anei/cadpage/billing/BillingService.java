/*
 * Copyright (C) 2010 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package net.anei.cadpage.billing;

import com.android.vending.billing.IMarketBillingService;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

import net.anei.cadpage.Log;
import net.anei.cadpage.billing.Consts.ResponseCode;
import net.anei.cadpage.billing.Security.VerifiedPurchase;


/**
 * This class sends messages to Android Market on behalf of the application by
 * connecting (binding) to the MarketBillingService. The application
 * creates an instance of this class and invokes billing requests through this service.
 *
 * The {@link BillingReceiver} class starts this service to process commands
 * that it receives from Android Market.
 */
public class BillingService extends Service implements ServiceConnection {

    /** The service connection to the remote MarketBillingService. */
    private static IMarketBillingService mService;

    /**
     * The list of requests that are pending while we are waiting for the
     * connection to the MarketBillingService to be established.
     */
    private static LinkedList<BillingRequest> mPendingRequests = new LinkedList<BillingRequest>();

    /**
     * The list of requests that we have sent to Android Market but for which we have
     * not yet received a response code. The HashMap is indexed by the
     * request Id that each request receives when it executes.
     */
    private static HashMap<Long, BillingRequest> mSentRequests =
        new HashMap<Long, BillingRequest>();
    
    private static Method mStartIntentSender = null;
    static {
        try {
            mStartIntentSender = Activity.class.getMethod("startIntentSender",
                new Class<?>[]{IntentSender.class, Intent.class, int.class, int.class, int.class});
        } catch (SecurityException e) {
            mStartIntentSender = null;
        } catch (NoSuchMethodException e) {
            mStartIntentSender = null;
        }
   }

    /**
     * The base class for all requests that use the MarketBillingService.
     * Each derived class overrides the run() method to call the appropriate
     * service interface.  If we are already connected to the MarketBillingService,
     * then we call the run() method directly. Otherwise, we bind
     * to the service and save the request on a queue to be run later when
     * the service is connected.
     */
    private abstract class BillingRequest {
        private final int mStartId;
        protected long mRequestId;

        public BillingRequest(int startId) {
            mStartId = startId;
        }

        public int getStartId() {
            return mStartId;
        }

        /**
         * Run the request, starting the connection if necessary.
         * @return true if the request was executed or queued; false if there
         * was an error starting the connection
         */
        public boolean runRequest() {
            if (runIfConnected()) {
                return true;
            }

            if (bindToMarketBillingService()) {
                // Add a pending request to run when the service is connected.
                mPendingRequests.add(this);
                return true;
            }
            return false;
        }

        /**
         * Try running the request directly if the service is already connected.
         * @return true if the request ran successfully; false if the service
         * is not connected or there was an error when trying to use it
         */
        public boolean runIfConnected() {
            if (Log.DEBUG) {
                Log.v(getClass().getSimpleName());
            }
            if (mService != null) {
                try {
                    mRequestId = run();
                    if (Log.DEBUG) {
                        Log.v("request id: " + mRequestId);
                    }
                    if (mRequestId >= 0) {
                        mSentRequests.put(mRequestId, this);
                    }
                    return true;
                } catch (RemoteException e) {
                    onRemoteException(e);
                }
            }
            return false;
        }

        /**
         * Called when a remote exception occurs while trying to execute the
         * {@link #run()} method.  The derived class can override this to
         * execute exception-handling code.
         * @param e the exception
         */
        protected void onRemoteException(RemoteException e) {
            Log.v("remote billing service crashed");
            mService = null;
        }

        /**
         * The derived class must implement this method.
         * @throws RemoteException
         */
        abstract protected long run() throws RemoteException;

        /**
         * This is called when Android Market sends a response code for this
         * request.
         * @param responseCode the response code
         */
        protected void responseCodeReceived(ResponseCode responseCode) {
        }

        protected Bundle makeRequestBundle(String method) {
            Bundle request = new Bundle();
            request.putString(Consts.BILLING_REQUEST_METHOD, method);
            request.putInt(Consts.BILLING_REQUEST_API_VERSION, 1);
            request.putString(Consts.BILLING_REQUEST_PACKAGE_NAME, getPackageName());
            return request;
        }

        protected void logResponseCode(String method, Bundle response) {
            ResponseCode responseCode = ResponseCode.valueOf(
                    response.getInt(Consts.BILLING_RESPONSE_RESPONSE_CODE));
            if (Log.DEBUG) {
                Log.e(method + " received " + responseCode.toString());
            }
        }
    }

    /**
     * Wrapper class that checks if in-app billing is supported.
     */
    private class CheckBillingSupported extends BillingRequest {
        public CheckBillingSupported() {
            // This object is never created as a side effect of starting this
            // service so we pass -1 as the startId to indicate that we should
            // not stop this service after executing this request.
            super(-1);
        }

        @Override
        protected long run() throws RemoteException {
          boolean billingSupported = false;
            Bundle request = makeRequestBundle("CHECK_BILLING_SUPPORTED");
            Bundle response = mService.sendBillingRequest(request);
            if (response != null) {
              int responseCode = response.getInt(Consts.BILLING_RESPONSE_RESPONSE_CODE);
              if (Log.DEBUG) {
                  Log.v("CheckBillingSupported response code: " +
                          ResponseCode.valueOf(responseCode));
              }
              if (responseCode == ResponseCode.RESULT_OK.ordinal()) billingSupported = true;
            }
            checkBillingSupportedResponse(billingSupported);
            return Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
        }
    }

    /**
     * Checks if in-app billing is supported.
     * @return true if supported; false otherwise
     */
    public boolean checkBillingSupported() {
        return new CheckBillingSupported().runRequest();
    }

    /**
     * Notifies the application of the availability of the MarketBillingService.
     * This method is called in response to the application calling
     * {@link BillingService#checkBillingSupported()}.
     * @param supported true if in-app billing is supported.
     */
    protected void checkBillingSupportedResponse(boolean billingSupported) {}

    /**
     * Wrapper class that requests a purchase.
     */
    class RequestPurchase extends BillingRequest {
        public final Activity mActivity;
        public final String mProductId;
        public final String mDeveloperPayload;

        public RequestPurchase(Activity activity, String itemId) {
            this(activity, itemId, null);
        }

        public RequestPurchase(Activity activity, String itemId, String developerPayload) {
            // This object is never created as a side effect of starting this
            // service so we pass -1 as the startId to indicate that we should
            // not stop this service after executing this request.
            super(-1);
            mActivity = activity;
            mProductId = itemId;
            mDeveloperPayload = developerPayload;
        }

        @Override
        protected long run() throws RemoteException {
            Bundle request = makeRequestBundle("REQUEST_PURCHASE");
            request.putString(Consts.BILLING_REQUEST_ITEM_ID, mProductId);
            // Note that the developer payload is optional.
            if (mDeveloperPayload != null) {
                request.putString(Consts.BILLING_REQUEST_DEVELOPER_PAYLOAD, mDeveloperPayload);
            }
            Bundle response = mService.sendBillingRequest(request);
            PendingIntent pendingIntent
                    = response.getParcelable(Consts.BILLING_RESPONSE_PURCHASE_INTENT);
            if (pendingIntent == null) {
                Log.e("Error with requestPurchase");
                return Consts.BILLING_RESPONSE_INVALID_REQUEST_ID;
            }

            Intent intent = new Intent();
            buyPageIntentResponse(pendingIntent, intent);
            return response.getLong(Consts.BILLING_RESPONSE_REQUEST_ID,
                    Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
        }

        private void buyPageIntentResponse(PendingIntent pendingIntent, Intent intent) {
            if (mStartIntentSender != null) {
                // This is on Android 2.0 and beyond.  The in-app buy page activity
                // must be on the activity stack of the application.
                try {
                    // This implements the method call:
                    // mActivity.startIntentSender(pendingIntent.getIntentSender(),
                    //     intent, 0, 0, 0);
                    mStartIntentSender.invoke(mActivity, new Object[]{
                        pendingIntent.getIntentSender(),
                        intent,
                        0,
                        0,
                        0
                    });
                } catch (Exception e) {
                    Log.e(e);
                }
            } else {
                // This is on Android version 1.6. The in-app buy page activity must be on its
                // own separate activity stack instead of on the activity stack of
                // the application.
                try {
                    pendingIntent.send(mActivity, 0 /* code */, intent);
                } catch (CanceledException e) {
                    Log.e(e);
                }
            }
        }

        @Override
        protected void responseCodeReceived(ResponseCode responseCode) {
            requestPurchaseResponse(responseCode);
        }
    }

    /**
     * Requests that the given item be offered to the user for purchase. When
     * the purchase succeeds (or is canceled) the {@link BillingReceiver}
     * receives an intent with the action {@link Consts#ACTION_NOTIFY}.
     * Returns false if there was an error trying to connect to Android Market.
     * @param productId an identifier for the item being offered for purchase
     * @param developerPayload a payload that is associated with a given
     * purchase, if null, no payload is sent
     * @return false if there was an error connecting to Android Market
     */
    public boolean requestPurchase(Activity activity, String productId, String developerPayload) {
        return new RequestPurchase(activity, productId, developerPayload).runRequest();
    }
    
    /**
     * This is called when we receive a response code from Market for a
     * RequestPurchase request that we made.  This is NOT used for any
     * purchase state changes.  All purchase state changes are received in
     * {@link #onPurchaseStateChange(PurchaseState, String, int, long)}.
     * This is used for reporting various errors, or if the user backed out
     * and didn't purchase the item.  The possible response codes are:
     *   RESULT_OK means that the order was sent successfully to the server.
     *       The onPurchaseStateChange() will be invoked later (with a
     *       purchase state of PURCHASED or CANCELED) when the order is
     *       charged or canceled.  This response code can also happen if an
     *       order for a Market-managed item was already sent to the server.
     *   RESULT_USER_CANCELED means that the user didn't buy the item.
     *   RESULT_SERVICE_UNAVAILABLE means that we couldn't connect to the
     *       Android Market server (for example if the data connection is down).
     *   RESULT_BILLING_UNAVAILABLE means that in-app billing is not
     *       supported yet.
     *   RESULT_ITEM_UNAVAILABLE means that the item this app offered for
     *       sale does not exist (or is not published) in the server-side
     *       catalog.
     *   RESULT_ERROR is used for any other errors (such as a server error).
     */
    protected void requestPurchaseResponse(ResponseCode responseCode) {}

    /**
     * Wrapper class that sends a GET_PURCHASE_INFORMATION message to the server.
     */
    private class GetPurchaseInformation extends BillingRequest {
        long mNonce;
        final String[] mNotifyIds;

        public GetPurchaseInformation(int startId, String[] notifyIds) {
            super(startId);
            mNotifyIds = notifyIds;
        }

        @Override
        protected long run() throws RemoteException {
            mNonce = Security.generateNonce();

            Bundle request = makeRequestBundle("GET_PURCHASE_INFORMATION");
            request.putLong(Consts.BILLING_REQUEST_NONCE, mNonce);
            request.putStringArray(Consts.BILLING_REQUEST_NOTIFY_IDS, mNotifyIds);
            Bundle response = mService.sendBillingRequest(request);
            logResponseCode("getPurchaseInformation", response);
            return response.getLong(Consts.BILLING_RESPONSE_REQUEST_ID,
                    Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
        }

        @Override
        protected void onRemoteException(RemoteException e) {
            super.onRemoteException(e);
            Security.removeNonce(mNonce);
        }
    }

    /**
     * Gets the purchase information. This message includes a list of
     * notification IDs sent to us by Android Market, which we include in
     * our request. The server responds with the purchase information,
     * encoded as a JSON string, and sends that to the {@link BillingReceiver}
     * in an intent with the action {@link Consts#ACTION_PURCHASE_STATE_CHANGED}.
     * Returns false if there was an error trying to connect to the MarketBillingService.
     *
     * @param startId an identifier for the invocation instance of this service
     * @param notifyIds a list of opaque identifiers associated with purchase
     * state changes
     * @return false if there was an error connecting to Android Market
     */
    private boolean getPurchaseInformation(int startId, String[] notifyIds) {
        return new GetPurchaseInformation(startId, notifyIds).runRequest();
    }

    /**
     * Wrapper class that confirms a list of notifications to the server.
     */
    class ConfirmNotifications extends BillingRequest {
        final String[] mNotifyIds;

        public ConfirmNotifications(int startId, String[] notifyIds) {
            super(startId);
            mNotifyIds = notifyIds;
        }

        @Override
        protected long run() throws RemoteException {
            Bundle request = makeRequestBundle("CONFIRM_NOTIFICATIONS");
            request.putStringArray(Consts.BILLING_REQUEST_NOTIFY_IDS, mNotifyIds);
            Bundle response = mService.sendBillingRequest(request);
            logResponseCode("confirmNotifications", response);
            return response.getLong(Consts.BILLING_RESPONSE_REQUEST_ID,
                    Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
        }
    }

    /**
     * Wrapper class that sends a RESTORE_TRANSACTIONS message to the server.
     */
    class RestoreTransactions extends BillingRequest {
        long mNonce;

        public RestoreTransactions() {
            // This object is never created as a side effect of starting this
            // service so we pass -1 as the startId to indicate that we should
            // not stop this service after executing this request.
            super(-1);
        }

        @Override
        protected long run() throws RemoteException {
            mNonce = Security.generateNonce();

            Bundle request = makeRequestBundle("RESTORE_TRANSACTIONS");
            request.putLong(Consts.BILLING_REQUEST_NONCE, mNonce);
            Bundle response = mService.sendBillingRequest(request);
            logResponseCode("restoreTransactions", response);
            return response.getLong(Consts.BILLING_RESPONSE_REQUEST_ID,
                    Consts.BILLING_RESPONSE_INVALID_REQUEST_ID);
        }

        @Override
        protected void onRemoteException(RemoteException e) {
            super.onRemoteException(e);
            Security.removeNonce(mNonce);
        }

        @Override
        protected void responseCodeReceived(ResponseCode responseCode) {
            ResponseHandler.responseCodeReceived(BillingService.this, this, responseCode);
        }
    }

    public BillingService() {
        super();
    }

    public void setContext(Context context) {
        attachBaseContext(context);
    }

    /**
     * We don't support binding to this service, only starting the service.
     */
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    /**
     * The {@link BillingReceiver} sends messages to this service using intents.
     * Each intent has an action and some extra arguments specific to that action.
     * @param intent the intent containing one of the supported actions
     * @param startId an identifier for the invocation instance of this service
     */
    @Override
    public void onStart(Intent intent, int startId) {
        String action = intent.getAction();
        if (Log.DEBUG) {
            Log.i("handleCommand() action: " + action);
        }
        if (Consts.ACTION_CONFIRM_NOTIFICATION.equals(action)) {
            String[] notifyIds = intent.getStringArrayExtra(Consts.NOTIFICATION_ID);
            confirmNotifications(startId, notifyIds);
        } else if (Consts.ACTION_GET_PURCHASE_INFORMATION.equals(action)) {
            String notifyId = intent.getStringExtra(Consts.NOTIFICATION_ID);
            getPurchaseInformation(startId, new String[] { notifyId });
        } else if (Consts.ACTION_PURCHASE_STATE_CHANGED.equals(action)) {
            String signedData = intent.getStringExtra(Consts.INAPP_SIGNED_DATA);
            String signature = intent.getStringExtra(Consts.INAPP_SIGNATURE);
            purchaseStateChanged(startId, signedData, signature);
        } else if (Consts.ACTION_RESPONSE_CODE.equals(action)) {
            long requestId = intent.getLongExtra(Consts.INAPP_REQUEST_ID, -1);
            int responseCodeIndex = intent.getIntExtra(Consts.INAPP_RESPONSE_CODE,
                    ResponseCode.RESULT_ERROR.ordinal());
            ResponseCode responseCode = ResponseCode.valueOf(responseCodeIndex);
            checkResponseCode(requestId, responseCode);
        }
    }

    /**
     * Binds to the MarketBillingService and returns true if the bind
     * succeeded.
     * @return true if the bind succeeded; false otherwise
     */
    private boolean bindToMarketBillingService() {
        try {
            if (Log.DEBUG) {
                Log.i("binding to Market billing service");
            }
            boolean bindResult = bindService(
                    new Intent(Consts.MARKET_BILLING_SERVICE_ACTION),
                    this,  // ServiceConnection.
                    Context.BIND_AUTO_CREATE);

            if (bindResult) {
                return true;
            } else {
                Log.e("Could not bind to service.");
            }
        } catch (SecurityException e) {
            Log.e("Security exception: " + e);
        }
        return false;
    }

    /**
     * Requests transaction information for all managed items. Call this only when the
     * application is first installed or after a database wipe. Do NOT call this
     * every time the application starts up.
     * @return false if there was an error connecting to Android Market
     */
    public boolean restoreTransactions() {
        return new RestoreTransactions().runRequest();
    }

    /**
     * Confirms receipt of a purchase state change. Each {@code notifyId} is
     * an opaque identifier that came from the server. This method sends those
     * identifiers back to the MarketBillingService, which ACKs them to the
     * server. Returns false if there was an error trying to connect to the
     * MarketBillingService.
     * @param startId an identifier for the invocation instance of this service
     * @param notifyIds a list of opaque identifiers associated with purchase
     * state changes.
     * @return false if there was an error connecting to Market
     */
    private boolean confirmNotifications(int startId, String[] notifyIds) {
        return new ConfirmNotifications(startId, notifyIds).runRequest();
    }

    /**
     * Verifies that the data was signed with the given signature, and calls
     * {@link ResponseHandler#purchaseResponse(Context, PurchaseState, String, String, long)}
     * for each verified purchase.
     * @param startId an identifier for the invocation instance of this service
     * @param signedData the signed JSON string (signed, not encrypted)
     * @param signature the signature for the data, signed with the private key
     */
    private void purchaseStateChanged(int startId, String signedData, String signature) {
        ArrayList<Security.VerifiedPurchase> purchases;
        purchases = Security.verifyPurchase(signedData, signature);
        if (purchases == null) {
            return;
        }

        ArrayList<String> notifyList = new ArrayList<String>();
        for (VerifiedPurchase vp : purchases) {
            if (vp.notificationId != null) {
                notifyList.add(vp.notificationId);
            }
            ResponseHandler.purchaseResponse(this, vp.purchaseState, vp.productId,
                    vp.orderId, vp.purchaseTime, vp.developerPayload);
        }
        if (!notifyList.isEmpty()) {
            String[] notifyIds = notifyList.toArray(new String[notifyList.size()]);
            confirmNotifications(startId, notifyIds);
        }
    }

    /**
     * This is called when we receive a response code from Android Market for a request
     * that we made. This is used for reporting various errors and for
     * acknowledging that an order was sent to the server. This is NOT used
     * for any purchase state changes.  All purchase state changes are received
     * in the {@link BillingReceiver} and passed to this service, where they are
     * handled in {@link #purchaseStateChanged(int, String, String)}.
     * @param requestId a number that identifies a request, assigned at the
     * time the request was made to Android Market
     * @param responseCode a response code from Android Market to indicate the state
     * of the request
     */
    private void checkResponseCode(long requestId, ResponseCode responseCode) {
        BillingRequest request = mSentRequests.get(requestId);
        if (request != null) {
            if (Log.DEBUG) {
                Log.v(request.getClass().getSimpleName() + ": " + responseCode);
            }
            request.responseCodeReceived(responseCode);
        }
        mSentRequests.remove(requestId);
    }

    /**
     * Runs any pending requests that are waiting for a connection to the
     * service to be established.  This runs in the main UI thread.
     */
    private void runPendingRequests() {
        int maxStartId = -1;
        BillingRequest request;
        while ((request = mPendingRequests.peek()) != null) {
            if (request.runIfConnected()) {
                // Remove the request
                mPendingRequests.remove();

                // Remember the largest startId, which is the most recent
                // request to start this service.
                if (maxStartId < request.getStartId()) {
                    maxStartId = request.getStartId();
                }
            } else {
                // The service crashed, so restart it. Note that this leaves
                // the current request on the queue.
                bindToMarketBillingService();
                return;
            }
        }

        // If we get here then all the requests ran successfully.  If maxStartId
        // is not -1, then one of the requests started the service, so we can
        // stop it now.
        if (maxStartId >= 0) {
            if (Log.DEBUG) {
                Log.i("stopping service, startId: " + maxStartId);
            }
            stopSelf(maxStartId);
        }
    }

    /**
     * This is called when we are connected to the MarketBillingService.
     * This runs in the main UI thread.
     */
    public void onServiceConnected(ComponentName name, IBinder service) {
        if (Log.DEBUG) {
            Log.v("Billing service connected");
        }
        mService = IMarketBillingService.Stub.asInterface(service);
        runPendingRequests();
    }

    /**
     * This is called when we are disconnected from the MarketBillingService.
     */
    public void onServiceDisconnected(ComponentName name) {
        Log.v("Billing service disconnected");
        mService = null;
    }

    /**
     * Unbinds from the MarketBillingService. Call this when the application
     * terminates to avoid leaking a ServiceConnection.
     */
    public void unbind() {
        try {
            unbindService(this);
        } catch (IllegalArgumentException e) {
            // This might happen if the service was disconnected
        }
    }
}
