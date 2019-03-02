package com.example.voicetuner;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class MainActivity extends AppCompatActivity {

    private final int RECORD_AUDIO_REQ_COD = 50;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private AdView mAdView;
    private FloatingActionButton recordButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loadAds();
        ActivityCompat.requestPermissions(this, permissions, RECORD_AUDIO_REQ_COD);
        recordButton = findViewById(R.id.fab);
        recordButton.setOnClickListener(new RecordListener(this, "testrec"));
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionToRecordGranted = false;
        switch (requestCode) {
            case (RECORD_AUDIO_REQ_COD):
                permissionToRecordGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordGranted) {
            Toast.makeText(getApplicationContext(), "L'app non può funzionare senza permessi. Sto uscendo...", Toast.LENGTH_LONG);
            finish();
        }
    }

    private void loadAds() {
        MobileAds.initialize(this, "ca-app-pub-8343076377545122~2378764836");
        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder()
                .addTestDevice("720C771F4D03D985B863C6D49FAE708A")  //TODO: solo per il test, rimuovere questa stringa al lancio
                .build();
        mAdView.loadAd(adRequest);
    }

}
