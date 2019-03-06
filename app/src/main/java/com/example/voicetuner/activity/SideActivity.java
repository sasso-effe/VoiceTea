package com.example.voicetuner.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.Objects;

public abstract class SideActivity extends BannerActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initToolBar();
    }

    private void initToolBar() {
        Toolbar myToolbar = getToolbar();
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle(getActivityName());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    public abstract String getActivityName();

    public abstract Toolbar getToolbar();

}
