package com.truebeans.voicetuner.activity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.truebeans.voicetuner.R;

import java.util.Objects;

public class OpenSourceActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_open_source);
        initToolBar();
        initTextViews();
    }

    private void initToolBar() {
        Toolbar myToolbar = findViewById(R.id.toolbar_open_source);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(R.string.title_activity_open_source);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @SuppressLint("SetTextI18n")
    private void initTextViews() {
        TextView graphViewLicense = findViewById(R.id.graphviewLicense);
        graphViewLicense.setText(getString(R.string.license_header1) + " " + getString(R.string.graphview) + getString(R.string.license_header2)
                + getString(R.string.graphView_license));

        TextView jTransformsLicense = findViewById(R.id.jTransformsLicense);
        jTransformsLicense.setText(getString(R.string.license_header1) + " " + getString(R.string.jtransforms) + getString(R.string.license_header2)
                + getString(R.string.jTranforms_license));

        TextView iapLicense = findViewById(R.id.iapLicense);
        iapLicense.setText(getString(R.string.license_header1) + " " + getString(R.string.gplay_iap_samples) + getString(R.string.license_header2)
                + getString(R.string.iap_license));
    }

}
