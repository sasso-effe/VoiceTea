package com.example.voicetuner.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.Button;
import android.widget.ProgressBar;

import com.example.voicetuner.DonationListener;
import com.example.voicetuner.R;
import com.example.voicetuner.internet.EarningsGetter;
import com.google.android.gms.ads.AdView;

public class AboutActivity extends SideActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initProgressBar();
    }

    private void initProgressBar() {
        ProgressBar myProgressBar = findViewById(R.id.progressBar);
        new EarningsGetter(myProgressBar);
    }

    private void initButton() {
        Button myButton = findViewById(R.id.button);
        myButton.setOnClickListener(new DonationListener());

    }

    @Override
    public String getActivityName() {
        return getString(R.string.about_us);
    }

    @Override
    public Toolbar getToolbar() {
        return findViewById(R.id.toolbar_about);
    }

    @Override
    public AdView getAdView() {
        return findViewById(R.id.adView_about);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }
}
