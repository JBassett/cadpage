package net.anei.cadpage;

import net.anei.cadpage.parsers.SmsMsgParser;
import net.anei.cadpage.preferences.AppEnabledCheckBoxPreference;
import net.anei.cadpage.preferences.DialogPreference;
import net.anei.cadpage.preferences.EditTextPreference;
import net.anei.cadpage.preferences.ListPreference;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.preference.PreferenceCategory;
import android.preference.PreferenceManager;
import android.preference.Preference.OnPreferenceChangeListener;
import android.preference.Preference.OnPreferenceClickListener;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SmsPopupConfigActivity extends PreferenceActivity {
  private static final int DIALOG_DONATE = Menu.FIRST;
  private Preference donateDialogPref = null;
  
  private String parserFilter = "";
  private CheckBoxPreference overrideFilterPref;
  private EditTextPreference filterPref;
  private CheckBoxPreference genAlertPref;

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    
    addPreferencesFromResource(R.xml.preferences);

    // Set preferences initialized flag
    SharedPreferences.Editor editor = 
        PreferenceManager.getDefaultSharedPreferences(this).edit();
    editor.putBoolean(getString(R.string.pref_initialized_key), true);
    editor.commit();

    // Set the version number in the about dialog preference
    final DialogPreference aboutPref =
      (DialogPreference) findPreference(getString(R.string.pref_about_key));
    aboutPref.setDialogTitle(SmsPopupUtils.getNameVersion(this));
    aboutPref.setDialogLayoutResource(R.layout.about);
    
    // The location, filter override checkbox, and filter edit box have a complex
    // relationship.  The override checkbox is enabled only when the location parser
    // has a default parser to override.  If it doesn't then it is disabled by forced
    // to true.  The filter is enabled when the override box is checked, whether it
    // is enabled or not.  We have to do this ourselves because the Android dependency
    // logic considers the value to be false if it isn't enabled.
    
    // On top of all that, the general alert box is enabled only if the current
    // parser has a default filter OR a user filter has been specified

    genAlertPref = (CheckBoxPreference)
        findPreference(getString(R.string.pref_gen_alert_key));
    filterPref = (EditTextPreference)findPreference(getString(R.string.pref_filter_key));
    filterPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
      @Override
      public boolean onPreferenceChange(Preference preference, Object newValue) {
        String filter = (String)newValue;
        genAlertPref.setEnabled(filter.length() > 1 || parserFilter.length() > 0);
        return true;
      }
    });

    overrideFilterPref = (CheckBoxPreference)
        findPreference(getString(R.string.pref_override_filter_key));
    filterPref.setEnabled(overrideFilterPref.isChecked());
    overrideFilterPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
      @Override
      public boolean onPreferenceChange(Preference preference, Object newValue) {
        filterPref.setEnabled((Boolean)newValue);
        return true;
      }
    });
    ListPreference locationPref = (ListPreference)
        findPreference(getString(R.string.pref_location_key));
    adjustLocationChange(locationPref.getValue(), false);
    locationPref.setOnPreferenceChangeListener(new OnPreferenceChangeListener(){
      @Override
      public boolean onPreferenceChange(Preference preference, Object newValue) {
        adjustLocationChange((String)newValue, true);
        return true;
      }
    });

    // Test message response
    Preference testmsgPref = findPreference(getString(R.string.pref_testmsg_key));
    testmsgPref.setOnPreferenceClickListener(new OnPreferenceClickListener(){
      @Override
      public boolean onPreferenceClick(Preference preference) {
        SmsReceiver.repeatLastPage(SmsPopupConfigActivity.this);
        return true;
      }});
    
    // Email developer response
    Preference emailPref = findPreference(getString(R.string.pref_email_key));
    emailPref.setOnPreferenceClickListener(new OnPreferenceClickListener(){
      @Override
      public boolean onPreferenceClick(Preference preference) {
        EmailDeveloperActivity.sendGeneralEmail(SmsPopupConfigActivity.this);
        return true;
      }});
    
    // Donate dialog preference
    donateDialogPref = findPreference(getString(R.string.pref_donate_key));
    if (donateDialogPref != null) {
      donateDialogPref.setOnPreferenceClickListener(new OnPreferenceClickListener() {
        public boolean onPreferenceClick(Preference preference) {
          SmsPopupConfigActivity.this.showDialog(DIALOG_DONATE);
          return true;
        }
      });
    }
  }

  /**
   * Make any necessary adjustments necessary
   * when the location preference is changed
   * @param location new location preference value
   * @param change true if location value has been changed
   */
  private void adjustLocationChange(String location, boolean change) {
    
    // Get the parser and see if it has a default filter
    // Save it in parserFilter so other preferences know what it is
    SmsMsgParser parser = ManageParsers.getInstance().getParser(location);
    parserFilter = parser.getFilter();
    
    // If the parser has a filter, enable the override checkbox, set its value to true
    // And insert the default filter value in the summary off message
    // And unilaterally enable the general alert box
    if (parserFilter.length() > 0) {
      overrideFilterPref.setEnabled(true);
      if (change) overrideFilterPref.setChecked(false);
      overrideFilterPref.setSummaryOff(getString(R.string.pref_override_filter_summaryoff, parserFilter));
      filterPref.setEnabled(overrideFilterPref.isChecked());
      genAlertPref.setEnabled(true);
    }
    
    // If there is no parser filter, the override box is disabled but forced to true
    // the general alert box is enabled only if the user filter
    else {
      overrideFilterPref.setEnabled(false);
      overrideFilterPref.setChecked(true);
      filterPref.setEnabled(true);
      String filter = filterPref.getText();
      genAlertPref.setEnabled(filter.length() > 1);
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    SharedPreferences myPrefs = PreferenceManager.getDefaultSharedPreferences(this);

    // Donate Dialog
    if (donateDialogPref != null) {
      boolean donated = myPrefs.getBoolean(this.getString(R.string.pref_donated_key), false);
      // boolean donated = true;
      if (donated) {
        PreferenceCategory otherPrefCategory =
          (PreferenceCategory) findPreference(getString(R.string.pref_other_key));
        otherPrefCategory.removePreference(donateDialogPref);
        donateDialogPref = null;
      }
    }

    /*
     * This is quite hacky - in case the app was enabled or disabled externally (by
     * ExternalEventReceiver) this will refresh the checkbox that is visible to the user
     */
    AppEnabledCheckBoxPreference mEnabledPreference =
      (AppEnabledCheckBoxPreference) findPreference(getString(R.string.pref_enabled_key));

    boolean enabled = myPrefs.getBoolean(getString(R.string.pref_enabled_key), true);
    mEnabledPreference.setChecked(enabled);
  }

  @Override
  protected Dialog onCreateDialog(int id) {
    switch (id) {

      case DIALOG_DONATE:
        LayoutInflater factory = getLayoutInflater();
        final View donateView = factory.inflate(R.layout.donate, null);

       
        Button donatePaypalButton = (Button) donateView.findViewById(R.id.DonatePaypalButton);
        donatePaypalButton.setOnClickListener(new OnClickListener() {
          public void onClick(View v) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(SmsPopupUtils.DONATE_PAYPAL_URI);
            SmsPopupConfigActivity.this.startActivity(i);
          }
        });

        return new AlertDialog.Builder(this)
        .setIcon(R.drawable.cadpage_icon)
        .setTitle(R.string.pref_donate_title)
        .setView(donateView)
        .setPositiveButton(android.R.string.ok, null)
        .create();
    }
    return super.onCreateDialog(id);
  }
  
  // If location code changes during this session, force a rebuild of
  // the call history data on the off chance that a general format message
  // can use the new location code.
  private String oldLocation = null;
  @Override
  protected void onStart() {
    oldLocation = ManagePreferences.location();
    super.onStart();
  }
  
  @Override
  protected void onStop() {
    super.onStop();
    String location = ManagePreferences.location();
    if (! location.equals(oldLocation)) {
      SmsMessageQueue.getInstance().notifyDataChange();
    }
  }
}