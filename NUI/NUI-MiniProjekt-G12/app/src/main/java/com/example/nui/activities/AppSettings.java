package com.example.nui.activities;


import android.preference.PreferenceFragment;
import androidx.annotation.Nullable;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;

import com.example.nui.R;
import com.example.nui.helpers.SettingsHelper;

public class AppSettings extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SettingsHelper.applyTheme(this);
        setContentView(R.layout.activity_app_settings);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        SettingsHelper.applyThemeToolbar((Toolbar)findViewById(R.id.toolbar),this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setTitle(getString(R.string.settings_title));
        getPrefFragment();
    }

    public static class AppPreference extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.app_preferences);
        }
    }

    //getting the setting fragment
    private void getPrefFragment(){
        FragmentManager fragmentManager=getFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.prefContainer,new AppPreference()).commit();
    }
}
