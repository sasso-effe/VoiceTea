package com.example.voicetuner;

public class Global {
    private static int sampleRate = 44100;
    private static int sampleRateModeId = 4;
    private static int bufferSize = 4096;
    private static int precisionModeId = 1;
    private static double[] windowFunction = generateHannFunction();
    public static int getSampleRate() { return sampleRate;}
    public static int getBufferSize() { return bufferSize;}
    public static int getSampleRateModeId() {return sampleRateModeId;}
    public static int getPrecisionModeId() {return precisionModeId;}

    public static void setSampleRate(int id) {
        switch (id) {
            case 0 :
                Global.sampleRate = 4096;
                break;
            case 1:
                Global.sampleRate = 5512;
                break;
            case 2:
                Global.sampleRate = 11025;
                break;
            case 3:
                Global.sampleRate = 22050;
                break;
            case 4:
                Global.sampleRate = 44100;
                break;
        }
        sampleRateModeId = id;
    }

    public static void setBufferSize(int id) {
        switch (id) {
            case 0:
                Global.bufferSize = 2048;
                break;
            case 1:
                Global.bufferSize = 4096;
                break;
        }
        windowFunction = generateHannFunction();
        precisionModeId = id;
    }

    private static double[] generateHannFunction() {
        double[] hann = new double[bufferSize];
        for (int i = 0; i<bufferSize; i++) {
            hann[i] = 0.5 * (1 - Math.cos(((2 * Math.PI * i) / bufferSize)));
        }
        return hann;
    }

    public static double[] getWindowFunction() {
        return windowFunction;
    }
}
