package com.yaopaine.dynamic;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.yaopaine.andfix.R;

import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/3/18
 */
public class LoadActivity extends AppCompatActivity {

    private static final String TAG = "LoadActivity";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);
        Intent intent = getIntent();
        findViewById(R.id.button1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadApk();
            }
        });
        Log.e(TAG, "LoadActivity onCreate: " + intent);
        findViewById(R.id.button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadRes();
            }
        });


    }

    private void loadRes() {
        String filePath = Environment.getExternalStorageDirectory().getPath() + File.separator + "app-release.apk";
        PackageManager packageManager = getPackageManager();
        PackageInfo archiveInfo = packageManager.getPackageArchiveInfo(filePath, PackageManager.GET_ACTIVITIES);
        if (archiveInfo == null) return;

        ApplicationInfo info = archiveInfo.applicationInfo;
        String className = info.className;
        String packageName = info.packageName;
        int versionCode = archiveInfo.versionCode;
        String versionName = archiveInfo.versionName;

        String appName = packageManager.getApplicationLabel(info).toString();

        Log.e(TAG, "appName: " + appName);
        Log.e(TAG, "className: " + className);
        Log.e(TAG, "packageName: " + packageName);
        Log.e(TAG, "versionCode: " + versionCode);
        Log.e(TAG, "versionName: " + versionName);

        DexClassLoader classLoader = new DexClassLoader(filePath, getExternalCacheDir().getAbsolutePath(), null, getClassLoader());

        try {
            Class<?> clazz = classLoader.loadClass(packageName + ".R$string");

            Field field = clazz.getDeclaredField("app_name");

            int resId = field.getInt(R.id.class);

            AssetManager assetManager = AssetManager.class.newInstance();
            Method addAssetPath = assetManager.getClass().getMethod("addAssetPath", String.class);

            addAssetPath.invoke(assetManager, filePath);

            Resources resources = this.getResources();

            Resources resource2 = new Resources(assetManager, resources.getDisplayMetrics(), resources.getConfiguration());

            String text = resource2.getString(resId);
            Log.e(TAG, "loadRes: " + text);
            showToast(text);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void loadApk() {
        File file = new File(Environment.getExternalStorageDirectory().getPath() + File.separator + "app-release.apk");

        if (!file.exists()) {
            showToast("apk 不存在");
            return;
        }

        if (!file.canRead()) {
            showToast("app需要开启文件读写权限");
            return;
        }

        Log.i(TAG, "getExternalCacheDir().getAbsolutePath()=" + getExternalCacheDir().getAbsolutePath());
        Log.i(TAG, "apkFile.getAbsolutePath()=" + file.getAbsolutePath());

        try {
            //DexFile dx = DexFile.loadDex(file.getAbsolutePath(), File.createTempFile("opt", "dex", getApplicationContext().getCacheDir()).getPath(), 0);
            //PathClassLoader pathClassLoader = new PathClassLoader(file.getAbsolutePath(), getClassLoader());

            /*Enumeration<String> entries = dx.entries();
            while (entries.hasMoreElements()) {
                String className = entries.nextElement();
                if (className.equals("com.baozi.common.AppInfoUtils")) {
                    Log.d(TAG, "#########################################################" + className);
                    Log.d(TAG, className);
                    Log.d(TAG, "##################g#######################################" + className);
                }
                Log.d(TAG, "Analyzing dex content, fonud class: " + className);
            }*/

            DexClassLoader dexClassLoader = new DexClassLoader(file.getAbsolutePath(), getExternalCacheDir().getAbsolutePath(), null, getClassLoader());

            Class<?> loadClass = dexClassLoader.loadClass("com.example.lib_common_util.utils.Utils");
//            Object instance = loadClass.newInstance();
            Constructor<?> constructor = loadClass.getDeclaredConstructor();
            constructor.setAccessible(true);
            Object obj = constructor.newInstance();

            Method method = loadClass.getMethod("getScreenWidth", Context.class);
            Class<?>[] parameterTypes = method.getParameterTypes();
            Object returnObj = method.invoke(null, (Context) getApplicationContext());
            Log.e(TAG, "loadApk: " + returnObj);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
