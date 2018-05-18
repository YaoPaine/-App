package com.yao.hotfix;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import com.yaopaine.BasicApp;
import com.yaopaine.andfix.R;
import com.yaopaine.androidart.chapter1.Chapter1Activity;
import com.yaopaine.helper.AMSHookHelper;
import com.yaopaine.helper.HookHelper;
import com.yaopaine.helper.PMSHookHelper;

import java.io.File;

import dalvik.system.DexClassLoader;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/3/18
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(newBase);
        new Thread() {
            @Override
            public void run() {
                super.run();
                File storageDirectory = Environment.getExternalStorageDirectory();
//                String pluginPath = storageDirectory.getAbsolutePath() + "/yaoPlugin/app-debug.apk";
                String pluginPath = storageDirectory.getPath() + File.separator + "yaoPlugin" + File.separator + "app-debug.apk";
                String cachePath = getCacheDir().getAbsolutePath();
                DexClassLoader dexClassLoader = new DexClassLoader(pluginPath, cachePath, cachePath, getClassLoader());

//                String apkPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/chajian_demo.apk";
//                DexClassLoader mClassLoader = new DexClassLoader(apkPath, cachePath, cachePath, getClassLoader());
                HookHelper.inject(dexClassLoader);
                try {
                    AMSHookHelper.hookActivityManager();
                    PMSHookHelper.hookPackManager();//继承AppCompatActivity会在PackManager中check ActivityInfo
                    AMSHookHelper.hookActivityThreadHandler();

                    BasicApp app = (BasicApp) getApplication();

                } catch (Exception e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(MainActivity.this, "hook完成", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }.start();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onClick(View view) {
//        showToast("I'm a bug");
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.yaopaine.aptsample", "com.yaopaine.aptsample.MainActivity"));
        startActivity(intent);
    }

    public void onFix(View view) {
//        Intent intent = new Intent(this, LoadActivity.class);
//        intent.putExtra("name", "跳转到LoadActivity");
//        startActivity(intent);
        Intent intent = new Intent();
        intent.setComponent(new ComponentName("com.yaopaine.aptsample", "com.yaopaine.aptsample.PluginActivity"));
        startActivityForResult(intent, 100);

//        try {
//            HotFixManager.getInstance().init(this);
//            HotFixManager.getInstance().fix();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }

    public void toActivity(View view) {
        startActivity(new Intent(this, Chapter1Activity.class));
    }

    private void showToast(String msg) {
//        Intent intent = new Intent(this, DynamicLoadActivity.class);
//        intent.putExtra("value", "跳转到DynamicActivity");

//        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
