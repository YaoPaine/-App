package com.yao.notificationdemo;

import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private String TAG = "MainActivity";
    private int ID = 100;
    private int num = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        findViewById(R.id.tv_send_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                progressNotification();
                final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);

                builder.setSmallIcon(R.mipmap.ic_launcher);
                builder.setContentTitle("订单未支付通知");
                builder.setContentText("您有一笔订单未支付");
                builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
                new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        for (int i = 0; i < 10; i++) {
                            builder.setContentText("您有" + (++num) + "笔订单未支付").setNumber(num);
                            notifyManager.notify(ID, builder.build());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {

                            }
                        }
                    }
                }.start();
            }
        });

    }

    /**
     * 带进度条的通知栏
     */
    public void progressNotification() {
        final NotificationManager notifyManager = (NotificationManager) MainActivity.this.getSystemService(Context.NOTIFICATION_SERVICE);

        final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
//        builder.setContentTitle("这是通知的标题");
        builder.setContentText("这是通知的内容");
        new Thread() {
            @Override
            public void run() {
                super.run();
                int incr;
                for (incr = 0; incr <= 100; incr += 5) {
//                    builder.setProgress(100, incr, false);
                    builder.setProgress(0, 0, true);
                    notifyManager.notify(ID, builder.build());
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        Log.d(TAG, "sleep failure");
                    }
                }
                builder.setContentText("Download complete")
                        // Removes the progress bar
                        .setProgress(0, 0, false);
                notifyManager.notify(ID, builder.build());//ID必须保持一致，否则会🈶️两个通知显示
            }
        }.start();
    }

    public NotificationCompat.Builder updateNotificationNumber() {


        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("订单未支付通知");
        builder.setContentText("您有一笔订单未支付");
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        return builder;
    }
}
