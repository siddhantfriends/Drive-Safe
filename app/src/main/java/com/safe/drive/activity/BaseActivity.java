package com.safe.drive.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Milton on 30/04/2016.
 */
public class BaseActivity extends AppCompatActivity {
    protected final String ARG_SHARED_PREFERENCES = "SharedPreferences";
    protected final String ARG_PLATE_NUMBER = "PlateNumber";
    protected final String ARG_ENGINE_NUMBER = "EngineNumber";
    protected final String ARG_CURRENT_SPEED = "CurrentSpeed";
    protected final String ARG_SPEED_LIMIT = "SpeedLimit";
    protected SharedPreferences mSharedPreferences;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSharedPreferences = getSharedPreferences(ARG_SHARED_PREFERENCES, MODE_PRIVATE);
    }
}