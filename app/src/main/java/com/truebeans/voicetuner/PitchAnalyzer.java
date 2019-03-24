package com.truebeans.voicetuner;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import com.truebeans.voicetuner.activity.MainActivity;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.List;

class PitchAnalyzer {
    private double[] freqsBuffer;
    private List<DataPoint> peaks;
    private MainActivity myActivity;

    PitchAnalyzer(MainActivity activity) {
        freqsBuffer = new double[3];
        peaks = new ArrayList<>();
        myActivity = activity;

    }

    void update(double freq, int x) {
        addFreq(freq);
        if (isSilent()) {
            if (peaks.size() > 1)
                analyze();
            peaks = new ArrayList<>();
        } else {
            checkPeak(x);
        }

    }

    private void addFreq(double freq) {
        if (freqsBuffer.length - 1 >= 0)
            System.arraycopy(freqsBuffer, 1, freqsBuffer, 0, freqsBuffer.length - 1);
        freqsBuffer[freqsBuffer.length - 1] = freq;
    }

    private boolean isSilent() {
        boolean result = true;
        for (double freq : freqsBuffer) {
            result &= freq == -1;
        }
        return result;
    }

    private void checkPeak(int x) {
        if (freqsBuffer[1] > freqsBuffer[2] & freqsBuffer[1] >= freqsBuffer[0]) {
            peaks.add(new DataPoint(x - 2, freqsBuffer[1]));
        }
    }

    private void analyze() {
        peaks.remove(0);
        double max = peaks.parallelStream().mapToDouble(DataPoint::getY).max().orElse(0);
        if (max != 0 && peaks.get(peaks.size() - 1).getY() == max) {
            vibrate();
            showPeak(peaks.get(peaks.size() - 1));
        }


    }

    private void vibrate() {
        Vibrator v = (Vibrator) myActivity.getSystemService(Context.VIBRATOR_SERVICE);
        // Vibrate for 500 milliseconds
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            v.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
        } else {
            //deprecated in API 26
            v.vibrate(100);
        }
    }

    @SuppressWarnings("unchecked")
    private void showPeak(DataPoint peak) {
        myActivity.getPeakPointsSeries().appendData(peak, false, 100);
    }

}
