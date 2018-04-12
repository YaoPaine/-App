package com.yaopaine.andfix;

import android.app.Application;
import android.util.Log;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 4/9/18
 */
public class BaseApp extends Application {

    private String TAG = "BaseApp";

    private PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        patchManager = new PatchManager(this);
        Log.e(TAG, "onCreate: " + (getFilesDir().exists() ? getFilesDir().getAbsolutePath() : ""));
        String appVersion = String.valueOf(BuildConfig.VERSION_CODE);
        patchManager.init(appVersion);//current version

        patchManager.loadPatch();
    }

    public PatchManager getPachManager() {
        return patchManager;
    }
}
