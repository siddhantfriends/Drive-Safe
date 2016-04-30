package com.safe.drive.activity;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.safe.drive.R;
import com.safe.drive.Vehicle;

public class ProfileActivity extends BaseActivity implements View.OnClickListener {
    private EditText mPlateNumberEditText;
    private EditText mEngineNumberEditText;
    private EditText mCurrentSpeedEditText;
    private EditText mSpeedLimitEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_vehicle);
        initialiseComponents();
    }

    private void initialiseComponents() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_action_close_white_24dp);
        }

        mPlateNumberEditText = (EditText) findViewById(R.id.plate_number_edit_text);
        mEngineNumberEditText = (EditText) findViewById(R.id.engine_number_edit_text);
        mCurrentSpeedEditText = (EditText) findViewById(R.id.current_speed_edit_text);
        mSpeedLimitEditText = (EditText) findViewById(R.id.speed_limit_edit_text);

        String plateNumber = mSharedPreferences.getString(ARG_PLATE_NUMBER, "");
        if (!isNullOrEmpty(plateNumber)) {
            mPlateNumberEditText.setText(plateNumber);
        }

        String engineNumber = mSharedPreferences.getString(ARG_ENGINE_NUMBER, "");
        if (!isNullOrEmpty(engineNumber)) {
            mEngineNumberEditText.setText(engineNumber);
        }

        int currentSpeed = mSharedPreferences.getInt(ARG_CURRENT_SPEED, Integer.MIN_VALUE);
        if (currentSpeed != Integer.MIN_VALUE) {
            mCurrentSpeedEditText.setText(String.valueOf(currentSpeed));
        }

        int speedLimit = mSharedPreferences.getInt(ARG_SPEED_LIMIT, Integer.MIN_VALUE);
        if (speedLimit != Integer.MIN_VALUE) {
            mSpeedLimitEditText.setText(String.valueOf(speedLimit));
        }

        Button saveButton = (Button) findViewById(R.id.save_button);
        saveButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (isNullOrEmpty(mPlateNumberEditText.getText()) || isNullOrEmpty(mPlateNumberEditText.getText())) {
            Snackbar.make(v, R.string.error_missing_fields, Snackbar.LENGTH_LONG).show();
        }

        Vehicle vehicle = new Vehicle(mPlateNumberEditText.getText().toString(),
                mEngineNumberEditText.getText().toString());
        mSharedPreferences.edit().putString(ARG_PLATE_NUMBER, vehicle.getPlateNumber()).apply();
        mSharedPreferences.edit().putString(ARG_ENGINE_NUMBER, vehicle.getEngineNumber()).apply();
        mSharedPreferences.edit().putInt(ARG_CURRENT_SPEED, Integer.parseInt(mCurrentSpeedEditText.getText().toString())).apply();
        mSharedPreferences.edit().putInt(ARG_SPEED_LIMIT, Integer.parseInt(mSpeedLimitEditText.getText().toString())).apply();

        finish();
    }

    private boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.toString().isEmpty();
    }
}