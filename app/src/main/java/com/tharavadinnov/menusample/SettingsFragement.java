package com.tharavadinnov.menusample;

import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.EditTextPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

/**
 * Add pref from res - > <addPreferencesFromResource(R.xml.settings_prefs)/>
 * Note : If you not using list prefs the code inside here is useless except add prefs from res
 */
public class SettingsFragement extends PreferenceFragmentCompat implements OnSharedPreferenceChangeListener {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings_prefs); // create pref screen from the xml file

        /**List and EditText prefs starts here*/


        /**Reference to shared prefs */
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        PreferenceScreen prefScreen = getPreferenceScreen();
        int count = prefScreen.getPreferenceCount(); // get count of total number of prefs

        // iterate through all shared prefs
        for (int i = 0; i < count; i++) {
            Preference prefs = prefScreen.getPreference(i);
            if (!(prefs instanceof CheckBoxPreference)) { // checking whether its a checkBox prefs or not
                doIfNotListOrEdittextPrefs(sharedPreferences, prefs);
            }
        }
    }

    /**
     * setting up of preference summary on xml
     */
    private void setPreferenceSummary(Preference prefs, String value) {
        //List prefs
        if (prefs instanceof ListPreference) {
            ListPreference listPreference = (ListPreference) prefs;
            int prefsIndex = listPreference.findIndexOfValue(value);//Find index of selected list prefs
            if (prefsIndex >= 0) {
                listPreference.setSummary(listPreference.getEntries()[prefsIndex]); // Get the label of each index of list prefs
            }
        }
        //Edit text prefs
        else if (prefs instanceof EditTextPreference) {
            prefs.setSummary(value);
        }
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        Preference preference = findPreference(key);
        if (null != preference) {
            if (!(preference instanceof CheckBoxPreference)) {
                doIfNotListOrEdittextPrefs(sharedPreferences, preference);
            }
        }
    }

    //function to do if not its a list or edit text prefs
    private void doIfNotListOrEdittextPrefs(SharedPreferences sharedPreferences, Preference prefs) {
        String value = sharedPreferences.getString(prefs.getKey(), "");
        setPreferenceSummary(prefs, value);
    }

    // for registerOnSharedPreferenceChangeListener
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getPreferenceScreen().getSharedPreferences()
                .registerOnSharedPreferenceChangeListener(this);
    }

    // for unregisterOnSharedPreferenceChangeListener
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        getPreferenceScreen().getSharedPreferences()
                .unregisterOnSharedPreferenceChangeListener(this);
    }
    /**List prefs ends here*/
}
