package com.yaopaine.dynamic;

import android.os.IBinder;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Map;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/9/18
 */
public class ClipHelper {

    public static void bind() {
        try {
            Class<?> serviceManagerClazz = Class.forName("android.os.ServiceManager");
            Method getServiceMethod = serviceManagerClazz.getDeclaredMethod("getService", String.class);
            IBinder clipbord = (IBinder) getServiceMethod.invoke(null, "clipboard");
            IBinder proxyInstance = (IBinder) Proxy.newProxyInstance(serviceManagerClazz.getClassLoader(), new Class[]{IBinder.class}, new ClipProxy(clipbord));

            Field field = serviceManagerClazz.getDeclaredField("sCache");
            field.setAccessible(true);
            Map<String, IBinder> map = (Map<String, IBinder>) field.get(null);
            map.put("clipboard", proxyInstance);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
    }
}
