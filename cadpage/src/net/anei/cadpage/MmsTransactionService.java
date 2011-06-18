/*
 * Copyright (C) 2007-2008 Esmertec AG.
 * Copyright (C) 2007-2008 The Android Open Source Project
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

package net.anei.cadpage;

import android.app.Notification;
import android.app.Service;
import android.content.ContentResolver;
import android.content.ContentUris;
import android.content.Context;
import android.content.Intent;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.PowerManager;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import net.anei.cadpage.mms.GenericPdu;
import net.anei.cadpage.mms.PduParser;
import net.anei.cadpage.parsers.SmsMsgParser;

/**
 * This service is responsible for retrieving the actual content of MMS messages
 * that have been determined to be possible CAD pages
 */
public class MmsTransactionService extends Service {
  
  private static final Uri MMS_URI = Uri.parse("content://mms");
  private static final Uri MMS_INBOX_URI = Uri.withAppendedPath(MMS_URI, "inbox");
  private enum EventType {TRANSACTION_REQUEST, TRANSACTION_COMPLETED_ACTION, DATA_CHANGE, TIMEOUT, TIMER_TICK, QUIT};

  // Column names for query searches
  private static String[] MMS_COL_LIST = new String[]{"_ID"};
  private static String[] PART_COL_LIST = new String[]{"text", "_data"};

  
  private Handler mainHandler = new Handler();
  private ServiceHandler mServiceHandler;
  private Looper mServiceLooper;
  private PowerManager.WakeLock mWakeLock;
  
  // Cached copies of different preferences we might need during off thread processing
  private boolean overrideFilter;
  private String sFilter;
  private boolean genAlert;
  private SmsMsgParser parser;
  private SmsMsgParser genAlertParser;
  private boolean smsPassThrough;
  private int mmsTimeout = 60*1000;

  @Override
  public void onCreate() {
    if (Log.DEBUG) Log.v("MmsTransactionService.onCreate()");

    // Give us foreground priority
    // we are saving our message list in memory and really don't want to
    // destroyed if we can possibly help it.
    startForeground(0, new Notification());
    
    // Acquire simple power lock while we are running
    PowerManager pm = (PowerManager)getSystemService(Context.POWER_SERVICE);
    mWakeLock = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK, "MMS Connectivity");
    mWakeLock.setReferenceCounted(false);
    mWakeLock.acquire();

    // Start up the thread running the service.  Note that we create a
    // separate thread because the service normally runs in the process's
    // main thread, which we don't want to block.
    HandlerThread thread = new HandlerThread("MmsTransactionService");
    thread.start();

    mServiceLooper = thread.getLooper();
    mServiceHandler = new ServiceHandler(mServiceLooper);

  }

  @Override
  public int onStartCommand(Intent intent, int flags, int startId) {
    if (Log.DEBUG) Log.v("MmsTransactionService.onStart()");
    if (intent == null) return START_NOT_STICKY;
    
    // Collect all of the preferences we might need while we are still on
    // the main thread;
    overrideFilter = ManagePreferences.overrideFilter();
    sFilter = (overrideFilter ? ManagePreferences.filter() : "");
    genAlert = ManagePreferences.genAlert();
    parser = ManageParsers.getInstance().getParser(null);
    genAlertParser = ManageParsers.getInstance().getAlertParser();
    smsPassThrough = ManagePreferences.smspassthru();
    
    EventType type;
    if ("android.provider.Telephony.WAP_PUSH_RECEIVED".equals(intent.getAction())) {
      type = EventType.TRANSACTION_REQUEST;
    } else if ("android.intent.action.TRANSACTION_COMPLETED_ACTION".equals(intent.getAction())) {
      type = EventType.TRANSACTION_COMPLETED_ACTION;
      if (Log.DEBUG) {
        Log.v("EventType.TRANSACTION_COMPLETED_ACTION");
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
          Log.v("state:" + bundle.get("state"));
          Log.v("uri:" + bundle.get("uri"));
        }
      }
    } else {
      Log.w("Unidentified request received by MmsTransactionService");
      return START_NOT_STICKY;
    }
    Message msg = mServiceHandler.obtainMessage(type.ordinal());
    msg.arg1 = startId;
    msg.obj = intent;
    mServiceHandler.sendMessage(msg);
    return Service.START_STICKY;
  }


  @Override
  public void onDestroy() {
    mWakeLock.release();
    mServiceHandler.sendEmptyMessage(EventType.QUIT.ordinal());
  }

  @Override
  public IBinder onBind(Intent intent) {
      return null;
  }
  
  // 
  private class MmsMsgEntry {
    SmsMmsMessage message = null;  // Message we are working on
    int msgId = -1;                // Message ID number
    boolean loading = false;      // True if we have requested data content download
  }

  // Main thread handler class
  private class ServiceHandler extends Handler {
    
    // Actual queue of pending MMS transactions
    private List<MmsMsgEntry> msgList  = new LinkedList<MmsMsgEntry>();
    
    private ContentResolver qr;
    private ContentObserver observer = null;

    
    public ServiceHandler(Looper looper) {
      super(looper);
      qr = getContentResolver();
      
      // Start timer ticks
      sendEmptyMessageDelayed(EventType.TIMER_TICK.ordinal(), 1000);
    }

    /**
     * Handle incoming transaction requests.
     * The incoming requests are initiated by the MMSC Server or by the
     * MMS Client itself.
     */
    @Override
    public void handleMessage(Message msg) {
      
      try {
  
        
        EventType type = EventType.values()[msg.what];
        if (Log.DEBUG) Log.v("mmsTransactionService." + type);
        switch (type) {
        case QUIT:
          if (!msgList.isEmpty()) {
            Log.w("TransactionService exiting with transaction still pending");
          }
          if (observer != null) qr.unregisterContentObserver(observer);
          observer = null;
          getLooper().quit();
          return;
        
        case TRANSACTION_REQUEST:
          Intent intent = (Intent)msg.obj;
          mmsReceive(intent);
          break;
          
        case TIMER_TICK:
          sendEmptyMessageDelayed(EventType.TIMER_TICK.ordinal(), 1000);
          
        case DATA_CHANGE:
        case TRANSACTION_COMPLETED_ACTION:
          mmsDataChange();
          break;
        
        case TIMEOUT:
          MmsMsgEntry entry = (MmsMsgEntry)msg.obj;
          if (msgList.remove(entry)) {
            SmsMsgLogBuffer.getInstance().add(entry.message.timeoutMarker());
          }
        }
          
        
        // If the message queue is empty, it is time to shut down
        if (msgList.size() == 0) {
          mainHandler.post(new Runnable(){
            @Override
            public void run() {
              stopSelf();
            }});
          return;
        }
        
        // Otherwise, make sure we have a content monitor on MMS info
        if (observer == null) {
          observer = new ContentObserver(this){
            @Override
            public void onChange(boolean selfChange) {
              sendEmptyMessage(EventType.DATA_CHANGE.ordinal());
            }
          };
          qr.registerContentObserver(MMS_URI, true, observer);
          qr.registerContentObserver(Uri.withAppendedPath(MMS_URI, "part"), true, observer);
        }
      } 
      
      // Exceptions thrown on this thread should be caught and rethrown on the
      // main thread where our top level exception handler will catch them.
      catch (final RuntimeException ex) {
        mainHandler.post(new Runnable(){
          @Override
          public void run() {
            throw(ex);
          }});
      }
    }

    /**
     * Process initial incoming MMS notification
     * @param intent MMS notification intent
     */
    private void mmsReceive(Intent intent) {
      // Get raw PDU push-data from the message and parse it
      byte[] pushData = intent.getByteArrayExtra("data");
  
      GenericPdu pdu = new PduParser(pushData).parse();
      if (null == pdu) {
        Log.e("Invalid PUSH data");
        return;
      }
      
      SmsMmsMessage message = pdu.getMessage();
      if (message == null) return;
  
      // See if message passes override from filter
      if (overrideFilter) {
        String sAddress = message.getAddress();
        if (! SmsPopupUtils.matchFilter(sAddress, sFilter)) return;
      }
      
      // Ignore if we have already processed a notification for this message
      if (SmsMsgLogBuffer.getInstance().checkDuplicateNotice(message)) return;
      
      // Save message for future test or error reporting use
      // Duplicate message check is ignored for now because we do not yet have a message body
      SmsMsgLogBuffer.getInstance().add(message);
      
      // See if we can rule out this message without the text body
      // meaning with just a subject and from address
      boolean isPage = (genAlert && sFilter.length() > 1) ||
          parser.isPageMsg(message, overrideFilter, genAlert);
      
      // If not a cad page, we're done with this
      if (! isPage) return;
      
      // Otherwise, add to the list of messages that we are waiting for content from.
      MmsMsgEntry entry = new MmsMsgEntry();
      entry.message = message;
      msgList.add(entry);
      
      // Post a timeout event for this message to remove if from the queue if
      // we haven't received any data content
      Message msg = mServiceHandler.obtainMessage(EventType.TIMEOUT.ordinal());
      msg.obj = entry;
      mServiceHandler.sendMessageDelayed(msg, mmsTimeout);
    }

    /**
     * Process MMS content data change
     */
    private void mmsDataChange() {
      
      // Loop through all of the pending MMS message entries
      for (Iterator<MmsMsgEntry> iter = msgList.iterator(); iter.hasNext(); ) {
        MmsMsgEntry entry = iter.next();
        final SmsMmsMessage message = entry.message;
        
        // If we don't have an record number for this message, try to get one
        entry.msgId = -1;
        if (entry.msgId < 0) {
          Cursor cur = qr.query(MMS_URI, MMS_COL_LIST, "tr_id=?", new String[]{message.getMmsMsgId()}, null);
          if (cur == null) continue;
          if (!cur.moveToFirst()) continue;
          entry.msgId = cur.getInt(0);
        }
        
        // OK, we have the desired record number
        // Now see if we can recover the content

        
        Uri mmsUri = ContentUris.withAppendedId(MMS_URI, entry.msgId);
        Uri partUri = Uri.withAppendedPath(mmsUri, "part");
        Cursor cur = qr.query(partUri, PART_COL_LIST, null, null, null);
        
        if (cur == null || !cur.moveToFirst()) {
          
          // The message contents do not yet exist.  Fire off a service request
          // to the messaging app to get these loaded if we haven't already
          if (entry.loading) continue; 
          if (Log.DEBUG) Log.v("Request MMS content for " + entry.message.getContentLoc()); 
          final Intent intent = new Intent();
          intent.setClassName("com.android.mms", "com.android.mms.transaction.TransactionService");
          intent.putExtra("type", 1);
          intent.putExtra("uri", ContentUris.withAppendedId(MMS_INBOX_URI, entry.msgId).toString());
          mainHandler.post(new Runnable(){
            @Override
            public void run() {
              if (startService(intent) == null) {
                Log.e("Tranaction.RETRIEVE_TRANSACTION service not found");
              }
            }});
          entry.loading = true;
          continue;
        }
        
        // Almost there, we have a return value, try to retrieve a text component from it
        String text = cur.getString(0);
        if (text == null) {
          byte[] ba = cur.getBlob(1);
          if (ba != null) text = new String(ba);
        }
        
        // for better or worse, we are done with this message, so let's
        // remove it from the processing list
        iter.remove();
        
        // If we didn't retrieve any text info, give it up
        // Otherwise add the text body to our message
        // And update the message saved in the log buffer
        if (text == null) continue;
        message.setMessageBody(text);
        SmsMsgLogBuffer.getInstance().update(message);
        
        // Now that we have the full message, we can try to parse it as a CAD page
        boolean isPage = 
            parser.isPageMsg(message, overrideFilter, genAlert);
        
        // If it didn't, see if we can accept this as a general page
        // which only happens if the general alert config settings is set and there
        // is an active user filter
        if (! isPage && genAlert && sFilter.length()>1) {
          isPage = genAlertParser.isPageMsg(message, overrideFilter, genAlert);
        }
        
        // If not a CAD page, we are done with it
        if (!isPage) continue;
        
        
        // If we were not supposed to pass messages through to the SMS app
        // we try to cover our tracks by deleting this message from the MMS
        // message content
        if (!smsPassThrough) qr.delete(mmsUri, null, null);
        
        // Pop back to the main thread to perform the rest of the CAD page 
        // message processing
        mainHandler.post(new Runnable(){
          @Override
          public void run() {
            SmsReceiver.processCadPage(MmsTransactionService.this, message);
          }});
      }
    }

  }
}
