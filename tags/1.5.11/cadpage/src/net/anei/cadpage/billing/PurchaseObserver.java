// Copyright 2010 Google Inc. All Rights Reserved.

package net.anei.cadpage.billing;

import net.anei.cadpage.Log;
import net.anei.cadpage.billing.BillingService.RequestPurchase;
import net.anei.cadpage.billing.BillingService.RestoreTransactions;
import net.anei.cadpage.billing.Consts.PurchaseState;
import net.anei.cadpage.billing.Consts.ResponseCode;

import android.app.Activity;
import android.app.PendingIntent;
import android.app.PendingIntent.CanceledException;
import android.content.Intent;
import android.content.IntentSender;

import java.lang.reflect.Method;

/**
 * An interface for observing changes related to purchases. The main application
 * extends this class and registers an instance of that derived class with
 * {@link ResponseHandler}. The main application implements the callbacks
 * {@link #onBillingSupported(boolean)} and
 * {@link #onPurchaseStateChange(PurchaseState, String, int, long)}.  These methods
 * are used to update the UI.
 */
public abstract class PurchaseObserver {
    private Method mStartIntentSender;
    private Object[] mStartIntentSenderArgs = new Object[5];
    private static final Class<?>[] START_INTENT_SENDER_SIG = new Class<?>[] {
        IntentSender.class, Intent.class, int.class, int.class, int.class
    };

    public PurchaseObserver() {
        initCompatibilityLayer();
    }

    /**
     * This is the callback that is invoked when Android Market responds to the
     * {@link BillingService#checkBillingSupported()} request.
     * @param supported true if in-app billing is supported.
     */
    public abstract void onBillingSupported(boolean supported);

    /**
     * This is the callback that is invoked when an item is purchased,
     * refunded, or canceled.  It is the callback invoked in response to
     * calling {@link BillingService#requestPurchase(String)}.  It may also
     * be invoked asynchronously when a purchase is made on another device
     * (if the purchase was for a Market-managed item), or if the purchase
     * was refunded, or the charge was canceled.  This handles the UI
     * update.  The database update is handled in
     * {@link ResponseHandler#purchaseResponse(Context, PurchaseState,
     * String, String, long)}.
     * @param purchaseState the purchase state of the item
     * @param itemId a string identifying the item (the "SKU")
     * @param quantity the current quantity of this item after the purchase
     * @param purchaseTime the time the product was purchased, in
     * milliseconds since the epoch (Jan 1, 1970)
     */
    public abstract void onPurchaseStateChange(PurchaseState purchaseState,
            String itemId, long purchaseTime, String developerPayload);

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
    public abstract void onRequestPurchaseResponse(RequestPurchase request,
            ResponseCode responseCode);

    /**
     * This is called when we receive a response code from Android Market for a
     * RestoreTransactions request that we made.  A response code of
     * RESULT_OK means that the request was successfully sent to the server.
     */
    public abstract void onRestoreTransactionsResponse(RestoreTransactions request,
            ResponseCode responseCode);

    private void initCompatibilityLayer() {
        try {
            mStartIntentSender = Activity.class.getMethod("startIntentSender",
                    START_INTENT_SENDER_SIG);
        } catch (SecurityException e) {
            mStartIntentSender = null;
        } catch (NoSuchMethodException e) {
            mStartIntentSender = null;
        }
    }

    void startBuyPageActivity(Activity activity, PendingIntent pendingIntent, Intent intent) {
        if (mStartIntentSender != null) {
            // This is on Android 2.0 and beyond.  The in-app buy page activity
            // must be on the activity stack of the application.
            try {
                // This implements the method call:
                // mActivity.startIntentSender(pendingIntent.getIntentSender(),
                //     intent, 0, 0, 0);
                mStartIntentSenderArgs[0] = pendingIntent.getIntentSender();
                mStartIntentSenderArgs[1] = intent;
                mStartIntentSenderArgs[2] = Integer.valueOf(0);
                mStartIntentSenderArgs[3] = Integer.valueOf(0);
                mStartIntentSenderArgs[4] = Integer.valueOf(0);
                mStartIntentSender.invoke(activity, mStartIntentSenderArgs);
            } catch (Exception e) {
                Log.e(e);
            }
        } else {
            // This is on Android version 1.6. The in-app buy page activity must be on its
            // own separate activity stack instead of on the activity stack of
            // the application.
            try {
                pendingIntent.send(activity, 0 /* code */, intent);
            } catch (CanceledException e) {
                Log.e(e);
            }
        }
    }

    /**
     * Report purchase state change
     * @param purchaseState the purchase state of the item
     * @param itemId a string identifying the item
     */
    void postPurchaseStateChange(PurchaseState purchaseState, String itemId,
                                  long purchaseTime, String developerPayload) {
      onPurchaseStateChange(purchaseState, itemId, purchaseTime, developerPayload);
    }
}
