package com.tharavadinnov.menusample;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

/** */
public class SettingsFragement extends PreferenceFragmentCompat {
    @Override
    public void onCreatePreferences(Bundle bundle, String s) {
        addPreferencesFromResource(R.xml.settings_prefs); // create pref screen from the xml file
    }
}
