package com.yao.rxjavaandretrofit.service;


import android.app.Notification;
import android.content.ComponentName;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.support.annotation.RequiresApi;
import android.util.Log;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/11 上午9:43
 * @Version:
 */

@RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR2)
public class NotificationListener extends NotificationListenerService {


    @Override
    public void onNotificationRemoved(StatusBarNotification sbn) {
        super.onNotificationRemoved(sbn);
        String packageName = sbn.getPackageName();
        Log.e("TAG", "packageName: " + packageName);
        Notification notification = sbn.getNotification();
        if (notification != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String setttingText = notification.getSettingsText().toString();
            }
        }
    }

    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);
        String packageName = sbn.getPackageName();
        Log.e("TAG", "packageName: " + packageName);
        Notification notification = sbn.getNotification();
        if (notification != null) {
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                Bundle extras = notification.extras;
                for (String key : extras.keySet()) {
                    Log.e("TAG", "key: "+key+"\tvalue: " );
                }
            }
            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                String setttingText = notification.getSettingsText().toString();
            }
        }
    }

    @Override
    public void onListenerConnected() {
        super.onListenerConnected();
        ComponentName componentName = new ComponentName(getPackageName(), "com.yao.rxjavaandretrofit.service.NotificationListener");
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            requestRebind(componentName);
        }
    }
}
