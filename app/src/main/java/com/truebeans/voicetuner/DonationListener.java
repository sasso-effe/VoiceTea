package com.truebeans.voicetuner;

import android.app.Activity;
import android.support.annotation.Nullable;
import android.view.View;
import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;

import java.util.List;

public class DonationListener implements View.OnClickListener, PurchasesUpdatedListener {
    private Activity myActivity;
    private BillingClient billingClient;


    @Override
    public void onClick(View v) {

    }


    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {

    }
}
