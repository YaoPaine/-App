package com.yaopaine.androidart.chapter2.binderpool;

import android.os.RemoteException;
import android.util.Log;

import com.yaopaine.androidart.ICompute;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
public class IComputeImpl extends ICompute.Stub {
    @Override
    public void add(int x, int y) throws RemoteException {
        Log.e("TAG", "add: " + (x + y));
    }
}
