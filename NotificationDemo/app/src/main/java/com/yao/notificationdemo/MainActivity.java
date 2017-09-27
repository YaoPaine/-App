package com.yao.notificationdemo;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
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


        findViewById(R.id.tv_send_notification).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                progressNotification();

//                updateNotificationNumber();

//                clickCancel();

                operator();

//                show();

//                showNotification();
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

    public void updateNotificationNumber() {
        final NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        final NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
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

    public void clickCancel() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(MainActivity.this);
        NotificationManager notifyManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentTitle("订单未支付通知");
        builder.setContentText("您有一笔订单未支付");
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        Notification notification = builder.build();
        notifyManager.notify(ID, notification);
    }

    public void operator() {
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("My notification")
                        .setContentText("Hello World!")
                        .setAutoCancel(true);
        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(this, ResultActivity.class);

        // The stack builder object will contain an artificial back stack for the
        // started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(ResultActivity.class);
        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        // mId allows you to update the notification later on.
        mNotificationManager.notify(ID, mBuilder.build());
    }

    public void show() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent msgIntent = new Intent();
        msgIntent.setClass(this, ResultActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, msgIntent, PendingIntent.FLAG_UPDATE_CURRENT);
// create and send notificaiton
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(getApplicationInfo().icon)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)//自己维护通知的消失
                .setContentTitle("我是标题")
                .setTicker("我是ticker")
                .setContentText("我是内容")
                .setContentIntent(pendingIntent);
        //将一个Notification变成悬挂式Notification
        mBuilder.setFullScreenIntent(pendingIntent, true);
        Notification notification = mBuilder.build();
        manager.notify(0, notification);

    }

    public void showNotification() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent msgIntent = new Intent();
        Intent mainIntent = new Intent();
        msgIntent.setClass(this, ResultActivity.class);
        mainIntent.setClass(this, MainActivity.class);
        //注意此处的顺序
        Intent[] intents = new Intent[]{mainIntent, msgIntent};
        PendingIntent pendingIntent = PendingIntent.
                getActivities(this, 0, intents, PendingIntent.FLAG_UPDATE_CURRENT);

        // create and send notificaiton
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(getApplicationInfo().icon)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)//自己维护通知的消失
                .setContentTitle("我是标题")
                .setTicker("我是ticker")
                .setContentText("我是内容")
                .setContentIntent(pendingIntent);
        //将一个Notification变成悬挂式Notification
//        mBuilder.setFullScreenIntent(pendingIntent, true);
        Notification notification = mBuilder.build();
        manager.notify(0, notification);
    }

    public void showNotification2() {
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        Intent msgIntent = new Intent();
        msgIntent.setClass(this, ResultActivity.class);

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, msgIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        // create and send notificaiton
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(getApplicationInfo().icon)
                .setWhen(System.currentTimeMillis())
                .setAutoCancel(true)//自己维护通知的消失
                .setContentTitle("我是标题")
                .setTicker("我是ticker")
                .setContentText("我是内容")
                .setContentIntent(pendingIntent);
        //将一个Notification变成悬挂式Notification
        mBuilder.setFullScreenIntent(pendingIntent, true);
        Notification notification = mBuilder.build();
        manager.notify(0, notification);
    }
}
