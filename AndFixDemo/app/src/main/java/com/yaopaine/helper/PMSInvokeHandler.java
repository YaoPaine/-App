package com.yaopaine.helper;

import android.content.ComponentName;
import android.content.pm.ComponentInfo;

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
public class PMSInvokeHandler implements InvocationHandler {

    private Object mPMS;

    public PMSInvokeHandler(Object packageManager) {
        this.mPMS = packageManager;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getActivityInfo".equals(method.getName())) {
            for (int i = 0; i < args.length; i++) {
                if (args[i] instanceof ComponentName) {
                    args[i] = new ComponentName(BasicApp.getContext().getPackageName(), EmptyActivity.class.getName());
                }
            }
        }
        return method.invoke(mPMS, args);
    }
}
