// Copyright 2010 Google Inc. All Rights Reserved.

package net.anei.cadpage.billing;

import net.anei.cadpage.billing.BillingService.RequestPurchase;
import net.anei.cadpage.billing.BillingService.RestoreTransactions;
import net.anei.cadpage.billing.Consts.PurchaseState;
import net.anei.cadpage.billing.Consts.ResponseCode;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import net.anei.cadpage.Log;

/**
 * This class contains the methods that handle responses from Android Market.  The
 * implementation of these methods is specific to a particular application.
 * The methods in this example update the database and, if the main application
 * has registered a {@llink PurchaseObserver}, will also update the UI.  An
 * application might also want to forward some responses on to its own server,
 * and that could be done here (in a background thread) but this example does
 * not do that.
 */
public class ResponseHandler {

    /**
     * This is a static instance of {@link PurchaseObserver} that the
     * application creates and registers with this class. The PurchaseObserver
     * is used for updating the UI if the UI is visible.
     */
    private static PurchaseObserver sPurchaseObserver;

    /**
     * Registers an observer that updates the UI.
     * @param observer the observer to register
     */
    public static synchronized void register(PurchaseObserver observer) {
        sPurchaseObserver = observer;
    }

    /**
     * Unregisters a previously registered observer.
     * @param observer the previously registered observer.
     */
    public static synchronized void unregister(PurchaseObserver observer) {
        sPurchaseObserver = null;
    }

    /**
     * Notifies the application of the availability of the MarketBillingService.
     * This method is called in response to the application calling
     * {@link BillingService#checkBillingSupported()}.
     * @param supported true if in-app billing is supported.
     */
    public static void checkBillingSupportedResponse(boolean supported) {
        if (sPurchaseObserver != null) {
            sPurchaseObserver.onBillingSupported(supported);
        }
    }

    /**
     * Starts a new activity for the user to buy an item for sale. This method
     * forwards the intent on to the PurchaseObserver (if it exists) because
     * we need to start the activity on the activity stack of the application.
     *
     * @param pendingIntent a PendingIntent that we received from Android Market that
     *     will create the new buy page activity
     * @param intent an intent containing a request id in an extra field that
     *     will be passed to the buy page activity when it is created
     */
    public static void buyPageIntentResponse(Activity activity, PendingIntent pendingIntent, Intent intent) {
        if (sPurchaseObserver == null) {
            if (Log.DEBUG) {
                Log.v("UI is not running");
            }
            return;
        }
        sPurchaseObserver.startBuyPageActivity(activity, pendingIntent, intent);
    }

    /**
     * Notifies the application of purchase state changes. The application
     * can offer an item for sale to the user via
     * {@link BillingService#requestPurchase(String)}. The BillingService
     * calls this method after it gets the response. Another way this method
     * can be called is if the user bought something on another device running
     * this same app. Then Android Market notifies the other devices that
     * the user has purchased an item, in which case the BillingService will
     * also call this method. Finally, this method can be called if the item
     * was refunded.
     * @param purchaseState the state of the purchase request (PURCHASED,
     *     CANCELED, or REFUNDED)
     * @param productId a string identifying a product for sale
     * @param orderId a string identifying the order
     * @param purchaseTime the time the product was purchased, in milliseconds
     *     since the epoch (Jan 1, 1970)
     * @param developerPayload the developer provided "payload" associated with
     *     the order
     */
    public static void purchaseResponse(
            final Context context, final PurchaseState purchaseState, final String productId,
            final String orderId, final long purchaseTime, final String developerPayload) {
      
      if (sPurchaseObserver != null) {
        sPurchaseObserver.postPurchaseStateChange(
                purchaseState, productId, purchaseTime, developerPayload);
      }
    }

    /**
     * This is called when we receive a response code from Android Market for a
     * RequestPurchase request that we made.  This is used for reporting various
     * errors and also for acknowledging that an order was sent successfully to
     * the server. This is NOT used for any purchase state changes. All
     * purchase state changes are received in the {@link BillingReceiver} and
     * are handled in {@link Security#verifyPurchase(String, String)}.
     * @param context the context
     * @param request the RequestPurchase request for which we received a
     *     response code
     * @param responseCode a response code from Market to indicate the state
     * of the request
     */
    public static void responseCodeReceived(Context context, RequestPurchase request,
            ResponseCode responseCode) {
        if (sPurchaseObserver != null) {
            sPurchaseObserver.onRequestPurchaseResponse(request, responseCode);
        }
    }

    /**
     * This is called when we receive a response code from Android Market for a
     * RestoreTransactions request.
     * @param context the context
     * @param request the RestoreTransactions request for which we received a
     *     response code
     * @param responseCode a response code from Market to indicate the state
     *     of the request
     */
    public static void responseCodeReceived(Context context, RestoreTransactions request,
            ResponseCode responseCode) {
        if (sPurchaseObserver != null) {
            sPurchaseObserver.onRestoreTransactionsResponse(request, responseCode);
        }
    }
}
