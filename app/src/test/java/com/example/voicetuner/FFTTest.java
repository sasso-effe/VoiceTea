package com.example.voicetuner;

import com.example.voicetuner.fft.Complex;
import com.example.voicetuner.fft.FFT;
import com.example.voicetuner.fft.Frequency;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class FFTTest {
    @Test
    public void fft_isCorrect() {
        final int AMPLITUDE, FREQUENCY, SAMPLE_RATE;
        AMPLITUDE = 10;
        FREQUENCY = 1046;
        SAMPLE_RATE = 44100;
        Complex[] buff = new Complex[4096];
        for (int i = 0; i<buff.length; i++) {
            buff[i] = new Complex(AMPLITUDE * Math.sin(2 * Math.PI * FREQUENCY * i / SAMPLE_RATE), 0);
        }
        buff = FFT.fft(buff);
        double frequency = Frequency.getFrequency(buff);
        assertEquals(frequency, FREQUENCY, 3);
    }
}