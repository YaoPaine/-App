package com.yaopaine.helper;

import android.os.Build;
import android.os.Handler;

import com.yaopaine.BasicApp;
import com.yaopaine.andfix.BuildConfig;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/10/18
 */
public class AMSHookHelper {
    /**
     * 把真正要启动的Activity临时替换为在AndroidManifest.xml中声明的替身Activity
     */
    public static void hookActivityManager() throws ClassNotFoundException {

        try {
            Field field;
            if (Build.VERSION.SDK_INT >= 26) {
                Class<?> activityManagerClazz = Class.forName("android.app.ActivityManager");
                field = activityManagerClazz.getField("IActivityManagerSingleton");

            } else {
                //获取ActivityManagerNative的类
                Class<?> activityManagerNativeClass = Class.forName("android.app.ActivityManagerNative");
                //拿到gDefault字段
                field = activityManagerNativeClass.getDeclaredField("gDefault");
            }

            field.setAccessible(true);
            //Singleton<IActivityManager>
            Object objectSingleton = field.get(null);

            Class<?> singletonClazz = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClazz.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Object raw2IActivityManager = mInstanceField.get(objectSingleton);

            Class<?> iActivityManagerClazz = Class.forName("android.app.IActivityManager");
            Object proxyInstance = Proxy.newProxyInstance(BasicApp.getContext().getClassLoader(), new Class[]{iActivityManagerClazz}, new MyInvokeHandler(raw2IActivityManager));

            mInstanceField.set(objectSingleton, proxyInstance);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }

    }

    public static void hookActivityThreadHandler() throws ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException, NoSuchFieldException {
        Class<?> activityThreadClass = Class.forName("android.app.ActivityThread");
        Method activityThreadMethod = activityThreadClass.getDeclaredMethod("currentActivityThread", null);
        activityThreadMethod.setAccessible(true);
        Object activityThread = activityThreadMethod.invoke(null);

        Field mHField = activityThreadClass.getDeclaredField("mH");
        mHField.setAccessible(true);
        //Handler
        Handler mH = (Handler) mHField.get(activityThread);

        Field mCallback = Handler.class.getDeclaredField("mCallback");
        mCallback.setAccessible(true);
        mCallback.set(mH, new MyCallBack(mH));
    }
}
