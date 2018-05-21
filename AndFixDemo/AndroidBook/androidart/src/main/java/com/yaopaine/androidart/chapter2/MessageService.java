package com.yaopaine.androidart.chapter2;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

import com.yaopaine.androidart.MainActivity;

public class MessageService extends Service {

    public static final int MSG_FROM_SERVICE = 888;

    private static class MessageHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MainActivity.MSG_FROM_CLIENT:
                    Bundle bundle = msg.getData();
                    Log.e(TAG, "handleMessage: " + bundle.getString("msg_server"));

                    Messenger client = msg.replyTo;
                    Message messageReplyTo = Message.obtain(null, MSG_FROM_SERVICE);
                    Bundle replyBundle = new Bundle();
                    replyBundle.putString("msg_client", "嗯，好的，收到了您的反馈，我们这边会尽快为您解决");
                    messageReplyTo.setData(replyBundle);

                    try {
                        client.send(messageReplyTo);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    super.handleMessage(msg);
            }
        }
    }

    private final Messenger mMessenger;
    private static String TAG = "MessageService";

    public MessageService() {
        mMessenger = new Messenger(new MessageHandler());
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.e(TAG, "onBind: ");
        return mMessenger.getBinder();
    }


    @Override
    public void onCreate() {
        super.onCreate();
        Log.e(TAG, "onCreate: ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.e(TAG, "onStart: ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.e(TAG, "onStartCommand: ");
        return super.onStartCommand(intent, flags, startId);
    }
}
