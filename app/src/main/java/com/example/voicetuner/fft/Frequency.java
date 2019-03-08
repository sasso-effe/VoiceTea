package com.example.voicetuner.fft;

import android.app.Activity;

import com.example.voicetuner.Global;
import com.example.voicetuner.R;

import java.util.Arrays;

public class Frequency {

    public static double getFrequency(Complex[] fft) {
        int index = getMaxMagnitudeIndex(fft);
        final int SAMPLERATE = Global.getSampleRate();
        return (double)index * SAMPLERATE / fft.length;
    }

    private static double magnitude(Complex c) {
        double magnitude = c.im()*c.im() + c.re() * c.re();
        return Math.sqrt(magnitude);
    }

    private static int getMaxMagnitudeIndex(Complex[] fft) {
        double[] magnitude = Arrays.stream(fft)
                .mapToDouble(Frequency::magnitude)
                .limit(fft.length/2)
                .toArray();
        int maxIndex = 0;
        double max = magnitude[0];
        for(int i = 1; i < magnitude.length; i++) {
            if (magnitude[i] > max) {
                max = magnitude[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }
}
