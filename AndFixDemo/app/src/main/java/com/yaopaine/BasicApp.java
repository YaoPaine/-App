package com.yaopaine;

import android.app.Instrumentation;
import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Environment;
import android.util.Log;

import com.yaopaine.andfix.BaseApp;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/9/18
 */
public class BasicApp extends BaseApp {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //replaceInstrumentation();
        mContext = getApplicationContext();

        try {
            resolveResource();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private AssetManager mNewAssetManager;
    private Resources mNewResource;
    private Resources.Theme newTheme;

    private void resolveResource() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        File storageDirectory = Environment.getExternalStorageDirectory();
        String pluginPath = storageDirectory.getPath() + File.separator + "yaoPlugin" + File.separator + "app-debug.apk";
        String cachePath = getCacheDir().getAbsolutePath();
        DexClassLoader dexClassLoader = new DexClassLoader(pluginPath, cachePath, cachePath, getClassLoader());

        mNewAssetManager = AssetManager.class.newInstance();
        Method addAssetPathMethod = mNewAssetManager.getClass().getDeclaredMethod("addAssetPath", String.class);
        addAssetPathMethod.setAccessible(true);

        addAssetPathMethod.invoke(mNewAssetManager, pluginPath);

        Method ensureStringBlocks = AssetManager.class.getDeclaredMethod("ensureStringBlocks");
        ensureStringBlocks.setAccessible(true);
        ensureStringBlocks.invoke(mNewAssetManager);

        Resources supResource = super.getResources();
        Log.e("Main", "supResource = " + supResource);
        mNewResource = new Resources(mNewAssetManager, supResource.getDisplayMetrics(), supResource.getConfiguration());
        Log.e("Main", "设置 getResource = " + getResources());

        newTheme = mNewResource.newTheme();
        newTheme.setTo(super.getTheme());
    }

    @Override
    public Resources getResources() {
        return mNewResource == null ? super.getResources() : mNewResource;
    }

    @Override
    public Resources.Theme getTheme() {
        return newTheme == null ? super.getTheme() : newTheme;
    }

    @Override
    public AssetManager getAssets() {
        return mNewAssetManager == null ? super.getAssets() : mNewAssetManager;
    }

    /**
     *
     */
    public void setAppCompatTheme() {

    }

    public static Context getContext() {
        return mContext;
    }

    /**
     * hook 系统启动Activity的方法
     */
    private void replaceInstrumentation() {
        try {
            Class<?> clazz = Class.forName("android.app.ActivityThread");

            Method currentActivityThread = clazz.getDeclaredMethod("currentActivityThread");
            currentActivityThread.setAccessible(true);
            Object currentThread = currentActivityThread.invoke(null);

            Field instrumentationField = clazz.getDeclaredField("mInstrumentation");
            instrumentationField.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) instrumentationField.get(currentThread);
            MyInstrumentation otherInstrumentation = new MyInstrumentation(instrumentation, this);
            instrumentationField.set(currentThread, otherInstrumentation);

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
