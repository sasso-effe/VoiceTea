package com.example.voicetuner;


import com.example.voicetuner.activity.MainActivity;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.jtransforms.fft.DoubleFFT_1D;

public class RecordObserver implements Observer<RecordListener> {
    private MainActivity myActivity;
    private int x = 0;
    private DoubleFFT_1D dfft;
    private PitchAnalyzer analyzer;

    RecordObserver(MainActivity activity) {
        myActivity = activity;
        dfft = new DoubleFFT_1D(Global.getBufferSize());
        analyzer = new PitchAnalyzer(activity);
    }

    @Override
    public void update(RecordListener ob) {
        short[] buffer = ob.getBuffer();
        double[] window = Global.getWindowFunction();
        double[] compBuffer = new double[buffer.length * 2];

        //Apply window function and then add to compBuffer with immaginary part = 0;
        for (int i = 0; i < buffer.length; i++) {
            compBuffer[2 * i] = buffer[i] * window[i];
            compBuffer[i + 1] = 0;
        }

        dfft.complexForward(compBuffer);
        double freq = Frequency.getFrequency(compBuffer);
        drawGraph(freq);
        analyzer.update(freq, x);
    }

    private void drawGraph(double y) {
        LineGraphSeries series = myActivity.getSeries();
        boolean notShow = y == Frequency.NOT_SHOW_FREQUENCY;
        if (notShow) {
            y = 0;
        }
        DataPoint p;
        p = new DataPoint(x, y);
        series.appendData(p, true, 100);
        x++;


    }


}
