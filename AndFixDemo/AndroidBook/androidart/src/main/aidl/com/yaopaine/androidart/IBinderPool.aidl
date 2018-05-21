// IBinderPool.aidl
package com.yaopaine.androidart;

import android.os.IBinder;
// Declare any non-default types here with import statements

interface IBinderPool {
    IBinder queryBinder(in int binderCode);
}
