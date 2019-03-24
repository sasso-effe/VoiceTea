package com.truebeans.voicetuner.activity;

import android.support.v7.widget.Toolbar;

import com.truebeans.voicetuner.R;
import com.google.android.gms.ads.AdView;

public class HelpActivity extends SideActivity {
    @Override
    public String getActivityName() {
        return getString(R.string.help);
    }

    @Override
    public Toolbar getToolbar() {
        return findViewById(R.id.toolbar_help);
    }

    @Override
    public AdView getAdView() {
        return findViewById(R.id.adView_help);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_help;
    }
}
