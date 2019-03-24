package com.truebeans.voicetuner.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.android.billingclient.api.BillingClient;
import com.android.billingclient.api.BillingClientStateListener;
import com.android.billingclient.api.BillingFlowParams;
import com.android.billingclient.api.Purchase;
import com.android.billingclient.api.PurchasesUpdatedListener;
import com.android.billingclient.api.SkuDetails;
import com.android.billingclient.api.SkuDetailsParams;
import com.truebeans.voicetuner.billing.KeyConstructor;
import com.truebeans.voicetuner.R;
import com.truebeans.voicetuner.billing.Security;
import com.google.android.gms.ads.AdView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class PremiumActivity extends SideActivity implements PurchasesUpdatedListener {
    private BillingClient billingClient;
    private SkuDetails premiumDetails;
    private SkuDetails coffeeDetails;
    private Activity thisActivity = this;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initButtons();
        billingClient = BillingClient.newBuilder(thisActivity).setListener(this).build();
        billingClient.startConnection(new BillingClientStateListener() {
            @Override
            public void onBillingSetupFinished(@BillingClient.BillingResponse int billingResponseCode) {
                if (billingResponseCode == BillingClient.BillingResponse.OK) {
                    // The billing client is ready. You can query purchases here.
                    List<String> skuList = new ArrayList<>();
                    skuList.add("premium_upgrade");
                    skuList.add("coffee_upgrade");
                    SkuDetailsParams.Builder params = SkuDetailsParams.newBuilder();
                    params.setSkusList(skuList).setType(BillingClient.SkuType.INAPP);
                    billingClient.querySkuDetailsAsync(params.build(),
                            (responseCode, skuDetailsList) -> {
                                if (responseCode == BillingClient.BillingResponse.OK
                                        && skuDetailsList != null) {
                                    for (SkuDetails skuDetails : skuDetailsList) {
                                        String sku = skuDetails.getSku();
                                        String price = skuDetails.getPrice();
                                        if ("premium_upgrade".equals(sku)) {
                                            TextView premiumPrice = findViewById(R.id.premium_price);
                                            premiumPrice.setText(price);
                                            premiumDetails = skuDetails;

                                        } else if ("coffee_upgrade".equals(sku)) {
                                            TextView coffeePrice = findViewById(R.id.coffee_price);
                                            coffeePrice.setText(price);
                                            coffeeDetails = skuDetails;
                                        }

                                    }
                                }
                            });
                }
            }
            @Override
            public void onBillingServiceDisconnected() {
                // Try to restart the connection on the next request to
                // Google Play by calling the startConnection() method.
                billingClient.startConnection(this);
            }
        });
    }

    @Override
    public String getActivityName() {
        return getString(R.string.title_activity_donate);
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
        return R.layout.activity_donate;
    }

    private void initButtons() {
        CardView premiumView = findViewById(R.id.cardView_premium);
        premiumView.setOnClickListener(v -> {
            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(premiumDetails)
                    .build();
            int responseCode = billingClient.launchBillingFlow(thisActivity, flowParams);
        });

        CardView coffeView = findViewById(R.id.cardView_coffe);
        coffeView.setOnClickListener(v -> {
            BillingFlowParams flowParams = BillingFlowParams.newBuilder()
                    .setSkuDetails(coffeeDetails)
                    .build();
            int responseCode = billingClient.launchBillingFlow(thisActivity, flowParams);
        });
    }

    @Override
    public void onPurchasesUpdated(int responseCode, @Nullable List<Purchase> purchases) {
        if (responseCode == BillingClient.BillingResponse.OK
                && purchases != null) {
            for (Purchase purchase : purchases) {
                handlePurchase(purchase);
            }
        } else if (responseCode == BillingClient.BillingResponse.USER_CANCELED) {
            // Handle an error caused by a user cancelling the purchase flow.
        } else {
            // Handle any other error codes.
        }
    }

    private void handlePurchase(Purchase purchase) {
        if (!verifyValidSignature(purchase.getOriginalJson(), purchase.getSignature())) {
            return;
        }
        //TODO
    }

    private boolean verifyValidSignature(String signedData, String signature) {
        try {
            return Security.verifyPurchase(KeyConstructor.get(), signedData, signature);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
