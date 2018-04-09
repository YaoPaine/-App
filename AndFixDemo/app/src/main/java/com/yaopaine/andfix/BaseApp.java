package com.yaopaine.andfix;

import android.app.Application;

import com.alipay.euler.andfix.patch.PatchManager;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 4/9/18
 */
public class BaseApp extends Application {

    private PatchManager patchManager;

    @Override
    public void onCreate() {
        super.onCreate();
        patchManager = new PatchManager(this);
        String appVersion = String.valueOf(BuildConfig.VERSION_CODE);
        patchManager.init(appVersion);//current version

        patchManager.loadPatch();
    }

    public PatchManager getPachManager() {
        return patchManager;
    }
}
