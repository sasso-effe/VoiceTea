package com.example.voicetuner;

import android.app.Application;

public class Global {
    private static final int SAMPLE_RATE = 5512;
    private static final int BUFFER_SIZE = 4096;
    public static int getSampleRate() { return SAMPLE_RATE;}
    public static int getBufferSize() { return BUFFER_SIZE;}
}
