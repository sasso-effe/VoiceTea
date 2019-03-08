package com.example.voicetuner.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.example.voicetuner.Global;
import com.example.voicetuner.R;
import com.google.android.gms.ads.AdView;

public class SettingsActivity extends SideActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Spinner sampleRateSpinner = setSpinner(R.id.samplerate_spinner, R.array.samplerate_entries);
        sampleRateSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Global.setSampleRate(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        sampleRateSpinner.setSelection(Global.getSampleRateModeId());

        Spinner precisionSpinner = setSpinner(R.id.precision_spinner, R.array.precision_entries);
        precisionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Global.setBufferSize(position);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
        precisionSpinner.setSelection(Global.getPrecisionModeId());
    }

    private Spinner setSpinner(int spinnerId, int arrayId) {
        Spinner spinner = (Spinner) findViewById(spinnerId);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                arrayId, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        return spinner;
    }

    @Override
    public String getActivityName() {
        return getString(R.string.settings);
    }

    @Override
    public Toolbar getToolbar() {
        return findViewById(R.id.toolbar_settings);
    }

    @Override
    public AdView getAdView() {
        return findViewById(R.id.adView_settings);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_settings;
    }
}
