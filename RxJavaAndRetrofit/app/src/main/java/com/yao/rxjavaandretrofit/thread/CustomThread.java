package com.yao.rxjavaandretrofit.thread;

import android.content.Context;

import com.yao.lib_common.rxjava.retrofit.model.api.ApiConstants;
import com.yao.lib_common.rxjava.retrofit.model.entity.Country;
import com.yao.lib_common.rxjava.retrofit.model.entity.Result;
import com.yao.lib_common.rxjava.retrofit.network.ApiService;
import com.yao.lib_common.rxjava.retrofit.network.RxService;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Map;

import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yaopaine on 12/22/17.
 */

public class CustomThread extends Thread {
    private Context context;
    private int regionId;
    private int i = 1;
    private Map<String, Object> mMap;

    public CustomThread(Context context, Map<String, Object> map) {
        this.mMap = map;
        this.context = context;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public void setI(int i) {
        this.i = i;
    }

    @Override
    public void run() {
        super.run();
        try {
            Thread.sleep(2000 * i);
        } catch (Exception e) {

        }
    }


}
