package com.truebeans.voicetuner.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.truebeans.voicetuner.R;
import com.google.android.gms.ads.AdView;

public class AboutActivity extends SideActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public String getActivityName() {
        return getString(R.string.about_us);
    }

    @Override
    public Toolbar getToolbar() {
        return findViewById(R.id.toolbar_donate);
    }

    @Override
    public AdView getAdView() {
        return findViewById(R.id.adView_donate);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_about;
    }
}
