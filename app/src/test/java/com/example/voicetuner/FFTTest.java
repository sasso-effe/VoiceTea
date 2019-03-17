package com.example.voicetuner;

import org.jtransforms.fft.DoubleFFT_1D;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class FFTTest {
    @Test
    public void fft_isCorrect() {
        final int AMPLITUDE, FREQUENCY, SAMPLE_RATE;
        Global.isSpeechFocusOn = true;
        AMPLITUDE = 1000000000;
        FREQUENCY = 500;
        SAMPLE_RATE = Global.getSampleRate();
        double[] buff = new double[Global.getBufferSize() * 2];
        for (int i = 0; i<buff.length; i += 2) {
            buff[i] = AMPLITUDE * Math.sin(Math.PI * FREQUENCY * i / SAMPLE_RATE);
            buff[i+1] = 0;
        }
        new DoubleFFT_1D(buff.length/2).complexForward(buff);
        double frequency = Frequency.getFrequency(buff);
        double delta = (double) SAMPLE_RATE / Global.getBufferSize();
        assertEquals(FREQUENCY, frequency, delta);
    }
}