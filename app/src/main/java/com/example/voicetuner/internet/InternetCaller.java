package com.example.voicetuner.internet;

import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public abstract class InternetCaller extends AsyncTask<Void, Void, Void> implements InternetCall {

    String serverResponse;

    @Override
    protected Void doInBackground(Void... voids) {
        internetCall();
        return null;
    }

    public void internetCall() {

        OkHttpClient client = new OkHttpClient();

        String hostingUrl = "http://truebeans.it/voicetea/";
        Request request = new Request.Builder()
                .url(hostingUrl + getPageUrl())
                .post(getRequestBody())
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }

                assert response.body() != null;
                serverResponse = response.body().string();

                Handler handler = new Handler(Looper.getMainLooper());

                handler.post(() -> responseReceived());
            }
        });

    }

    public abstract RequestBody getRequestBody();

    public abstract String getPageUrl();

    public abstract void responseReceived();


}
