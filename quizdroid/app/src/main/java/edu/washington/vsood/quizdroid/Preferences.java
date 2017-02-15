package edu.washington.vsood.quizdroid;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by vishesh on 2/12/17.
 */

public class Preferences extends PreferenceActivity implements SharedPreferences.OnSharedPreferenceChangeListener {

    @SuppressWarnings("deprecation")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.pref);

    }


    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences,
                                          String key) {
        if(key.equals("location")){
            Intent service = new Intent(Preferences.this, ObtainRepository.class);
            startService(service);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                finish();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
