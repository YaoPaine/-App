package com.yao.rxjavaandretrofit;

import android.os.Environment;
import android.util.Log;

import com.google.gson.reflect.TypeToken;
import com.yao.lib_common.rxjava.retrofit.gson.GsonUtils;
import com.yao.lib_common.rxjava.retrofit.model.api.ApiConstants;
import com.yao.lib_common.rxjava.retrofit.model.entity.Country;
import com.yao.lib_common.rxjava.retrofit.model.entity.Result;
import com.yao.lib_common.rxjava.retrofit.network.ApiService;
import com.yao.lib_common.rxjava.retrofit.network.RxService;
import com.yao.lib_common.rxjava.retrofit.observer.ApiCallBack;
import com.yao.lib_common.sign.SignBuilder;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by yaopaine on 12/27/17.
 */

public class Spider {

    private static final String TAG = "Spider";


    public static void main(String[] args) {
        String json = "";
        Result<Country> result = GsonUtils.instance().fromJson(json, new TypeToken<Result<Country>>() {
        }.getType());

        SignBuilder signBuilder = new SignBuilder();
        List<Country> regionList = result.getRegionList();
        scrapData(regionList, signBuilder);
    }

    public static void scrapData(final List<Country> regionList, final SignBuilder signBuilder) {
        if (regionList == null || regionList.size() < 1) return;
        for (Country country : regionList) {
            int regionId = country.getRegionId();
            HashMap<String, Object> objectHashMap = signBuilder.getRegionList(regionId);
            RxService.createRetrofit()
                    .create(ApiService.class)
                    .postWithMap(ApiConstants.getRegionByParentNew, objectHashMap)
                    .subscribeOn(Schedulers.single())
                    .delay(3000, TimeUnit.MILLISECONDS)
                    .subscribeOn(Schedulers.io())
                    .unsubscribeOn(Schedulers.io())
                    .observeOn(Schedulers.io())
                    .flatMapIterable(new Function<Result<Country>, Iterable<Country>>() {
                        @Override
                        public Iterable<Country> apply(Result<Country> regionResult) throws Exception {
//                            try {
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//
//                            }
//                            List<Country> regionLists = regionResult.getRegionList();
//                            if (regionLists != null && regionLists.size() > 1) {
//                                Log.e(TAG, "region: " + GsonUtils.instance().toJson(regionLists));
//                                writeDataToStorage("region", regionLists, regionResult.getSeq());
//                            }
//                            try {
//                                Thread.sleep(2000);
//                            } catch (InterruptedException e) {
//
//                            }
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                            return regionResult.getRegionList();//获取二级地址
                        }
                    })
                    .flatMap(new Function<Country, ObservableSource<Result<Country>>>() {
                        @Override
                        public ObservableSource<Result<Country>> apply(Country country) throws Exception {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                            if (country != null) {
                                return observable(signBuilder, country.getRegionId());
                            } else {
                                return null;
                            }
                        }
                    })
                    .flatMapIterable(new Function<Result<Country>, Iterable<Country>>() {
                        @Override
                        public Iterable<Country> apply(Result<Country> cityResult) throws Exception {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                            List<Country> districtList = cityResult.getRegionList();
                            if (districtList != null && districtList.size() > 1) {
//                                Log.e(TAG, "city: " + GsonUtils.instance().toJson(districtList));
                                writeDataToStorage("city", districtList, cityResult.getSeq());
                            }
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                            return districtList;//获取三级地址
                        }
                    })
                    .flatMap(new Function<Country, ObservableSource<Result<Country>>>() {
                        @Override
                        public ObservableSource<Result<Country>> apply(Country country) throws Exception {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                            if (country != null) {
                                return observable(signBuilder, country.getRegionId());
                            } else {
                                return null;
                            }
                        }
                    })
                    .subscribe(new ApiCallBack<Result<Country>>() {
                        @Override
                        public void onNext(Result<Country> data) {
                            super.onNext(data);
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                            List<Country> districtList = data.getRegionList();
                            if (districtList != null && districtList.size() > 1) {
//                                Log.e(TAG, "district: " + GsonUtils.instance().toJson(districtList));
                                writeDataToStorage("district", districtList, data.getSeq());
                            }
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                        }
                    });
        }
    }

    private static Observable<Result<Country>> observable(SignBuilder signBuilder, int cityId) {
        HashMap<String, Object> objectHashMap = signBuilder.getRegionList(cityId);
        return RxService.createRetrofit()
                .create(ApiService.class)
                .postWithMap(ApiConstants.getRegionByParentNew, objectHashMap)
                .subscribeOn(Schedulers.single())
                .delay(3000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(Schedulers.io());
    }

    public static void writeDataToStorage(String files, List<Country> list, int parentId) {
        String storageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(storageState)) {
//            File file = new File(Environment.getExternalStorageDirectory(), files + "/" + parentId + ".json");
            File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    , files + "/" + parentId + ".json");
            FileOutputStream outputStream = null;
            FileOutputStream fos = null;
            try {
//                if (!file.exists()) {
//                    File parentFile = file.getParentFile();
//                    if (!parentFile.exists()) {
//                        parentFile.mkdir();
//                    }
//                    file.createNewFile();
//                }
                if (!file1.exists()) {
                    File parentFile = file1.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdir();
                    }
                    file1.createNewFile();
                }

//                fos = new FileOutputStream(file);
                outputStream = new FileOutputStream(file1);
                String json = GsonUtils.instance().toJson(list);
                byte[] bytes = json.getBytes("UTF-8");
//                fos.write(bytes);
                outputStream.write(bytes);
            } catch (FileNotFoundException e) {
            } catch (UnsupportedEncodingException e) {

            } catch (IOException e) {
            } finally {
                if (outputStream != null) {
                    try {
                        outputStream.close();
                    } catch (IOException e) {

                    }
                }

//                if (fos != null) {
//                    try {
//                        fos.close();
//                    } catch (IOException e) {
//
//                    }
//                }
            }
        }
    }
}
