package com.yaopaine.androidart.chapter2.binderpool;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.yaopaine.androidart.IBinderPool;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
public class BinderPoolService extends Service {

    private IBinderPool.Stub mBinder = new BinderPool.BinderPoolImpl();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }
}
