package com.example.voicetuner;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

public class AboutActivity extends AppCompatActivity {
    private ContentLoadingProgressBar myProgressBar;

    @Override
    public void onCreate( Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_about);
        myProgressBar.findViewById(R.id.progressBar);
        myProgressBar.setProgress(25);

    }
}
