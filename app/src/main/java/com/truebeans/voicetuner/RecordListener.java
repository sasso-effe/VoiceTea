package com.truebeans.voicetuner;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Toast;

import com.truebeans.voicetuner.activity.MainActivity;

public class RecordListener extends Observable implements View.OnClickListener {

    private AudioRecord record;
    /* private int minSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
     *      AudioFormat.ENCODING_PCM_16BIT);
     * minSize is about 3500, but we need a power of 2 dimension. 4096 is 2^12
     */
    private short[] buffer = new short[Global.getBufferSize()];
    private boolean isRecording = false;
    private MainActivity activity;

    public RecordListener(MainActivity activity) {
        super();
        this.activity = activity;
        addOb(new RecordObserver(activity));

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
        //Hz value. 44100 Hz is CD samplerate standard.
        int SAMPLE_RATE = Global.getSampleRate();
        record = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, Global.getBufferSize());
        record.startRecording();
        //activity.getSeries().resetData(new DataPointInterface{new DataPoint(0,0)});
        Thread recordThread = new Thread() {
            @Override
            public void run() {
                while (isRecording) {
                    record.read(buffer, 0, Global.getBufferSize());
                    notifyObservers();
                }
            }
        };
        recordThread.start();
    }

    private void stopRecording() {
        isRecording = false;
        record.stop();
        record.release();
        record = null; //for Garbage Collector
        Toast.makeText(getActivity().getApplicationContext(), "Stop recording.", Toast.LENGTH_SHORT).show();
    }

    private Context getActivity() {
        return activity;
    }

    short[] getBuffer() {
        return buffer;
    }


}


