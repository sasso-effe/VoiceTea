package com.truebeans.voicetuner.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.truebeans.voicetuner.AdLoader;
import com.google.android.gms.ads.AdView;

public abstract class BannerActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        loadAds();
    }

    private void loadAds() {
        AdView mAdView = getAdView();
        AdLoader.loadAd(this, mAdView);
    }

    public abstract AdView getAdView();

    public abstract int getLayoutId();


}
