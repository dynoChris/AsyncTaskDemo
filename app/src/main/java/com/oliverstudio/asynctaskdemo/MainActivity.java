package com.oliverstudio.asynctaskdemo;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.InetSocketAddress;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {

    private ProgressBar progressBar;
    private BackgroundTask backgroundTask;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        progressBar = findViewById(R.id.progress_bar);

        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backgroundTask = new BackgroundTask(progressBar);
                backgroundTask.execute();
            }
        });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (backgroundTask != null) {
            backgroundTask.cancel(true);
        }
    }

    private static class BackgroundTask extends AsyncTask<Void, Void, Boolean> {

        private final WeakReference<ProgressBar> messageViewReference;

        private BackgroundTask(ProgressBar progressBar) {
            this.messageViewReference = new WeakReference<>(progressBar);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            ProgressBar progressBar = messageViewReference.get();
            if (progressBar != null) {
                progressBar.setVisibility(View.VISIBLE);
                Log.d("devptag", "onPreExecute: ");
            }
        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            return isInternetConnected();
        }

        boolean isInternetConnected() {
            try {
                Socket sock = new Socket();
                sock.connect(new InetSocketAddress("8.8.8.8", 53), 1500);
                sock.close();
                return true;
            } catch (IOException e) {
                return false;
            }
        }
        
        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);

            ProgressBar progressBar = messageViewReference.get();
            if (progressBar != null) {
                progressBar.setVisibility(View.GONE);
                Log.d("devptag", "onPostExecute. Result: " + result);
            }
        }
    }
}