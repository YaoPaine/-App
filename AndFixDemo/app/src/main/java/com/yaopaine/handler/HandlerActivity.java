package com.yaopaine.handler;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/10/18
 */
public class HandlerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        final MyThread myThread = new MyThread();
//        boolean alive = myThread.isAlive();
//        Log.e("TAG", "onCreate: " + alive);
//        myThread.start();
//        Log.e("TAG", "onCreate: " + alive);
        HandlerThread handlerThread = new HandlerThread("HandlerThread");
        handlerThread.start();
        Handler handler = new Handler(handlerThread.getLooper()) {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);


            }
        };
    }

    private static class MyThread extends Thread {

        private Looper mLooper;

        @Override
        public void run() {
            super.run();

            Looper.prepare();

            mLooper = Looper.myLooper();
            Log.e("TAG", "run: " + mLooper);
            Looper.loop();
        }
    }
}
