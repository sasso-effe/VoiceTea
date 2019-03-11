package com.example.voicetuner;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.voicetuner.fft.Complex;
import com.example.voicetuner.fft.FFT;
import com.example.voicetuner.fft.Frequency;

import java.util.Arrays;

public class RecordObserver implements Observer<RecordListener> {
    private Activity myActivity;

    RecordObserver(Activity activity) {
        myActivity = activity;
    }

    @Override
    public void update(RecordListener ob) {
        short[] buffer = ob.getBuffer();
        Complex[] compBuffer = new Complex[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            compBuffer[i] = new Complex(buffer[i], 0);
        }
        compBuffer = FFT.fft(compBuffer);
        double freq = Frequency.getFrequency(compBuffer);
        showFrequency(freq);

    }

    private void showFrequency(double freq) {
        if (freq > 0) {//TODO: solo per test
            TextView frequencyText = myActivity.findViewById(R.id.frequencyText);
            ProgressBar frequencyBar = myActivity.findViewById(R.id.frequencyBar);
            //frequencyText.setText(String.format("%s Hz", freq));
            int freqPercent = (int) freq * 100 / (Global.getSampleRate() / 2);
            frequencyBar.setProgress(freqPercent);
        } else {
            Log.i("Frequency error", "Frequency too low: " + freq);
        }
    }
}
