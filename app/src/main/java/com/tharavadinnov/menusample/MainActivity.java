package com.tharavadinnov.menusample;
/**
 * New -> Android resource directory -> Menu
 * menu ->  menu resource file
 * Creating settings activity
 * Necessary changes in Manifest*/
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
    public boolean onOptionsItemSelected(MenuItem item) {
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
}
