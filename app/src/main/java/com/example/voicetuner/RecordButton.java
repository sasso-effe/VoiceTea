package com.example.voicetuner;

import android.content.Context;
import android.media.MediaRecorder;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.io.IOException;

public class RecordButton extends android.support.design.widget.FloatingActionButton {
    private boolean isRecording = false;
    private String fileName;
    private MediaRecorder recorder = new MediaRecorder();

    OnClickListener listener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            if (isRecording) {
                stopRecording();
            } else {
                startRecording();
            }
            isRecording = !isRecording;
        }
    };

    public RecordButton(Context context, String fileName) {
        super(context);
        setOnClickListener(listener);
        this.fileName = context.getExternalCacheDir().getAbsolutePath();
        this.fileName += fileName;
    }

    public boolean isRecording() {
        return isRecording;
    }

    public String getFilePath() {
        return fileName;
    }

    private void startRecording() {
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        recorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_2_TS); //MPEG2_TS format is useful for streaming
        recorder.setOutputFile(fileName);
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e("recorder", "prepare() failed");
        }
        recorder.start();
        Toast.makeText(getContext(), "Start recording.", Toast.LENGTH_SHORT);
    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null; //per il Garbage Collector
        Toast.makeText(getContext(), "Stop recording.", Toast.LENGTH_SHORT);
    }

}
