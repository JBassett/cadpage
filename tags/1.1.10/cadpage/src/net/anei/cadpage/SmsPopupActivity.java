package net.anei.cadpage;


import android.os.Bundle;
import android.os.Environment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.anei.cadpage.ManageKeyguard.LaunchOnKeyguardExit;
import net.anei.cadpage.ManagePreferences.Defaults;
import net.anei.cadpage.preferences.ButtonListPreference;
import net.anei.cadpage.wrappers.TextToSpeechWrapper;
import net.anei.cadpage.wrappers.TextToSpeechWrapper.OnInitListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnCancelListener;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.speech.RecognizerIntent;
import android.text.TextUtils;
import android.view.ContextMenu;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.ViewStub;
import android.view.Window;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.WindowManager.LayoutParams;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.tts.TTS;
import com.google.tts.TTSVersionAlert;
import com.google.tts.TTS.InitListener;

@SuppressWarnings("deprecation")
public class SmsPopupActivity extends Activity {
  private SmsMmsMessage message;

  private boolean exitingKeyguardSecurely = false;
  private InputMethodManager inputManager = null;
  private View inputView = null;
  private TextView fromTV;
  private TextView messageReceivedTV;
  private TextView messageTV;

  private TextView mmsSubjectTV = null;
  private ScrollView messageScrollView = null;
  private EditText qrEditText = null;
  private ProgressDialog mProgressDialog = null;


  private ViewStub unreadCountViewStub;
  private View unreadCountView = null;
  private ViewStub mmsViewStub;
  private View mmsView = null;
  private ViewStub privacyViewStub;
  private View privacyView = null;
  private View buttonsLL = null;
  private LinearLayout mainLL = null;

  private boolean wasVisible = false;
  private boolean inbox = false;
  private boolean messageViewed = true;

  private static final double WIDTH = 0.9;
  private static final int MAX_WIDTH = 640;
  private static final int DIALOG_DELETE = Menu.FIRST;
  private static final int DIALOG_QUICKREPLY = Menu.FIRST + 1;
  private static final int DIALOG_PRESET_MSG = Menu.FIRST + 2;
  private static final int DIALOG_LOADING = Menu.FIRST + 3;

  private static final int CONTEXT_CLOSE_ID = Menu.FIRST;
  private static final int CONTEXT_DELETE_ID = Menu.FIRST + 1;
  private static final int CONTEXT_REPLY_ID = Menu.FIRST + 2;
  private static final int CONTEXT_QUICKREPLY_ID = Menu.FIRST + 3;
  private static final int CONTEXT_INBOX_ID = Menu.FIRST + 4;
  private static final int CONTEXT_TTS_ID = Menu.FIRST + 5;
  private static final int CONTEXT_VIEWCONTACT_ID = Menu.FIRST + 6;

  private static final int VOICE_RECOGNITION_REQUEST_CODE = 8888;

  private SmsPopupDbAdapter mDbAdapter;

  // TextToSpeech variables
  private boolean ttsInitialized = false;
  private static boolean androidTextToSpeechAvailable = false;
  private TTS eyesFreeTts = null;
  private TextToSpeechWrapper androidTts = null;
	
	private SmsMsgParser parser;
  

  // Establish whether the Android TextToSpeech class is available to us
  static {
    try {
      TextToSpeechWrapper.checkAvailable();
      androidTextToSpeechAvailable = true;
    } catch (Throwable t) {
      androidTextToSpeechAvailable = false;
    }
  }

  @Override
  protected void onCreate(Bundle bundle) {
    super.onCreate(bundle);
    if (Log.DEBUG) Log.v("SMSPopupActivity: onCreate()");
    // First things first, acquire wakelock, otherwise the phone may sleep
    //ManageWakeLock.acquirePartial(getApplicationContext());

    requestWindowFeature(Window.FEATURE_NO_TITLE);
    setContentView(R.layout.popup);
    // Check if screen orientation should be "user" or "behind" based on prefs
    if (ManagePreferences.autoRotate()) {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_USER);
    } else {
      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_BEHIND);
    }


    resizeLayout();

    // Find the main textviews
    fromTV = (TextView) findViewById(R.id.FromTextView);
    messageTV = (TextView) findViewById(R.id.MessageTextView);
    messageReceivedTV = (TextView) findViewById(R.id.HeaderTextView);
    messageScrollView = (ScrollView) findViewById(R.id.MessageScrollView);

    // Find the ImageView that will show the contact photo
    //photoImageView = (ImageView) findViewById(R.id.FromImageView);
    //contactPhotoPlaceholderDrawable =
    //  getResources().getDrawable(SmsPopupUtils.CONTACT_PHOTO_PLACEHOLDER);

    // Enable long-press context menu
    registerForContextMenu(findViewById(R.id.MainLinearLayout));

    // Assign view stubs
    unreadCountViewStub = (ViewStub) findViewById(R.id.UnreadCountViewStub);
    mmsViewStub = (ViewStub) findViewById(R.id.MmsViewStub);
    privacyViewStub = (ViewStub) findViewById(R.id.PrivacyViewStub);
    buttonsLL = findViewById(R.id.ButtonLinearLayout);


    // Get shared prefs
    SharedPreferences mPrefs = PreferenceManager.getDefaultSharedPreferences(this);
    
    // See if user wants to show buttons on the popup
    if (!mPrefs.getBoolean(
        getString(R.string.pref_show_buttons_key), Defaults.PREFS_SHOW_BUTTONS)) {

      // Hide button layout
      buttonsLL.setVisibility(View.GONE);

    } else {

      // Button 1
      final Button button1 = (Button) findViewById(R.id.button1);
      PopupButton button1Vals =
        new PopupButton(getApplicationContext(), Integer.parseInt(mPrefs.getString(
            getString(R.string.pref_button1_key), Defaults.PREFS_BUTTON1)));
      button1.setOnClickListener(button1Vals);
      button1.setText(button1Vals.buttonText);
      button1.setVisibility(button1Vals.buttonVisibility);

      // Button 2
      final Button button2 = (Button) findViewById(R.id.button2);
      PopupButton button2Vals =
        new PopupButton(getApplicationContext(), Integer.parseInt(mPrefs.getString(
            getString(R.string.pref_button2_key), Defaults.PREFS_BUTTON2)));
      button2.setOnClickListener(button2Vals);
      button2.setText(button2Vals.buttonText);
      button2.setVisibility(button2Vals.buttonVisibility);

      // Button 3
      final Button button3 = (Button) findViewById(R.id.button3);
      PopupButton button3Vals =
        new PopupButton(getApplicationContext(), Integer.parseInt(mPrefs.getString(
            getString(R.string.pref_button3_key), Defaults.PREFS_BUTTON3)));
      button3.setOnClickListener(button3Vals);
      button3.setText(button3Vals.buttonText);
      button3.setVisibility(button3Vals.buttonVisibility);

      /*
       * This is really hacky. There are two types of reply buttons (quick reply
       * and reply). If the user has selected to show both the replies then the
       * text on the buttons should be different. If they only use one then the
       * text can just be "Reply".
       */
      int numReplyButtons = 0;
      if (button1Vals.isReplyButton) numReplyButtons++;
      if (button2Vals.isReplyButton) numReplyButtons++;
      if (button3Vals.isReplyButton) numReplyButtons++;

      if (numReplyButtons == 1) {
        if (button1Vals.isReplyButton) button1.setText(R.string.button_reply);
        if (button2Vals.isReplyButton) button2.setText(R.string.button_reply);
        if (button3Vals.isReplyButton) button3.setText(R.string.button_reply);
      }
    }

    populateViews(getIntent());


    mDbAdapter = new SmsPopupDbAdapter(getApplicationContext());
    
    wakeApp();

    // Eula.show(this);
  }



 
/*
   * Internal class to handle dynamic button functions on popup
   */
  class PopupButton implements OnClickListener {
    private int buttonId;
    public boolean isReplyButton;
    public String buttonText;
    public int buttonVisibility = View.VISIBLE;

    public PopupButton(Context mContext, int id) {
      buttonId = id;
      isReplyButton = false;
      if (buttonId == ButtonListPreference.BUTTON_REPLY
          || buttonId == ButtonListPreference.BUTTON_QUICKREPLY
          || buttonId == ButtonListPreference.BUTTON_REPLY_BY_ADDRESS) {
        isReplyButton = true;
      }
      String[] buttonTextArray = mContext.getResources().getStringArray(R.array.buttons_text);
      buttonText = buttonTextArray[buttonId];

      if (buttonId == ButtonListPreference.BUTTON_DISABLED) { // Disabled
        buttonVisibility = View.GONE;
      }
    }

    public void onClick(View v) {
      switch (buttonId) {
        case ButtonListPreference.BUTTON_DISABLED: // Disabled
          break;
        case ButtonListPreference.BUTTON_CLOSE: // Close
//          closeMessage();
          finish();
          break;
        case ButtonListPreference.BUTTON_DELETE: // Delete
          showDialog(DIALOG_DELETE);
          finish();
          break;
        case ButtonListPreference.BUTTON_DELETE_NO_CONFIRM: // Delete no confirmation
//          deleteMessage();
          break;
        case ButtonListPreference.BUTTON_REPLY: // Reply
//          replyToMessage(true);
          break;
        case ButtonListPreference.BUTTON_REPLY_BY_ADDRESS: // Quick Reply
//          replyToMessage(false);
          break;
        case ButtonListPreference.BUTTON_INBOX: // Inbox
//          gotoInbox();
          break;
        case ButtonListPreference.BUTTON_TTS: // Text-to-Speech
          speakMessage();
          break;
        case ButtonListPreference.BUTTON_MAP: // Google Map the Call
          mapMessage();
          break;
      }
    }
  }

  @Override
  protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    if (Log.DEBUG) Log.v("SMSPopupActivity: onNewIntent()");

    // First things first, acquire wakelock, otherwise the phone may sleep
    //ManageWakeLock.acquirePartial(getApplicationContext());

    setIntent(intent);

    // Force a reload of the contact photo
    //contactPhoto = null;

    // Re-populate views with new intent data (ie. new sms data)
    populateViews(intent);

    wakeApp();
  }

  @Override
  protected void onStart() {
    super.onStart();
    if (Log.DEBUG) Log.v("SMSPopupActivity: onStart()");
    //ManageWakeLock.acquirePartial(getApplicationContext());
  }

  @Override
  protected void onResume() {
    super.onResume();
    if (Log.DEBUG) Log.v("SMSPopupActivity: onResume()");
    wasVisible = false;
    // Reset exitingKeyguardSecurely bool to false
    exitingKeyguardSecurely = false;
  }

  @Override
  protected void onPause() {
    super.onPause();
    if (Log.DEBUG) Log.v("SMSPopupActivity: onPause()");

    // Hide the soft keyboard in case it was shown via quick reply
    hideSoftKeyboard();

    // Shutdown eyes-free TTS
    if (eyesFreeTts != null) {
      eyesFreeTts.shutdown();
    }

    // Shutdown Android TTS
    if (androidTextToSpeechAvailable) {
      if (androidTts != null) {
        androidTts.shutdown();
      }
    }

    // Dismiss loading dialog
    if (mProgressDialog != null) {
      mProgressDialog.dismiss();
    }

    if (wasVisible) {
      // Cancel the receiver that will clear our locks
      ClearAllReceiver.removeCancel(getApplicationContext());
      ClearAllReceiver.clearAll(!exitingKeyguardSecurely);
    }

    mDbAdapter.close();
  }

  @Override
  protected void onStop() {
    super.onStop();
    if (Log.DEBUG) Log.v("SMSPopupActivity: onStop()");

    // Cancel the receiver that will clear our locks
    ClearAllReceiver.removeCancel(getApplicationContext());
    ClearAllReceiver.clearAll(!exitingKeyguardSecurely);
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
  }

  @Override
  public void onWindowFocusChanged(boolean hasFocus) {
    super.onWindowFocusChanged(hasFocus);
    // Log.v("SMSPopupActivity: onWindowFocusChanged(" + hasFocus + ")");
    if (hasFocus) {
      // This is really hacky, basically a flag that is set if the message
      // was at some point visible. I tried using onResume() or other methods
      // to prevent doing some things 2 times but this seemed to be the only
      // reliable way (?)
      wasVisible = true;
      refreshPrivacy();
    }
  }

  @Override
  public void onSaveInstanceState(Bundle outState) {
    super.onSaveInstanceState(outState);
    if (Log.DEBUG) Log.v("SMSPopupActivity: onSaveInstanceState()");

    // Save values from most recent bundle (ie. most recent message)
    outState.putAll(getIntent().getExtras());
  }

  /*
   * Customized activity finish. Ensures the notification is in sync and cancels
   * any scheduled reminders (as the user has interrupted the app.
   */
//  private void myFinish() {
//    if (Log.DEBUG) Log.v("myFinish()");
//
//    if (inbox) {
//      ManageNotification.clearAll(getApplicationContext());
//    } else {
//
//      // Start a service that will update the notification in the status bar
//      Intent i = new Intent(getApplicationContext(), SmsPopupUtilsService.class);
//      i.setAction(SmsPopupUtilsService.ACTION_UPDATE_NOTIFICATION);
//
//      // Convert current message to bundle
//      message.addToIntent(i);
//
//      // We need to know if the user is replying - if so, the entire thread id should
//      // be ignored when working out the message tally in the notification bar.
//      // We can't rely on the system database as it may take a little while for the
//      // reply intent to fire and load up the messaging up (after which the messages
//      // will be marked read in the database).
//      i.putExtra(SmsMmsMessage.EXTRAS_REPLYING, replying);
//
//      // Start the service
//      SmsPopupUtilsService.beginStartingService(SmsPopupActivity.this.getApplicationContext(), i);
//    }
//
//    // Cancel any reminder notifications
//    ReminderReceiver.cancelReminder(getApplicationContext());
//
//    // Finish up the activity
//    finish();
//  }

  // Populate views from intent
  private void populateViews(Intent intent) {

    // Regenerate the SmsMmsMessage from the extras bundle
    populateViews(new SmsMmsMessage(getApplicationContext(), intent));
  }

  /*
   * Populate all the main SMS/MMS views with content from the actual
   * SmsMmsMessage
   */
  private void populateViews(SmsMmsMessage newMessage) {

    // Store message
    message = newMessage;
    parser = new SmsMsgParser(newMessage.getMessageFull());
    
    // If it's a MMS message, just show the MMS layout
    if (message.getMessageType() == SmsMmsMessage.MESSAGE_TYPE_MMS) {
      if (mmsView == null) {
        mmsView = mmsViewStub.inflate();
        mmsSubjectTV = (TextView) mmsView.findViewById(R.id.MmsSubjectTextView);

        // The ViewMMS button
//        Button viewMmsButton = (Button) mmsView.findViewById(R.id.ViewMmsButton);
//        viewMmsButton.setOnClickListener(new OnClickListener() {
//          public void onClick(View v) {
//            replyToMessage();
//          }
//        });
      }
      messageScrollView.setVisibility(View.GONE);
      // privacyViewStub.setVisibility(View.GONE);
      mmsView.setVisibility(View.VISIBLE);

      // If no MMS subject, hide the subject text view
      if (TextUtils.isEmpty(message.getMessageBody())) {
        mmsSubjectTV.setVisibility(View.GONE);
      } else {
        mmsSubjectTV.setVisibility(View.VISIBLE);
      }
    } else {
      // Otherwise hide MMS layout
      if (mmsView != null) {
        mmsView.setVisibility(View.GONE);
      }

      // Refresh privacy settings (hide/show message) depending on privacy setting
      refreshPrivacy();
    }

    // If only 1 unread message waiting
    if (message.getUnreadCount() <= 1) {
      if (unreadCountView != null) {
        unreadCountView.setVisibility(View.GONE);
      }
    } else { // More unread messages waiting, show the extra view
      if (unreadCountView == null) {
        unreadCountView = unreadCountViewStub.inflate();
      }
      unreadCountView.setVisibility(View.VISIBLE);
      TextView tv = (TextView) unreadCountView.findViewById(R.id.UnreadCountTextView);

      String textWaiting = getString(R.string.unread_text_waiting, message.getUnreadCount() - 1);
      tv.setText(textWaiting);

      // The inbox button
//      Button inboxButton = (Button) unreadCountView.findViewById(R.id.InboxButton);
//      inboxButton.setOnClickListener(new OnClickListener(){
//
//        @Override
//        public void onClick(View v) {
//          gotoInbox();
//        }});
    }
   
    
    // Update TextView that contains the timestamp for the incoming message
    String headerText = getString(R.string.new_text_at, message.getFormattedTimestamp().toString());

    // Set the from, message and header views
    fromTV.setText(parser.getCall());
    if (message.getMessageType() == SmsMmsMessage.MESSAGE_TYPE_SMS) {
      StringBuilder sb = new StringBuilder(parser.getAddress());
      if (parser.getCity().length() > 0) {
        sb.append('\n');
        sb.append(parser.getCity());
      }
      sb.append("\nX:");
      sb.append(parser.getCross());
      if (parser.getUnit().length() > 0) {
        sb.append("\nUnits: ");
        sb.append(parser.getUnit());
      }
      messageTV.setText(sb.toString());
    } else {
      mmsSubjectTV.setText(getString(R.string.mms_subject) + " " + message.getMessageBody());
    }
    messageReceivedTV.setText(headerText);
    
    // There used to be a call to myFinish() that was invoked if this method was
    // passed a message that was not a CAD page.  I am about as certain as I can
    // possibly be that this is no longer possible, which is why this call no
    // longer exists.  But the comment remains as a possible clue if someone
    // should discover I was wrong.
    
    //Will add a Database method in the future.
    //storeFileMessage();
    
  } //end of function

  
private void storeFileMessage(String sMessage) {
	// Store the message in a flat file for History.
	//Use calldata to get information.
	String sFileName = R.string.app_file_dir +"/" + R.string.app_file_name;
	if (externalStorageAvailable()){
		File sdcard = Environment.getExternalStorageDirectory();
		try {
			FileOutputStream fos = openFileOutput(sFileName,Context.MODE_APPEND);
			try {
				fos.write(sMessage.getBytes());
			} catch (IOException e) {
				if (Log.DEBUG) Log.v("Exception in storeFileMessage Open for Writing." + e.toString());
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			if (Log.DEBUG) Log.v("Exception in storeFileMessage Open for Writing." + e.toString());
			e.printStackTrace();
		}
		StringBuilder text = new StringBuilder();
		
	}
}

private boolean externalStorageAvailable() {
	// From google
	boolean mExternalStorageAvailable = false;
	boolean mExternalStorageWriteable = false;
	String state = Environment.getExternalStorageState();

	if (Environment.MEDIA_MOUNTED.equals(state)) {
	    // We can read and write the media
	    mExternalStorageAvailable = mExternalStorageWriteable = true;
	} else if (Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
	    // We can only read the media
	    mExternalStorageAvailable = true;
	    mExternalStorageWriteable = false;
	} else {
	    // Something else is wrong. It may be one of many other states, but all we need
	    //  to know is we can neither read nor write
	    mExternalStorageAvailable = mExternalStorageWriteable = false;
	}
	if (mExternalStorageAvailable==true && mExternalStorageWriteable==true){
		return true;
	}
	return false;
}
  /*
   * This handles hiding and showing various views depending on the privacy
   * settings of the app and the current state of the phone (keyguard on or off)
   */
  final private void refreshPrivacy() {
    if (Log.DEBUG) Log.v("refreshPrivacy()");
    messageViewed = true;

    // This gets called before onNewIntent() which mean message may not be set
    // Don't understand this well enough to know how this should be handled, but
    // for now this will keep the app from closing  -kec
    if (message != null && message.getMessageType() == SmsMmsMessage.MESSAGE_TYPE_SMS) {
      if (ManagePreferences.privacyMode()) {
        // We need to init the keyguard class so we can check if the keyguard is
        // on
        ManageKeyguard.initialize(getApplicationContext());

        if (ManageKeyguard.inKeyguardRestrictedInputMode()) {
          messageViewed = false;

          if (privacyView == null) {
            privacyView = privacyViewStub.inflate();

            // The view button (if in privacy mode)
            Button viewButton = (Button) privacyView.findViewById(R.id.ViewButton);
            viewButton.setOnClickListener(new OnClickListener() {
              public void onClick(View v) {
                viewMessage();
              }
            });
          }
          messageScrollView.setVisibility(View.GONE);
        } else {
          if (privacyView != null) {
            privacyView.setVisibility(View.GONE);
          }
          messageScrollView.setVisibility(View.VISIBLE);
        }
      } else {
        if (privacyView != null) {
          privacyView.setVisibility(View.GONE);
        }
        messageScrollView.setVisibility(View.VISIBLE);
      }
    }
  }

  /*
   * Wake up the activity, this will acquire the wakelock (turn on the screen)
   * and sound the notification if needed. This is called once all preparation
   * is done for this activity (end of onCreate()).
   */
  private void wakeApp() {
    // Time to acquire a full WakeLock (turn on screen)
    ManageWakeLock.acquireFull(getApplicationContext());
    ManageWakeLock.releasePartial();

    inbox = false;

    // See if a notification has been played for this message...
    if (message.getNotify()) {
      
      // Indicate that we have already notified for this message
      message.resetNotify();

      // Reset the reminderCount to 0 just to be sure
      message.updateReminderCount(0);

      // Schedule a reminder notification
      ReminderReceiver.scheduleReminder(getApplicationContext(), message);

      // Run the notification
      ManageNotification.show(getApplicationContext(), message);
    }
  }

  /*
   * Create Dialog
   */
  @Override
  protected Dialog onCreateDialog(int id) {
    if (Log.DEBUG) Log.v("onCreateDialog()");

    switch (id) {
      /*
       * Delete message dialog
       */
      case DIALOG_DELETE:
        return new AlertDialog.Builder(this)
        .setIcon(android.R.drawable.ic_dialog_alert)
        .setTitle(getString(R.string.pref_show_delete_button_dialog_title))
        .setMessage(getString(R.string.pref_show_delete_button_dialog_text))
        .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
          public void onClick(DialogInterface dialog, int whichButton) {
//           This will need to do something someday, but for now deleting msg is a NOOP            
//            deleteMessage();
          }
        })
        .setNegativeButton(android.R.string.cancel, null)
        .create();


        /*
         * Loading Dialog
         */
      case DIALOG_LOADING:
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.loading_message));
        mProgressDialog.setIndeterminate(true);
        mProgressDialog.setCancelable(true);
        return mProgressDialog;
    }

    return null;
  }

  @Override
  protected void onPrepareDialog(int id, Dialog dialog) {
    super.onPrepareDialog(id, dialog);

    if (Log.DEBUG) Log.v("onPrepareDialog()");
    // User interacted so remove all locks and cancel reminders
    ClearAllReceiver.removeCancel(getApplicationContext());
    ClearAllReceiver.clearAll(false);
    ReminderReceiver.cancelReminder(getApplicationContext());

    switch (id) {
      case DIALOG_QUICKREPLY:
        showSoftKeyboard(qrEditText);

        // Set width of dialog to fill_parent
        LayoutParams mLP = dialog.getWindow().getAttributes();

        // TODO: this should be limited in case the screen is large
        mLP.width = LayoutParams.FILL_PARENT;
        dialog.getWindow().setAttributes(mLP);
        break;

      case DIALOG_PRESET_MSG:
        break;
    }
  }

  /*
   * Create Context Menu (Long-press menu)
   */
  @Override
  public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
    super.onCreateContextMenu(menu, v, menuInfo);

    menu.add(Menu.NONE, CONTEXT_VIEWCONTACT_ID, Menu.NONE, getString(R.string.view_contact));
    menu.add(Menu.NONE, CONTEXT_CLOSE_ID, Menu.NONE, getString(R.string.button_close));
    menu.add(Menu.NONE, CONTEXT_DELETE_ID, Menu.NONE, getString(R.string.button_delete));
    menu.add(Menu.NONE, CONTEXT_REPLY_ID, Menu.NONE, getString(R.string.button_reply));
    menu.add(Menu.NONE, CONTEXT_QUICKREPLY_ID, Menu.NONE, getString(R.string.button_quickreply));
    menu.add(Menu.NONE, CONTEXT_TTS_ID, Menu.NONE, getString(R.string.button_tts));
    menu.add(Menu.NONE, CONTEXT_INBOX_ID, Menu.NONE, getString(R.string.button_inbox));
  }

  /*
   * Context Menu Item Selected
   */
//  @Override
//  public boolean onContextItemSelected(MenuItem item) {
//    switch (item.getItemId()) {
//      case CONTEXT_CLOSE_ID:
//        closeMessage();
//        break;
//      case CONTEXT_DELETE_ID:
//        showDialog(DIALOG_DELETE);
//        break;
//      case CONTEXT_INBOX_ID:
//        gotoInbox();
//        break;
//      case CONTEXT_TTS_ID:
//        speakMessage();
//        break;
//    }
//    return super.onContextItemSelected(item);
//  }

  /*
   * Handle the results from the recognition activity.
   */
  @Override
  protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    super.onActivityResult(requestCode, resultCode, data);
    if (Log.DEBUG) Log.v("onActivityResult");
    if (requestCode == VOICE_RECOGNITION_REQUEST_CODE && resultCode == RESULT_OK) {
      ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
      if (Log.DEBUG) Log.v("Voice recog text: " + matches.get(0));
      //quickReply(matches.get(0));
    }
  }

  // The eyes-free text-to-speech library InitListener
  private final TTS.InitListener eyesFreeTtsListener = new InitListener() {
    public void onInit(int version) {
      if (mProgressDialog != null) {
        mProgressDialog.dismiss();
      }
      ttsInitialized = true;
      speakMessage();
    }
  };

  // The Android text-to-speech library OnInitListener (via wrapper class)
  private final TextToSpeechWrapper.OnInitListener androidTtsListener = new OnInitListener() {
    public void onInit(int status) {
      if (mProgressDialog != null) {
        mProgressDialog.dismiss();
      }
      if (status == TextToSpeechWrapper.SUCCESS) {
        ttsInitialized = true;
        speakMessage();
      } else {
        Toast.makeText(SmsPopupActivity.this, R.string.error_message, Toast.LENGTH_SHORT);
      }
    }
  };

  /*
   * Speak the message out loud using text-to-speech (either via Android text-to-speech or
   * via the free eyes-free text-to-speech library)
   */
  private void speakMessage() {
    // TODO: we should really require the keyguard be unlocked here if we are in privacy mode

    // If not previously initialized...
    if (!ttsInitialized) {

      // Show a loading dialog
      showDialog(DIALOG_LOADING);

      // User interacted so remove all locks and cancel reminders
      ClearAllReceiver.removeCancel(getApplicationContext());
      ClearAllReceiver.clearAll(false);
      ReminderReceiver.cancelReminder(getApplicationContext());

      // We'll use update notification to stop the sound playing
      ManageNotification.update(getApplicationContext(), message);

      if (androidTextToSpeechAvailable) {
        // Android text-to-speech available (normally found on Android 1.6+, aka Donut)
        androidTts = new TextToSpeechWrapper(SmsPopupActivity.this, androidTtsListener);
      } else { // Else use eyes-free text-to-speech library
        /*
         * This is an aweful fix for the loading dialog not disappearing
         * when the user decides to not install the TTS package but there didn't
         * seem like another way to hook into the current TTS library.
         * 
         * This will all go away once we can purely use the system TTS engine and do away
         * with the eyes-free version from Market.
         */
        // Extend TTS alert dialog so we can dismiss the loading dialog correctly
        class mTtsVersionAlert extends TTSVersionAlert {
          // Leaving this as hardcoded just as from the TTS source
          private final static String QUIT = "Do not install the TTS";
          mTtsVersionAlert(Context context) {
            super(context, null, null, null);
            setNegativeButton(QUIT, new DialogInterface.OnClickListener() {
              public void onClick(DialogInterface dialog, int which) {
                if (mProgressDialog != null) {
                  mProgressDialog.dismiss();
                }
              }
            });
            setOnCancelListener(new OnCancelListener() {
              public void onCancel(DialogInterface dialog) {
                if (mProgressDialog != null) {
                  mProgressDialog.dismiss();
                }
              }
            });
          }
        }

        // Init the eyes-free text-to-speech library
        eyesFreeTts = new TTS(this, eyesFreeTtsListener, new mTtsVersionAlert(this));
      }

    } else {

      // Speak the message!
      if (androidTextToSpeechAvailable) {
        androidTts.speak(message.getMessageBody(), TextToSpeechWrapper.QUEUE_FLUSH, null);
      } else {
        eyesFreeTts.speak(message.getMessageBody(), 0 /* no queue mode */, null);
      }
    }
  }

  /**
   * Close the message window/popup, mark the message read if the user has this option on
   */
  
  private void  mapMessage()  {
    if (Log.DEBUG) Log.v("Request Received to Map Call");
    if (haveNet()) {
        String searchStr = parser.getFullAddress();
        if (parser.getCity().length() > 0) searchStr = searchStr + ", " + parser.getCity();
        Intent intent = new Intent(Intent.ACTION_SEARCH);
        intent.setComponent(new ComponentName("com.google.android.apps.maps", "com.google.android.maps.MapsActivity"));
        intent.putExtra(SearchManager.QUERY, searchStr);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            Log.e("Could not find com.google.android.maps.Maps activity");
        }
        
    } else {
      if (Log.DEBUG) Log.v("Error: No Network Connection.");
      Dialog locationError = new AlertDialog.Builder(
          this).setIcon(0).setTitle("Error").setPositiveButton("Ok", null)
          .setMessage("Unable to Map Address due to Network Failure.")
          .create();
      locationError.show();
    }
  }
	
private boolean haveNet(){

    NetworkInfo info = (NetworkInfo) ((ConnectivityManager) this
            .getSystemService(Context.CONNECTIVITY_SERVICE)).getActiveNetworkInfo();

    if (info == null || !info.isConnected()) {
        return false;
    }
    if (info.isRoaming()) {
        // here is the roaming option you can change it if you want to
        // disable internet while roaming, just return false
        return false;
    }
    return true;

}
	
	
//  private void closeMessage() {
//    if (messageViewed) {
//      Intent i = new Intent(getApplicationContext(), SmsPopupUtilsService.class);
//      /*
//       * Switched back to mark messageId as read for >v1.0.6 (marking thread as read is slow
//       * for really large threads)
//       */
//      i.setAction(SmsPopupUtilsService.ACTION_MARK_MESSAGE_READ);
//      //i.setAction(SmsPopupUtilsService.ACTION_MARK_THREAD_READ);
//      message.addToIntent(i);
//      SmsPopupUtilsService.beginStartingService(getApplicationContext(), i);
//    }
//
//    // Finish up this activity
//    myFinish();
//  }

  /**
   * Reply to the current message, start the reply intent
   */
//  private void replyToMessage(final boolean replyToThread) {
//    exitingKeyguardSecurely = true;
//    ManageKeyguard.exitKeyguardSecurely(new LaunchOnKeyguardExit() {
//      public void LaunchOnKeyguardExitSuccess() {
//        Intent reply = message.getReplyIntent(replyToThread);
//        SmsPopupActivity.this.getApplicationContext().startActivity(reply);
//        replying = true;
//        myFinish();
//      }
//    });
//  }

//  private void replyToMessage() {
//    replyToMessage(true);
//  }

  /**
   * View the private message (this basically just unlocks the keyguard and then
   * reloads the activity).
   */
  private void viewMessage() {
    exitingKeyguardSecurely = true;
    ManageKeyguard.exitKeyguardSecurely(new LaunchOnKeyguardExit() {
      public void LaunchOnKeyguardExitSuccess() {
        // Yet another fix for the View button in privacy mode :(
        // This will remotely call refreshPrivacy in case the user doesn't have
        // the security pattern on (so the screen will not refresh and therefore
        // the popup will not come out of privacy mode)
        runOnUiThread(new Runnable() {
          public void run() {
            refreshPrivacy();
          }
        });
      }
    });
  }

  /**
   * Take the user to the messaging app inbox
   */
//  private void gotoInbox() {
//    exitingKeyguardSecurely = true;
//    ManageKeyguard.exitKeyguardSecurely(new LaunchOnKeyguardExit() {
//      public void LaunchOnKeyguardExitSuccess() {
//        Intent i = SmsPopupUtils.getSmsInboxIntent();
//        SmsPopupActivity.this.getApplicationContext().startActivity(i);
//        inbox = true;
//        myFinish();
//      }
//    });
//  }

  /**
   * Delete the current message from the system database
   */
//  private void deleteMessage() {
//    Intent i =
//      new Intent(SmsPopupActivity.this.getApplicationContext(), SmsPopupUtilsService.class);
//    i.setAction(SmsPopupUtilsService.ACTION_DELETE_MESSAGE);
//    message.addToIntent(i);
//    SmsPopupUtilsService.beginStartingService(SmsPopupActivity.this.getApplicationContext(), i);
//    myFinish();
//  }


  @Override
  public void onConfigurationChanged(Configuration newConfig) {
    super.onConfigurationChanged(newConfig);
    if (Log.DEBUG) Log.v("SMSPopupActivity: onConfigurationChanged()");
    resizeLayout();
  }

  private void resizeLayout() {
    // This sets the minimum width of the activity to a minimum of 80% of the screen
    // size only needed because the theme of this activity is "dialog" so it looks
    // like it's floating and doesn't seem to fill_parent like a regular activity
    if (mainLL == null) {
      mainLL = (LinearLayout) findViewById(R.id.MainLinearLayout);
    }
    Display d = getWindowManager().getDefaultDisplay();

    int width = d.getWidth() > MAX_WIDTH ? MAX_WIDTH : (int) (d.getWidth() * WIDTH);

    mainLL.setMinimumWidth(width);
    mainLL.invalidate();
  }
 
  /**
   * Show the soft keyboard and store the view that triggered it
   */
  private void showSoftKeyboard(View triggerView) {
    if (Log.DEBUG) Log.v("showSoftKeyboard()");
    if (inputManager == null) {
      inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }
    inputView = triggerView;
    inputManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
  }

  /**
   * Hide the soft keyboard
   */
  private void hideSoftKeyboard() {
    if (inputView == null) return;
    if (Log.DEBUG) Log.v("hideSoftKeyboard()");
    if (inputManager == null) {
      inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
    }
    inputManager.hideSoftInputFromWindow(inputView.getApplicationWindowToken(), 0);
    inputView = null;
  }

 

 
    
  }

