package com.yaopaine.androidart.chapter2.binderpool;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.yaopaine.androidart.IBinderPool;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
public class BinderPool {

    private static volatile BinderPool sInstance;
    private Context mContext;

    private BinderPool(Context context) {
        mContext = context.getApplicationContext();
        connectBinderPoolService();
    }

    private synchronized void connectBinderPoolService() {

        Intent intent = new Intent(mContext, BinderPoolService.class);
        mContext.bindService(intent, mConnection, Context.BIND_AUTO_CREATE);
    }

    private IBinderPool mBinderPool;
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mBinderPool = IBinderPool.Stub.asInterface(service);
            Log.e("TAG", "onServiceConnected: " + mBinderPool);
            try {
                mBinderPool.asBinder().linkToDeath(mDeathRecipient, 0);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    };

    private IBinder.DeathRecipient mDeathRecipient = new IBinder.DeathRecipient() {
        @Override
        public void binderDied() {
            Log.e("TAG", "binderDied: ");
            mBinderPool.asBinder().unlinkToDeath(this, 0);
            mBinderPool = null;
            connectBinderPoolService();
        }
    };

    public static final int BINDER_CODE_ENCRTY = 1;
    public static final int BINDER_CODE_COMPUTE = 2;

    public static class BinderPoolImpl extends IBinderPool.Stub {

        @Override
        public IBinder queryBinder(int binderCode) {
            IBinder binder = null;
            switch (binderCode) {
                case BINDER_CODE_ENCRTY:
                    binder = new ISecurityImpl();
                    break;
                case BINDER_CODE_COMPUTE:
                    binder = new IComputeImpl();
                    break;
            }
            Log.e("TAG", "queryBinder: " + binder);
            return binder;
        }
    }

    public static BinderPool getInstance(Context context) {
        if (sInstance == null) {
            synchronized (BinderPool.class) {
                if (sInstance == null) {
                    sInstance = new BinderPool(context);
                }
            }
        }
        return sInstance;
    }

    public IBinder queryBinder(int binderCode) {
        IBinder binder = null;
        try {
            if (mBinderPool != null) {
                binder = mBinderPool.queryBinder(binderCode);
            }
        } catch (RemoteException e) {
        }
        return binder;
    }
}
