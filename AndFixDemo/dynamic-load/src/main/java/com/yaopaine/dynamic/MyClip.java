package com.yaopaine.dynamic;

import android.content.ClipData;
import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/9/18
 */
public class MyClip implements InvocationHandler {

    private Object iBinder;

    public MyClip(IBinder binder, Class<?> stubClass) {
        try {
            Method method = stubClass.getDeclaredMethod("asInterface", IBinder.class);
            iBinder = method.invoke(null, binder);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("getPrimaryClip".equals(method.getName())) {
            return ClipData.newPlainText(null, "剪切板内容被我修改了！哈哈哈哈");
        }

        //再拦截是否有复制的方法，放系统认为一直都有
        if ("hasPrimaryClip".equals(method.getName())) {
            return true;
        }

        return method.invoke(iBinder, args);
    }
}
