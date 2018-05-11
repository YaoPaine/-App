package com.yaopaine.helper;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.lang.reflect.Field;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/10/18
 */
public class MyCallBack implements Handler.Callback {
    private Handler mH;

    public MyCallBack(Handler handler) {
        this.mH = handler;
    }

    @Override
    public boolean handleMessage(Message msg) {
        /*switch (msg.what) {
            case LAUNCH_ACTIVITY: {
                Trace.traceBegin(Trace.TRACE_TAG_ACTIVITY_MANAGER, "activityStart");
                final ActivityClientRecord r = (ActivityClientRecord) msg.obj;
                r.loadedApk = getLoadedApkNoCheck(
                        r.activityInfo.applicationInfo, r.compatInfo);
                handleLaunchActivity(r, null, "LAUNCH_ACTIVITY");
                Trace.traceEnd(Trace.TRACE_TAG_ACTIVITY_MANAGER);*/
        switch (msg.what) {
            case 100:
                try {
                    handleLaunchActivity(msg);
                } catch (Exception e) {
                }
                break;
        }
//        mH.handleMessage(msg);
        return false;
    }

    private void handleLaunchActivity(Message msg) throws NoSuchFieldException, IllegalAccessException {
        Log.e("MyCallBack", "handleLaunchActivity方法 拦截");
        //final ActivityClientRecord r = (ActivityClientRecord) msg.obj;
        Object obj = msg.obj;
        Field intentField = obj.getClass().getDeclaredField("intent");
        intentField.setAccessible(true);
        Intent intent = (Intent) intentField.get(obj);
        Log.e("MyCallBack", "假的: " + intent);
        Intent rawIntent = intent.getParcelableExtra(MyInvokeHandler.EXTRA_TARGET_INTENT);
        Log.e("MyCallBack", "真的: " + rawIntent);
        intent.setComponent(rawIntent.getComponent());
    }
}
