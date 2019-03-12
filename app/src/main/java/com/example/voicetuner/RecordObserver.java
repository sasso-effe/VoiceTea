package com.example.voicetuner;


import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.voicetuner.activity.MainActivity;
import com.example.voicetuner.fft.Complex;
import com.example.voicetuner.fft.FFT;
import com.example.voicetuner.fft.Frequency;
import com.jjoe64.graphview.series.DataPoint;

import java.util.Arrays;

public class RecordObserver implements Observer<RecordListener> {
    private MainActivity myActivity;
    private int x = 0;

    RecordObserver(MainActivity activity) {
        myActivity = activity;
    }

    @Override
    public void update(RecordListener ob) {
        short[] buffer = ob.getBuffer();
        double[] window = Global.getWindowFunction();
        Complex[] compBuffer = new Complex[buffer.length];
        for (int i = 0; i < buffer.length; i++) {
            compBuffer[i] = new Complex(buffer[i] * window[i], 0);
        }
        compBuffer = FFT.fft(compBuffer);
        double freq = Frequency.getFrequency(compBuffer);
        drawGraph(freq);
        //showFrequency(freq);

    }

    private void drawGraph(double y) {
        DataPoint p = new DataPoint(x, y);
        myActivity.getSeries().appendData(p, true, 100);
        x++;

    }

}
