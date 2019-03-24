package com.truebeans.voicetuner.internet;

import android.widget.ProgressBar;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class EarningsGetter extends InternetCaller {
    private ProgressBar myProgressBar;

    public EarningsGetter(ProgressBar myProgBar) {
        myProgressBar = myProgBar;
        execute();
    }

    @Override
    public RequestBody getRequestBody() {
        return new FormBody.Builder().build();

    }

    @Override
    public String getPageUrl() {
        return "actualEarnings.php";
    }

    @Override
    public void responseReceived() {
        double earnings = Double.parseDouble(serverResponse);
        int percent = (int) (earnings * 100 / 30);
        myProgressBar.setProgress(percent);
    }
}
