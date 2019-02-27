package com.example.voicetuner;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.*;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.widget.Toast;

public class MainActivity extends Activity {

    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private final int RECORD_AUDIO_REQ_COD = 1010;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActivityCompat.requestPermissions(this, permissions, RECORD_AUDIO_REQ_COD);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        boolean permissionToRecordGranted = false;
        switch (requestCode) {
            case (RECORD_AUDIO_REQ_COD) :
                permissionToRecordGranted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                break;
        }
        if (!permissionToRecordGranted) {
            Toast.makeText(getApplicationContext(), "L'app non può funzionare senza permessi. Sto uscendo...", Toast.LENGTH_LONG);
            finish();}
    }
}
