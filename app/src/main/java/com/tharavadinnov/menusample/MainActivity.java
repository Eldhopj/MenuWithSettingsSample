package com.tharavadinnov.menusample;
/**
 * Create a menu
 * Creating settings activity
 * Necessary changes in Manifest (check commented parts)
 * Create a fragment class which extends PreferenceFragmentCompat (SettingsFragment.class)
 * Create an xml folder inside that folder -> create an xml for preference screen ( settings_prefs.xml )
 * Convert activity_settings into fragment
 * Styles -> Add a theme for preference screen
 * Add dependency for settings preference
 *NOTE : we wont directly write into shared prefs its handled by the system in background
 * Implement <SharedPreferences.OnSharedPreferenceChangeListener/> on the activity which needed to be implemented (here its MainActivity)
 * Note: EditText validation check Lesson 6:28 <https://classroom.udacity.com/courses/ud851/lessons/1392b674-18b6-4636-b36b-da7d37a319e3/concepts/7156d056-641e-491c-9b86-49f5310de0b0></https://classroom.udacity.com/courses/ud851/lessons/1392b674-18b6-4636-b36b-da7d37a319e3/concepts/7156d056-641e-491c-9b86-49f5310de0b0>
 * */

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements SharedPreferences.OnSharedPreferenceChangeListener {
    TextView bass;
    TextView color;
    TextView editText;
    SharedPreferences sharedPreferences; // Global variable of sharedPrefs

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bass = findViewById(R.id.booleanBass);
        color = findViewById(R.id.colorSelection);
        editText = findViewById(R.id.editText);

        showSettingsValue(); // function to retrieve values of settings
    }

    /**inflating the menu*/
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true; // or super.onCreateOptionsMenu(menu)
    }

    /**Setting up onclick listener*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) { //item -> gives which item is selected

        switch (item.getItemId()){

            case R.id.android :
                Toast.makeText(this, "Android selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.settings :
                Intent intent = new Intent(this,SettingsActivity.class);
                startActivity(intent);
                return true;
            case R.id.item2 :
                Toast.makeText(this, "item2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subItem1 :
                Toast.makeText(this, "subItem2 selected", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.subItem2 :
                Toast.makeText(this, "subItem2 selected", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Getting values of settings and registerOnSharedPreferenceChangeListener
     */
    // This function is to register the onShared change listener and to show the pref value on onCreate
    private void showSettingsValue() {
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        //Setting up values on textView
        bass.setText(String.valueOf(sharedPreferences.getBoolean("show_bass", true)));
        color.setText(sharedPreferences.getString("colors", "Red"));
        editText.setText(sharedPreferences.getString("value", "Eldho"));

        sharedPreferences.registerOnSharedPreferenceChangeListener(this); // registering onShared change listener
    }

    /**
     * Looking for changes in the shared prefs
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        //when change happen in pref it will automatically update the textView
        switch (key) {
            case "show_bass":
                bass.setText(String.valueOf(sharedPreferences.getBoolean(key, true)));
                break;
            case "colors":
                color.setText(sharedPreferences.getString(key, "Red"));
                break;
            case "value":
                editText.setText(sharedPreferences.getString(key, "Eldho"));
                break;
        }
    }

    /**
     * Un registering the SharedPreferences
     */
    @Override
    protected void onDestroy() {
        sharedPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }
}
