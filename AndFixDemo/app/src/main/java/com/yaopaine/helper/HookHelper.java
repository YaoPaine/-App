package com.yaopaine.helper;

import android.content.Context;
import android.util.Log;

import com.yaopaine.BasicApp;

import java.lang.reflect.Array;
import java.lang.reflect.Field;

import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexClassLoader;
import dalvik.system.PathClassLoader;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/10/18
 */
public class HookHelper {
    /**
     * @param dexClassLoader 加载插件的classloader
     */
    public static void inject(DexClassLoader dexClassLoader) {
        Context context = BasicApp.getContext();
        //该classloader是宿主apk的classloader
        PathClassLoader pathClassLoader = (PathClassLoader) context.getClassLoader();

        try {
            Object pluginDexPathList = getPathList(dexClassLoader);
            Object hostDexPathList = getPathList(pathClassLoader);

            Class<?> loadClass = dexClassLoader.loadClass("com.yaopaine.aptsample.PluginActivity");
            Log.e("TAG", "inject: " + loadClass.getName());
            Object pluginDexElements = getDexElements(pluginDexPathList);
            Object hostDexElements = getDexElements(hostDexPathList);

            //将合并的pathList设置到本应用的ClassLoader
            Object newDexElements = combineArray(pluginDexElements, hostDexElements);
            setField(hostDexPathList, newDexElements, "dexElements");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static Object combineArray(Object pluginDexElements, Object hostDexElements) {
        //
        Class<?> componentType = hostDexElements.getClass().getComponentType();

        int pluginLength = Array.getLength(pluginDexElements);
        int hostLength = Array.getLength(hostDexElements);
        int newArrayLength = pluginLength + hostLength;
        Object newDexElementsArray = Array.newInstance(componentType, newArrayLength);

        for (int i = 0; i < newArrayLength; i++) {
            if (i < hostLength) {
                Array.set(newDexElementsArray, i, Array.get(hostDexElements, i));
            } else {
                Array.set(newDexElementsArray, i, Array.get(pluginDexElements, i - hostLength));
            }
        }
        return newDexElementsArray;
    }

    /**
     * DexPathList
     */
    public static Object getPathList(BaseDexClassLoader classLoader) throws
            ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
        return getField(classLoader, Class.forName("dalvik.system.BaseDexClassLoader"), "pathList");
    }

    /**
     * @param object DexPathList
     * @return
     */
    public static Object getDexElements(Object object) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException {
//        return getField(object, Class.forName("dalvik.system.DexPathList"), "dexElements");
        return getField(object, object.getClass(), "dexElements");
    }

    /**
     */
    public static Object getField(Object object, Class<?> clazz, String field) throws
            NoSuchFieldException, IllegalAccessException {
        Field declaredField = clazz.getDeclaredField(field);
        declaredField.setAccessible(true);
        return declaredField.get(object);
    }

    public static void setField(Object object, Object value, String field) throws NoSuchFieldException, IllegalAccessException {
        Field declaredField = object.getClass().getDeclaredField(field);
        declaredField.setAccessible(true);
        declaredField.set(object, value);
    }


}
