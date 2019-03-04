package com.example.voicetuner;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.Observable;

public class RecordListener extends Observable implements View.OnClickListener {

    private final int SAMPLE_RATE = 44100; //Hz value. 44100 Hz is CD samplerate standard.
    // construct AudioRecord to record audio from microphone with sample rate of 44100Hz
    private int minSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT);
    private AudioRecord record;
    private short[] buffer = new short[minSize];

    private boolean isRecording = false;
    private Context context;

    RecordListener(Context context) {
        super();
        this.context = context;
    }

    @Override
    public void onClick(View v) {
        if (isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
    }

    private boolean isRecording() {
        return isRecording;
    }


    private void startRecording() {
        isRecording = true;
        record = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, minSize);
        record.startRecording();
        Thread recThread = new Thread() {
            @Override
            public void run() {
                while (isRecording) {
                    record.read(buffer, 0, minSize);
                    notifyObservers();
                }
            }
        };
        recThread.start();
        Toast.makeText(getContext(), "Start recording.", Toast.LENGTH_SHORT).show();
    }

    private void stopRecording() {
        isRecording = false;
        record.stop();
        record.release();
        record = null; //for Garbage Collector
        Toast.makeText(getContext(), "Stop recording.", Toast.LENGTH_SHORT).show();
    }

    private Context getContext() {
        return context;
    }

    public short[] getBuffer() {
        return buffer;
    }

    @Deprecated
    //For test only
    private void show() {
        Thread thread = new Thread() {
            @Override
            public void run() {
                while (isRecording()) {
                    String logBuffer = "";
                    for (short s : getBuffer()) {
                        logBuffer += s;
                    }
                    Log.i("buffer", logBuffer);
                }
            }
        };
        thread.start();
    }
}


