package com.example.voicetuner;

import android.content.Context;
import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.view.View;
import android.widget.Toast;

import java.util.Objects;

public class RecordListener implements View.OnClickListener {

    private final int SAMPLE_RATE = 44100; //Hz value. 44100 Hz is CD samplerate standard.
    // construct AudioRecord to record audio from microphone with sample rate of 44100Hz
    private int minSize = AudioRecord.getMinBufferSize(SAMPLE_RATE, AudioFormat.CHANNEL_IN_MONO,
            AudioFormat.ENCODING_PCM_16BIT);
    private AudioRecord record;
    private short[] buffer = new short[minSize];

    private boolean isRecording = false;
    private String fileName, filePath;
    private Context context;

    RecordListener(Context context, String fileName) {
        super();
        this.context = context;
        this.fileName = fileName;
        this.filePath = Objects.requireNonNull(context.getExternalCacheDir()).getAbsolutePath() + fileName;
    }

    @Override
    public void onClick(View v) {
        if (isRecording) {
            stopRecording();
        } else {
            startRecording();
        }
        isRecording = !isRecording;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public String getFilePath() {
        return filePath;
    }

    public String getFileName() {
        return fileName;
    }

    private void startRecording() {
        record = new AudioRecord(MediaRecorder.AudioSource.MIC, SAMPLE_RATE,
                AudioFormat.CHANNEL_IN_MONO,
                AudioFormat.ENCODING_PCM_16BIT, minSize);
        record.startRecording();
        record.read(buffer, 0, minSize);
        Toast.makeText(getContext(), "Start recording.", Toast.LENGTH_SHORT).show();
    }

    private void stopRecording() {
        record.stop();
        record = null; //for Garbage Collector
        Toast.makeText(getContext(), "Stop recording.", Toast.LENGTH_SHORT).show();
    }

    private Context getContext() {
        return context;
    }

}
