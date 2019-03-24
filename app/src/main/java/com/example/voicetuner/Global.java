package com.example.voicetuner;

public class Global {
    /* Class to access to global variables such as current settings.
     * ---VARIABLE EXPLANATION:---
     * sampleRate:          number of samples in a second
     * sampleRateId:    id for showing current setting in setting spinner
     * bufferSize:          size of the buffer where is recorded audio. Bigger size means more precision
     * bufferSizeId:     id for showing current setting in setting spinner
     * windowFunction:      array with length = bufferSize containing y window function values
     * MIN_SPEECH_FREQ:     minimum frequency to be considered if Speech Focus Mode is active
     * MAX_SPEECH_FREQ:     maximum frequency to be considered if Speech Focus Mode is active
     * MIN_SPEECH_MAGNITUDE:minimun magnitude to be considered if Speech Focus Mode is active
     */
    private static final int[] SAMPLE_RATE = {4096, 5512, 11025, 22050, 44100};
    private static int sampleRateId = 4;
    private static final int[] BUFFER_SIZE = {2048, 4096};
    private static int bufferSizeId = 1;
    private static double[] windowFunction = generateHannFunction();
    public static final int MIN_SPEECH_FREQ = 30;
    public static final int MAX_SPEECH_FREQ = 700;
    public static final int MIN_SPEECH_MAGNITUDE = 80000;
    public static boolean isSpeechFocusOn = true;
    public static boolean isSignalModeOn = true;


    public static int getBufferSize() {
        return BUFFER_SIZE[getBufferSizeId()];
    }

    public static int getSampleRateId() {
        return sampleRateId;
    }

    public static int getBufferSizeId() {
        return bufferSizeId;
    }

    public static int getSampleRate() {
        return SAMPLE_RATE[getSampleRateId()];
    }

    public static void setSampleRateId(int id) {
        sampleRateId = id;
    }

    public static void setBufferSize(int id) {
        bufferSizeId = id;
        windowFunction = generateHannFunction();
    }

    private static double[] generateHannFunction() {
        int size = getBufferSize();
        double[] hann = new double[size];
        for (int i = 0; i < size; i++) {
            hann[i] = 0.5 * (1 - Math.cos(((2 * Math.PI * i) / size)));
        }
        return hann;
    }

    public static double[] getWindowFunction() {
        return windowFunction;
    }
}
