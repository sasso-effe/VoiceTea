package com.example.voicetuner.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.voicetuner.R;
import com.example.voicetuner.RecordListener;
import com.google.android.gms.ads.AdView;
import com.jjoe64.graphview.GraphView;

public class MainActivity extends BannerActivity {
    private final int RECORD_AUDIO_REQ_COD = 50;
    private String[] permissions = {Manifest.permission.RECORD_AUDIO};
    private GraphView graph;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCompat.requestPermissions(this, permissions, RECORD_AUDIO_REQ_COD);
        FloatingActionButton recordButton = findViewById(R.id.fab);
        recordButton.setOnClickListener(new RecordListener(this));
        Toolbar myToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.navigation_about:
                startActivity(AboutActivity.class);
                break;
            case R.id.navigation_help:
                startActivity(AboutActivity.class);
                break;
            case R.id.navigation_settings:
                startActivity(SettingsActivity.class);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void startActivity(java.lang.Class activity) {
        Intent homeIntent = new Intent(MainActivity.this, activity);
        startActivity(homeIntent);

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
            Toast.makeText(getApplicationContext(), "L'app non può funzionare senza permessi. Sto uscendo...", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    @Override
    public AdView getAdView() {
        return findViewById(R.id.adView);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initGraph() {
        graph = (GraphView) findViewById(R.id.graph);
        graph.setBackgroundColor(getColor(R.color.colorBackground));
    }

    public GraphView getGraph() {return graph;}
}
