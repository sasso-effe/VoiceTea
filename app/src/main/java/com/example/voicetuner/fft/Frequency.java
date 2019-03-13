package com.example.voicetuner.fft;

import com.example.voicetuner.Global;

import java.util.Arrays;
import java.util.stream.DoubleStream;

public class Frequency {

    public static double getFrequency(Complex[] fft) {
        int offset = 0; //ignore is voice focus mode is off
        if (Global.isVoiceFocusOn) {
            fft = reduceArrayToSpeechFreqs(fft);
            offset = getMinBin(); // number of bin skipped
        }
        int index = getMaxMagnitudeIndex(fft) + offset;
        final int SAMPLERATE = Global.getSampleRate();
        return (double) index * SAMPLERATE / Global.getBufferSize();
    }

    private static double magnitude(Complex c) {
        double magnitude = c.im() * c.im() + c.re() * c.re();
        return Math.sqrt(magnitude);
    }

    private static int getMaxMagnitudeIndex(Complex[] fft) {
        DoubleStream magnitudeStream = Arrays.stream(fft)
                .mapToDouble(Frequency::magnitude);
        if (!Global.isVoiceFocusOn) {
            /* since fft is R -> C, the result array is symmetric and it is possible to analyze only
             * one half to speed up the execution times.
             * However it's not possible if VoiceMode is on, because in this case we are analyzing
             * only one non-symmetric portion of the array.
            */
            magnitudeStream = magnitudeStream.limit(fft.length / 2);
        }
        double[] magnitude = magnitudeStream.toArray();
        int maxIndex = 0;
        double max = magnitude[0];
        for (int i = 1; i < magnitude.length; i++) {
            if (magnitude[i] > max) {
                max = magnitude[i];
                maxIndex = i;
            }
        }
        return maxIndex;
    }

    private static Complex[] reduceArrayToSpeechFreqs(Complex[] fft) {
        int deltaBin = getMaxBin() - getMinBin();
        return Arrays.stream(fft).skip(getMinBin()).limit(deltaBin).toArray(Complex[]::new);
    }

    private static int getMinBin() {
        return Global.MIN_SPEECH_FREQ * Global.getBufferSize() / Global.getSampleRate();
    }

    private static int getMaxBin() {
        return Global.MAX_SPEECH_FREQ * Global.getBufferSize() / Global.getSampleRate();
    }
}
