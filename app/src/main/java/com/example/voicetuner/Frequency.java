package com.example.voicetuner;

import android.util.Log;

import com.example.voicetuner.Global;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Frequency {
    public static final int NOT_SHOW_FREQUENCY = -1;

    public static double getFrequency(double[] fft) {
        int offset = 0; //ignore is voice focus mode is off
        fft = reduceArray(fft);
        if (Global.isSpeechFocusOn) {
            offset = getMinBin() / 2; // number of bin skipped
        }
        int index = getMaxMagnitudeIndex(fft);
        if (index != NOT_SHOW_FREQUENCY) {
            index += offset;
            final int SAMPLERATE = Global.getSampleRate();
            return (double) (index * SAMPLERATE / Global.getBufferSize());
        } else
            return NOT_SHOW_FREQUENCY;
    }

    private static double magnitude(double re, double im) {
        double magnitude = im * im + re * re;
        return Math.sqrt(magnitude);
    }

    private static int getMaxMagnitudeIndex(double[] fft) {
        double max = 0;
        int maxIndex = 0;
        for (int i = 0; i <fft.length; i += 2) {
            double magnitude = magnitude(fft[i], fft[i+1]);
            if (magnitude > max) {
                max = magnitude;
                maxIndex = i/2;
            }
        }
        if (Global.isSpeechFocusOn && max < Global.MIN_SPEECH_MAGNITUDE)
            return NOT_SHOW_FREQUENCY;
        else
            return maxIndex;
    }

    private static double[] reduceArray(double[] fft) {
        if(Global.isSpeechFocusOn) {
            int deltaBin = getMaxBin() - getMinBin();
            return Arrays.stream(fft).skip(getMinBin()).limit(deltaBin * 2).toArray();
        } else {
            return Arrays.stream(fft).limit(fft.length / 2).toArray();
        }
    }

    private static int getMinBin() {
        return 2 * (Global.MIN_SPEECH_FREQ * Global.getBufferSize() / Global.getSampleRate());
    }

    private static int getMaxBin() {
        return 2 * (Global.MAX_SPEECH_FREQ * Global.getBufferSize() / Global.getSampleRate());
    }
}
