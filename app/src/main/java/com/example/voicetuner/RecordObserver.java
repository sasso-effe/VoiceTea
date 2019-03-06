package com.example.voicetuner;


import com.example.voicetuner.fft.Complex;
import com.example.voicetuner.fft.FFT;

import java.util.Arrays;

public class RecordObserver implements Observer<RecordListener> {

    @Override
    public void update(RecordListener ob) {
        short[] buffer = ob.getBuffer();
        Complex[] compBuffer = new Complex[buffer.length];
        for (int i = 0; i<buffer.length; i++) {
            compBuffer[i] = new Complex(buffer[i], 0);
        }
        FFT.fft(compBuffer);
    }
}
