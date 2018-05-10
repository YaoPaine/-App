package com.yaopaine.helper;

import com.yaopaine.BasicApp;

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
public class PMSHookHelper {

    public static void hookPackManager() throws Exception {
        Class<?> activityThreadClazz = Class.forName("android.app.ActivityThread");
        Field currentActivityThreadField = activityThreadClazz.getDeclaredField("sCurrentActivityThread");
        currentActivityThreadField.setAccessible(true);
        Object currentActivityThread = currentActivityThreadField.get(null);

        Method getPackageManagerMethod = activityThreadClazz.getDeclaredMethod("getPackageManager");
        getPackageManagerMethod.setAccessible(true);
        //static volatile IPackageManager sPackageManager;
        Object sPackageManager = getPackageManagerMethod.invoke(currentActivityThread);

        Class<?> IPackageManagerClazz = Class.forName("android.content.pm.IPackageManager");

        Object proxyInstance = Proxy.newProxyInstance(BasicApp.getContext().getClassLoader(), new Class[]{IPackageManagerClazz}, new PMSInvokeHandler(sPackageManager));

        Field sPackageManagerField = currentActivityThread.getClass().getDeclaredField("sPackageManager");
        sPackageManagerField.setAccessible(true);
        sPackageManagerField.set(currentActivityThread, proxyInstance);
    }
}
