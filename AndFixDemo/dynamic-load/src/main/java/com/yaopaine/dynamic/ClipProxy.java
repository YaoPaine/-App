package com.yaopaine.dynamic;

import android.os.IBinder;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/9/18
 */
public class ClipProxy implements InvocationHandler {

    private IBinder mIBinder;

    public ClipProxy(IBinder clipbord) {
        this.mIBinder = clipbord;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        if ("queryLocalInterface".equals(method.getName())) {
            Class<?> mStubClass = Class.forName("android.content.IClipboard$Stub");
            Class<?> mIClipboard = Class.forName("android.content.IClipboard");

            return Proxy.newProxyInstance(mStubClass.getClassLoader(), new Class[]{mIClipboard}, new MyClip(mIBinder, mStubClass));
        }
        //不是这个方法还是返回原系统的执行
        return method.invoke(mIBinder, args);
    }
}
