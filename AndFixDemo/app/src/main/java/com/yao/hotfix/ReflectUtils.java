package com.yao.hotfix;

import java.lang.reflect.Field;
import java.lang.reflect.Method;


/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/3/18
 */
public class ReflectUtils {

    public static Object invokeStaticMethod(String className, String methodName, Class[] parameterTypes, Object[] args) {
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(null, args);
        } catch (Exception e) {

        }
        return null;
    }

    public static Object getStaticFieldObj(String className, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.get(null);
        } catch (Exception e) {

        }
        return null;
    }

    public static Object invokeMethod(String className, Object instance, String methodName, Class[] parameterTypes, Object[] args) {
        try {
            Class<?> clazz = Class.forName(className);
            Method method = clazz.getDeclaredMethod(methodName, parameterTypes);
            method.setAccessible(true);
            return method.invoke(instance, args);
        } catch (Exception e) {

        }
        return null;
    }

    public static Object getFieldObj(String className, Object object, String fieldName) {
        try {
            Class<?> clazz = Class.forName(className);
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            field.get(object);
        } catch (Exception e) {

        }
        return null;
    }
}
