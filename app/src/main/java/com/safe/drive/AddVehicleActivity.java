package com.safe.drive;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddVehicleActivity extends AppCompatActivity implements View.OnClickListener {
    private final String ARG_SHARED_PREFERENCES = "SharedPreferences";
    private final String ARG_PLATE_NUMBER = "PlateNumber";
    private final String ARG_ENGINE_NUMBER = "EngineNumber";

    private EditText mPlateNumberEditText;
    private EditText mEngineNumberEditText;

    private SharedPreferences mSharedPreferences;

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

        mSharedPreferences = getSharedPreferences(ARG_SHARED_PREFERENCES, MODE_PRIVATE);

        String plateNumber = mSharedPreferences.getString(ARG_PLATE_NUMBER, "");
        if (!isNullOrEmpty(plateNumber)) {
            mPlateNumberEditText.setText(plateNumber);
        }

        String engineNumber = mSharedPreferences.getString(ARG_ENGINE_NUMBER, "");
        if (!isNullOrEmpty(engineNumber)) {
            mEngineNumberEditText.setText(engineNumber);
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

        finish();
    }

    private boolean isNullOrEmpty(CharSequence charSequence) {
        return charSequence == null || charSequence.toString().isEmpty();
    }
}