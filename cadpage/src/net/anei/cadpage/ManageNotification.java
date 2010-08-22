package net.anei.cadpage;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import net.anei.cadpage.ManagePreferences.Defaults;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;

/*
 * This class handles the Notifications (sounds/vibrate/LED)
 */
public class ManageNotification {
  public static final int NOTIFICATION_ALERT = 1337;
  public static final int NOTIFICATION_TEST = 1337;
  public static final int NOTIFICATION_SEND_FAILED = 100;
  public static final String defaultRingtone = Settings.System.DEFAULT_NOTIFICATION_URI.toString();
  private static final Uri UNDELIVERED_URI = Uri.parse("content://mms-sms/undelivered");

  /*
   * Class to hold the popup notification elements
   */
  static class PopupNotification {
    public Notification notification;
    public boolean privacyMode;
    public boolean replyToThread;

    PopupNotification(Notification n) {
      this.notification = n;
    }

    final public void notify(Context context, int notif) {
      NotificationManager myNM =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

      // Seems this is needed for the number value to take effect on the Notification
      myNM.cancel(notif);

      if (Log.DEBUG) Log.v("*** Notify running ***");
      myNM.notify(notif, notification);
    }
  }

  /*
   * Show/play the notification given a SmsMmsMessage and a notification ID
   * (really just NOTIFICATION_ALERT for the main alert and NOTIFICATION_TEST
   * for the test notification from the preferences screen)
   */
  public static void show(Context context, SmsMmsMessage message, int notif) {
    notify(context, message, false, notif);
  }

  /*
   * Default to NOTIFICATION_ALERT if notif is left out
   */
  public static void show(Context context, SmsMmsMessage message) {
    notify(context, message, false, NOTIFICATION_ALERT);
  }

  /*
   * Only update the notification given the SmsMmsMessage (ie. do not play the
   * vibrate/sound, just update the text).
   */
  public static void update(Context context, SmsMmsMessage message) {
    // TODO: can I just use Notification.setLatestEventInfo() to update instead?

    if (message != null) {
      if (message.getUnreadCount() > 0) {
        notify(context, message, true, NOTIFICATION_ALERT);
        return;
      }
    }

    // TODO: Should reply flag be set to true?
    ManageNotification.clearAll(context, true);
  }

  /*
   * The main notify method
   */
  private static void notify(Context context, SmsMmsMessage message, boolean onlyUpdate, int notif) {

    // Fetch info from the message object
    int unreadCount = message.getUnreadCount();
    String messageBody = message.getMessageBody();
    String contactName = message.getContactName();
    long timestamp = message.getTimestamp();

    // Check if there are unread messages - if not, we're done :)
    //if (unreadCount < 1) {
    //  return;
    //}

    PopupNotification n = buildNotification(context, message.getContactId(), onlyUpdate, notif);

    if (n == null) return;

    // The notification title, sub-text and text that will scroll
    String contentTitle;
    String contentText;
    SpannableString scrollText;

    // If we're updating the notification, do not set the ticker text
    if (onlyUpdate) {
      scrollText = null;
    } else {
      /*
       *  This service runs a content observer on the system sms db to help clear the notification
       *  icon in the case the user reads the messages outside of sms popup.  the service will be
       *  stopped when unread messages = 0
       */
      SmsMonitorService.beginStartingService(context);

      // If we're in privacy mode and the keyguard is on then just display
      // the name of the person, otherwise scroll the name and message
      if (n.privacyMode && ManageKeyguard.inKeyguardRestrictedInputMode()) {
        scrollText =
          new SpannableString(context.getString(R.string.notification_scroll_privacy, contactName));
      } else {
        scrollText =
          new SpannableString(context.getString(R.string.notification_scroll, contactName,
              messageBody));

        // Set contact name as bold
        scrollText.setSpan(new StyleSpan(Typeface.BOLD), 0, contactName.length(),
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
      }
    }

    // The default intent when the notification is clicked (Inbox)
    Intent smsIntent = SmsPopupUtils.getSmsInboxIntent();

    // If more than one message waiting ...
    if (unreadCount > 1) {
      contentTitle = context.getString(R.string.notification_multiple_title);
      contentText = context.getString(R.string.notification_multiple_text, unreadCount);
      // smsIntent = SMSPopupUtils.getSmsIntent();
    } else { // Else 1 message, set text and intent accordingly
      //contentTitle = contactName;
      contentTitle = "Alert";
      contentText = messageBody;
      smsIntent = message.getReplyIntent(n.replyToThread);
    }

    /*
     * Ok, let's create our Notification object and set up all its parameters.
     */

    // Set the icon, scrolling text and timestamp
    n.notification.icon = R.drawable.stat_notify_sms;
    n.notification.tickerText = scrollText;
    n.notification.when = timestamp;

    //  Notification notification =
    //  new Notification(R.drawable.stat_notify_sms, scrollText, timestamp);

    // If in a call, start the media player
    //    if (callState == TelephonyManager.CALL_STATE_OFFHOOK
    //        && AM.getRingerMode() == AudioManager.RINGER_MODE_NORMAL) {
    //
    //      // TODO: make this an option
   // Uri alarmSoundURI=Uri.parse("file:///sdcard/media/audio/notifications/generalquarter.wav");
   //   if (Log.DEBUG) Log.v("running own mediaplayer");
   //     MediaPlayer mMediaPlayer = MediaPlayer.create(context, alarmSoundURI);
   //
   //    mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
   //     mMediaPlayer.setLooping(false);
   //     mMediaPlayer.setVolume(1, 1);
   //      if (Log.DEBUG) Log.v("Starting Media Player Sound");
   //     mMediaPlayer.start();
    //   }

    // Set the PendingIntent if the status message is clicked
    PendingIntent notifIntent = PendingIntent.getActivity(context, 0, smsIntent, 0);

    // Set the messages that show when the status bar is pulled down
    n.notification.setLatestEventInfo(context, contentTitle, contentText, notifIntent);

    // Set number of events that this notification signifies (unread messages)
    if (unreadCount > 1) {
      n.notification.number = unreadCount;
    }

    n.notify(context, notif);
  }

  /*
   * Build the notification from user preferences
   */
  private static PopupNotification buildNotification(Context context, String contactId,
      boolean onlyUpdate, int notif) {

    ManagePreferences mPrefs = new ManagePreferences(context, contactId);
    AudioManager AM = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);

    // Check if notifications are enabled - if not, we're done :)
    if (!mPrefs.getBoolean(
        R.string.pref_notif_enabled_key,
        Defaults.PREFS_NOTIF_ENABLED,
        SmsPopupDbAdapter.KEY_ENABLED_NUM)) {
      return null;
    }

    // Get some preferences: vibrate and vibrate_pattern prefs
    boolean vibrate =
      mPrefs.getBoolean(R.string.pref_vibrate_key, Defaults.PREFS_VIBRATE_ENABLED,
          SmsPopupDbAdapter.KEY_VIBRATE_ENABLED_NUM);

    String vibrate_pattern_raw =
      mPrefs.getString(R.string.pref_vibrate_pattern_key,
          Defaults.PREFS_VIBRATE_PATTERN, SmsPopupDbAdapter.KEY_VIBRATE_PATTERN_NUM);

    String vibrate_pattern_custom_raw =
      mPrefs.getString(R.string.pref_vibrate_pattern_custom_key,
          Defaults.PREFS_VIBRATE_PATTERN,
          SmsPopupDbAdapter.KEY_VIBRATE_PATTERN_CUSTOM_NUM);

    // Get LED preferences
    boolean flashLed =
      mPrefs.getBoolean(R.string.pref_flashled_key, Defaults.PREFS_LED_ENABLED,
          SmsPopupDbAdapter.KEY_LED_ENABLED_NUM);

    String flashLedCol =
      mPrefs.getString(R.string.pref_flashled_color_key, Defaults.PREFS_LED_COLOR,
          SmsPopupDbAdapter.KEY_LED_COLOR_NUM);

    String flashLedColCustom =
      mPrefs.getString(R.string.pref_flashled_color_custom_key,
          Defaults.PREFS_LED_COLOR, SmsPopupDbAdapter.KEY_LED_COLOR_CUSTOM_NUM);

    String flashLedPattern =
      mPrefs.getString(R.string.pref_flashled_pattern_key,
          Defaults.PREFS_LED_PATTERN, SmsPopupDbAdapter.KEY_LED_PATTERN_NUM);

    String flashLedPatternCustom =
      mPrefs.getString(R.string.pref_flashled_pattern_custom_key,
          Defaults.PREFS_LED_PATTERN, SmsPopupDbAdapter.KEY_LED_PATTERN_CUSTOM_NUM);

    // Try and parse the user ringtone, use the default if it fails
    //Uri alarmSoundURI =
    //  Uri.parse(mPrefs.getString(R.string.pref_notif_sound_key, defaultRingtone,
    //      SmsPopupDbAdapter.KEY_RINGTONE_NUM));
    Uri alarmSoundURI=Uri.parse("file:///sdcard/media/audio/notifications/generalquarter.wav");
    if (Log.DEBUG) Log.v("Sounds URI = " + alarmSoundURI.toString());

    // See if user wants some privacy
    boolean privacyMode =
      mPrefs.getBoolean(R.string.pref_privacy_key, Defaults.PREFS_PRIVACY);

    boolean replyToThread =
      mPrefs.getBoolean(R.string.pref_reply_to_thread_key, Defaults.PREFS_REPLY_TO_THREAD);

    // All done with prefs, close it up
    mPrefs.close();

    /*
     * Ok, let's create our Notification object and set up all its parameters.
     */
    Notification notification = new Notification();

    // Set auto-cancel flag
    notification.flags = Notification.FLAG_AUTO_CANCEL;

    // Set audio stream to ring
    notification.audioStreamType = Notification.STREAM_DEFAULT;

    /*
     * If this is a new notification (not updating a notification) then set
     * LED, vibrate and ringtone to fire
     */
    if (!onlyUpdate) {

      // Set up LED pattern and color
      if (flashLed) {
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;

        /*
         * Set up LED blinking pattern
         */
        int[] led_pattern = null;

        if (context.getString(R.string.pref_custom_val).equals(flashLedPattern)) {
          led_pattern = parseLEDPattern(flashLedPatternCustom);
        } else {
          led_pattern = parseLEDPattern(flashLedPattern);
        }

        // Set to default if there was a problem
        if (led_pattern == null) {
          led_pattern =
            parseLEDPattern(context.getString(R.string.pref_flashled_pattern_default));
        }

        notification.ledOnMS = led_pattern[0];
        notification.ledOffMS = led_pattern[1];

        /*
         * Set up LED color
         */
        // Check if a custom color is set
        if (context.getString(R.string.pref_custom_val).equals(flashLedCol)) {
          flashLedCol = flashLedColCustom;
        }

        // Default in case the parse fails
        int col = Color.parseColor(context.getString(R.string.pref_flashled_color_default));

        // Try and parse the color
        if (flashLedCol != null) {
          try {
            col = Color.parseColor(flashLedCol);
          } catch (IllegalArgumentException e) {
            // No need to do anything here
          }
        }

        notification.ledARGB = col;
      }

      /*
       * Set up vibrate pattern
       */
      // If vibrate is ON, or if phone is set to vibrate
      if ((vibrate || AudioManager.RINGER_MODE_VIBRATE == AM.getRingerMode())) {
        long[] vibrate_pattern = null;
        if (context.getString(R.string.pref_custom_val).equals(vibrate_pattern_raw)) {
          vibrate_pattern = parseVibratePattern(vibrate_pattern_custom_raw);
        } else {
          vibrate_pattern = parseVibratePattern(vibrate_pattern_raw);
        }
        if (vibrate_pattern != null) {
          notification.vibrate = vibrate_pattern;
        } else {
          notification.defaults = Notification.DEFAULT_VIBRATE;
        }
      }

     //notification.sound = alarmSoundURI;
     
     //notification.sound = Uri.parse("android.resource://net.anei.cadpage/res/raw/alert.wav");
     //notification.sound = 
      //Uri alarmSoundURI=Uri.parse("file:///sdcard/media/audio/notifications/generalquarter.wav");
      if (Log.DEBUG) Log.v("running own mediaplayer");
      MediaPlayer mMediaPlayer = MediaPlayer.create(context, alarmSoundURI);
      mMediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
      mMediaPlayer.setLooping(false);
      mMediaPlayer.setVolume(1, 1);
      if (Log.DEBUG) Log.v("Starting Media Player Sound");
      mMediaPlayer.start();
      
    }

    // Set intent to execute if the "clear all" notifications button is pressed -
    // basically stop any future reminders.
    Intent deleteIntent = new Intent(new Intent(context, ReminderReceiver.class));
    deleteIntent.setAction(Intent.ACTION_DELETE);
    PendingIntent pendingDeleteIntent = PendingIntent.getBroadcast(context, 0, deleteIntent, 0);

    notification.deleteIntent = pendingDeleteIntent;

    PopupNotification popupNotification = new PopupNotification(notification);
    popupNotification.replyToThread = replyToThread;
    popupNotification.privacyMode = privacyMode;

    return popupNotification;
  }

  // Clear the standard notification alert
  public static void clear(Context context) {
    clear(context, NOTIFICATION_ALERT);
  }

  // Clear a single notification
  public static void clear(Context context, int notif) {
    NotificationManager myNM =
      (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
    myNM.cancel(notif);
  }

  /*
   * Build the notification with no contactId
   */
  private static PopupNotification buildNotification(Context context,
      boolean onlyUpdate, int notif) {
    return buildNotification(context, "0", onlyUpdate, notif);
  }

  public static void clearAll(Context context, boolean reply) {
    SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(context);

    if (reply
        || myPrefs.getBoolean(context.getString(R.string.pref_markread_key),
            Boolean.parseBoolean(context.getString(R.string.pref_markread_default)))) {
      NotificationManager myNM =
        (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
      myNM.cancelAll();
    }
  }

  public static void clearAll(Context context) {
    clearAll(context, true);
  }

  /**
   * Parse the user provided custom vibrate pattern into a long[]
   * 
   */
  // TODO: tidy this up
  public static long[] parseVibratePattern(String stringPattern) {
    ArrayList<Long> arrayListPattern = new ArrayList<Long>();
    Long l;

    if (stringPattern == null) return null;

    String[] splitPattern = stringPattern.split(",");
    int VIBRATE_PATTERN_MAX_SECONDS = 60000;
    int VIBRATE_PATTERN_MAX_PATTERN = 100;

    for (int i = 0; i < splitPattern.length; i++) {
      try {
        l = Long.parseLong(splitPattern[i].trim());
      } catch (NumberFormatException e) {
        return null;
      }
      if (l > VIBRATE_PATTERN_MAX_SECONDS) {
        return null;
      }
      arrayListPattern.add(l);
    }

    // TODO: can i just cast the whole ArrayList into long[]?
    int size = arrayListPattern.size();
    if (size > 0 && size < VIBRATE_PATTERN_MAX_PATTERN) {
      long[] pattern = new long[size];
      for (int i = 0; i < pattern.length; i++) {
        pattern[i] = arrayListPattern.get(i);
      }
      return pattern;
    }

    return null;
  }

  /**
   * Parse LED pattern string into int[]
   * 
   * @param stringPattern
   * @return
   */
  public static int[] parseLEDPattern(String stringPattern) {
    int[] arrayPattern = new int[2];
    int on, off;

    if (stringPattern == null) return null;

    String[] splitPattern = stringPattern.split(",");

    if (splitPattern.length != 2) return null;

    int LED_PATTERN_MIN_SECONDS = 0;
    int LED_PATTERN_MAX_SECONDS = 60000;

    try {
      on = Integer.parseInt(splitPattern[0]);
    } catch (NumberFormatException e) {
      return null;
    }

    try {
      off = Integer.parseInt(splitPattern[1]);
    } catch (NumberFormatException e) {
      return null;
    }

    if (on >= LED_PATTERN_MIN_SECONDS && on <= LED_PATTERN_MAX_SECONDS
        && off >= LED_PATTERN_MIN_SECONDS && off <= LED_PATTERN_MAX_SECONDS) {
      arrayPattern[0] = on;
      arrayPattern[1] = off;
      return arrayPattern;
    }

    return null;
  }

  public static void notifySendFailed(Context context) {
    PopupNotification n = buildNotification(context, false, NOTIFICATION_SEND_FAILED);
    if (n == null) return;

    // The notification title, sub-text and text that will scroll
    String contentTitle;
    String contentText;
    //SpannableString scrollText;

    long[] threadIdResult = {0};
    int failedCount = getUndeliveredMessageCount(context, threadIdResult);

    // The default intent when the notification is clicked (Inbox)
    Intent smsIntent = SmsPopupUtils.getSmsInboxIntent();

    // If more than one message failed
    if (failedCount > 1) {
      contentTitle = "Multiple errors";
      contentText = "Multiple errors when sending messages";
      // smsIntent = SMSPopupUtils.getSmsIntent();
    } else { // Else 1 message failed
      contentTitle = "Error sending message";
      contentText = "Error sending message";
      long threadId = (threadIdResult[0] != 0 ? threadIdResult[0] : 0);
      smsIntent = SmsPopupUtils.getSmsToIntent(context, threadId);
    }

    // Set the icon, scrolling text and timestamp
    n.notification.icon = R.drawable.stat_notify_sms_failed;
    n.notification.tickerText = contentTitle;
    n.notification.when = System.currentTimeMillis();

    // Set the PendingIntent if the status message is clicked
    PendingIntent notifIntent = PendingIntent.getActivity(context, 0, smsIntent, 0);

    // Set the messages that show when the status bar is pulled down
    n.notification.setLatestEventInfo(context, contentTitle, contentText, notifIntent);

    n.notify(context, NOTIFICATION_SEND_FAILED);
  }

  // threadIdResult[0] contains the thread id of the first message.
  // threadIdResult[1] is nonzero if the thread ids of all the messages are the same.
  // You can pass in null for threadIdResult.
  // You can pass in a threadIdResult of size 1 to avoid the comparison of each thread id.
  private static int getUndeliveredMessageCount(Context context, long[] threadIdResult) {

    // TODO: switch projection to use common static variables
    Cursor undeliveredCursor = context.getContentResolver().query(
        UNDELIVERED_URI, new String[] { "thread_id" //Mms.THREAD_ID
        }, "read=0", null, null);
    if (undeliveredCursor == null) {
      return 0;
    }
    int count = undeliveredCursor.getCount();
    try {
      if (threadIdResult != null && undeliveredCursor.moveToFirst()) {
        threadIdResult[0] = undeliveredCursor.getLong(0);

        if (threadIdResult.length >= 2) {
          // Test to see if all the undelivered messages belong to the same thread.
          long firstId = threadIdResult[0];
          while (undeliveredCursor.moveToNext()) {
            if (undeliveredCursor.getLong(0) != firstId) {
              firstId = 0;
              break;
            }
          }
          threadIdResult[1] = firstId;    // non-zero if all ids are the same
        }
      }
    } finally {
      undeliveredCursor.close();
    }
    return count;
  }

  public static void updateSendFailedNotification(Context context) {
    if (getUndeliveredMessageCount(context, null) < 1) {
      clear(context, NOTIFICATION_SEND_FAILED);
    } else {
      notifySendFailed(context); // rebuild and adjust the message count if necessary.
    }
  }

}
