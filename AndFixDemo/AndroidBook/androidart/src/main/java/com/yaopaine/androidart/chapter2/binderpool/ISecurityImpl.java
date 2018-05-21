package com.yaopaine.androidart.chapter2.binderpool;

import android.os.RemoteException;
import android.util.Log;

import com.yaopaine.androidart.ISecurity;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 5/21/18
 */
public class ISecurityImpl extends ISecurity.Stub {
    @Override
    public String encry(String text) throws RemoteException {
        Log.e("TAG", "encry: " + text);
        return text;
    }

    @Override
    public String decode(String text) throws RemoteException {
        Log.e("TAG", "decode: " + text);
        return text;
    }
}
