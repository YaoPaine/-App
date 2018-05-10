package com.yaopaine.helper;

import android.content.ComponentName;
import android.content.Intent;
import android.util.Log;

import com.yaopaine.BasicApp;
import com.yaopaine.dynamic.EmptyActivity;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/10/18
 */
public class MyInvokeHandler implements InvocationHandler {

    public static String EXTRA_TARGET_INTENT = "extra_target_intent";

    private Object iActivityManager;

    public MyInvokeHandler(Object iActivityManager) {
        this.iActivityManager = iActivityManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("startActivity".equals(method.getName())) {
            Log.e("Main", "startActivity方法拦截了");
            int index = 0;
            Intent rawIntent = null;
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof Intent) {
                    index = i;
                    rawIntent = (Intent) args[i];
                    break;
                }
            }
            //创建一个掉包的Intent
            Intent intent = new Intent();

            String packageName = BasicApp.getContext().getPackageName();
            ComponentName componentName = new ComponentName(packageName, EmptyActivity.class.getName());
            intent.setComponent(componentName);
            Log.e("TAG", "invoke: " + rawIntent);
            intent.putExtra(EXTRA_TARGET_INTENT, rawIntent);

            args[index] = intent;

            return method.invoke(iActivityManager, args);
        }
        return method.invoke(iActivityManager, args);
    }


}
