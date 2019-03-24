package com.truebeans.voicetuner;

import android.content.Context;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdLoader {

    public static void loadAd(Context context, AdView mAdView) {
        MobileAds.initialize(context, "ca-app-pub-8343076377545122~2378764836");
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("720C771F4D03D985B863C6D49FAE708A") //pietro
                .addTestDevice("07303EA481353FF5281793383FABF10A")// bernardo TODO: solo per il test, rimuovere questa stringa al lancio
                .build();
        mAdView.loadAd(adRequest);
    }
}
