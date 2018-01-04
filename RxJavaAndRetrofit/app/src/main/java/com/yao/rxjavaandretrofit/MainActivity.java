package com.yao.rxjavaandretrofit;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yao.lib_common.rxjava.retrofit.gson.GsonUtils;
import com.yao.lib_common.rxjava.retrofit.model.api.ApiConstants;
import com.yao.lib_common.rxjava.retrofit.model.entity.UserModel;
import com.yao.lib_common.rxjava.retrofit.network.ApiService;
import com.yao.lib_common.rxjava.retrofit.network.RxService;
import com.yao.lib_common.rxjava.retrofit.observer.ApiCallBack;
import com.yao.lib_common.rxjava.retrofit.transformer.Transformer;
import com.yao.lib_common.sign.SignBuilder;
import com.yao.lib_mvp.R2;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.lib_mvp.mvp2.model.entity.GoodsEntity;
import com.yao.lib_common.rxjava.retrofit.model.entity.Country;
import com.yao.lib_common.rxjava.retrofit.model.entity.Result;
import com.yao.resource.constants.RouterConstants;
import com.yao.resource.utils.Utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private Map<String, Object> mMap = new HashMap<>();
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (!isEnabled()) {
            Intent intent = new Intent("android.settings.ACTION_NOTIFICATION_LISTENER_SETTINGS");
            startActivity(intent);
        } else {
            Toast.makeText(this, "已开启服务权限", Toast.LENGTH_SHORT).show();
        }

//        Gson gson = new Gson();
//        String jsonNumber = gson.toJson(100);       // 100
//        String jsonBoolean = gson.toJson(false);    // false
//        String jsonString = gson.toJson("String");

//        Log.e(TAG, "jsonNumber: " + jsonNumber);
//        Log.e(TAG, "jsonBoolean: " + jsonBoolean);
//        Log.e(TAG, "jsonString: " + jsonString);

//        initParams();
    }

    /**
     * {
     * "appChannel": "GooglePlay",
     * "appKey": "android_lk98f83",
     * "appTimestamp": "1513929431686",
     * "appTypeId": "0",
     * "appVersion": "6.11.2",
     * "cookieId": "511cad5b-6ae2-49a0-b644-b69a2660ba94",
     * "countryCode": "HK",
     * "currency": "HKD",
     * "lang": "0",
     * "parentId": 1876,
     * "seq": 1876,
     * "sign": "076dc36bddfd39fa987657e89bd1fd3d",
     * "terminalType": "1",
     * "userId": "14963605",
     * "userToken": "Mmnk8V5gDSz0pegyf7aUcgQQ"
     * }
     */
    private void initParams() {
        mMap.put("appChannel", "GooglePlay");
        mMap.put("appKey", "android_lk98f83");
        mMap.put("appTypeId", "0");
        mMap.put("appVersion", "6.11.2");
        mMap.put("cookieId", "511cad5b-6ae2-49a0-b644-b69a2660ba94");
        mMap.put("countryCode", "HK");
        mMap.put("currency", "HKD");
        mMap.put("lang", "0");
//        mMap.put("sign", "076dc36bddfd39fa987657e89bd1fd3d");
        mMap.put("terminalType", "1");
        mMap.put("userId", "14963605");
        mMap.put("userToken", "Mmnk8V5gDSz0pegyf7aUcgQQ");
    }

    @OnClick({R2.id.button1, R2.id.button2, R2.id.button3, R.id.button5})
    public void clickEvent(View view) {
//        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button1:
                ARouter.getInstance().build(RouterConstants.MODULE_B_ACTIVITY).navigation();
               /* intent.setClass(this, ModuleAActivity.class);
                startActivity(intent);*/
                break;
            case R.id.button2:
                /*intent.setClass(this, ModuleAActivity.class);
                intent.putExtra("module", "my name is he yao");
                startActivity(intent);*/
                Bundle bundle = new Bundle();
                bundle.putSerializable("user", new UserModel("heyao", "!@#qwe123"));
                ARouter.getInstance().build(RouterConstants.MODULE_D_ACTIVITY).with(bundle).navigation();
                break;
            case R.id.button3:
                Fragment fragment = (Fragment) ARouter.getInstance().build(RouterConstants.MODULE_B_FRAGMENT).navigation();
                Log.e(TAG, "fragment==null: " + (fragment == null));
                if (null != fragment) {
                    FragmentManager fragmentManager = getSupportFragmentManager();
                    FragmentTransaction transaction = fragmentManager.beginTransaction();
                    transaction.add(R.id.container, fragment).commit();
                }
                break;
            case R.id.button5:
//                String json = Utils.loadAssetsJson(this, "country/country.json");
//                Result<Country> result = GsonUtils.instance().fromJson(json, new TypeToken<Result<Country>>() {
//                }.getType());
//                List<Country> regionList = result.getRegionList();
                String json = Utils.loadAssetsJson(this, "country/middle.json");
                if (json == null) return;
                List<Country> regionList = GsonUtils.instance().fromJson(json, new TypeToken<List<Country>>() {
                }.getType());

                SignBuilder signBuilder = new SignBuilder();
                scrapData(regionList, signBuilder);

                /*if (regionList == null || regionList.size() < 1) return;
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
                            .subscribe(new ApiCallBack<Result<Country>>() {
                                @Override
                                public void onNext(Result<Country> data) {
                                    super.onNext(data);
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {

                                    }
                                    List<Country> regionLists = data.getRegionList();
                                    if (regionLists != null && regionLists.size() > 1) {
                                        Log.e(TAG, "onNext: " + GsonUtils.instance().toJson(regionLists));
                                        writeDataToStorage("region", regionLists, data.getSeq());
                                    }
                                    try {
                                        Thread.sleep(2000);
                                    } catch (InterruptedException e) {

                                    }
                                }
                            });
                }*/

                /*new Consumer<Result<Country>>() {
                    @Override
                    public void accept(Result<Country> countryResult) throws Exception {
                        Thread.sleep(2000);
                        List<Country> regionLists = countryResult.getRegionList();
                        if (regionLists != null && regionLists.size() > 1)
                            writeDataToStorage("region", regionLists, parentId);
                        Thread.sleep(2000);
                    }
                }*/
                break;
        }

    }

    public void scrapData(final List<Country> regionList, final SignBuilder signBuilder) {
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
                            List<Country> regionLists = regionResult.getRegionList();
                            /*try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                            if (regionLists != null && regionLists.size() > 1) {
                                writeDataToStorage("Region3", regionLists, regionResult.getSeq());
                            }*/
                            return regionLists;//获取二级地址
                        }
                    })
                    .flatMap(new Function<Country, ObservableSource<Result<Country>>>() {
                        @Override
                        public ObservableSource<Result<Country>> apply(Country country) throws Exception {
                            return observable(signBuilder, country.getRegionId());
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
//                            if (districtList != null && districtList.size() > 1) {
//                                writeDataToStorage("City4", districtList, cityResult.getSeq());
//                            }
                            writeDataToStorage("City5", districtList, cityResult.getSeq());
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
                            return observable(signBuilder, country.getRegionId());
                        }
                    })
                    .subscribe(new ApiCallBack<Result<Country>>() {
                        @Override
                        public void onNext(Result<Country> data) {
                            super.onNext(data);
                            List<Country> districtList = data.getRegionList();
//                            if (districtList != null && districtList.size() > 1) {
//                                writeDataToStorage("District4", districtList, data.getSeq());
//                            }
                            writeDataToStorage("District5                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                       ", districtList, data.getSeq());
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {

                            }
                        }
                    });
        }
    }

    private Observable<Result<Country>> observable(SignBuilder signBuilder, int cityId) {
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

    public void writeToDataDataFiles(Context context, List<Country> list) {
        File cacheDir = context.getFilesDir();
        for (Country region : list) {
            File saveFile = new File(cacheDir, region.getParentId() + "-" + region.getRegionId() + ".txt");
            ObjectOutputStream outStream = null;
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(saveFile);
                outStream = new ObjectOutputStream(fos);
                outStream.writeObject(region);
            } catch (FileNotFoundException e) {
            } catch (IOException e) {

            } finally {
                try {
                    if (fos != null) {
                        fos.close();
                    }
                    if (outStream != null) {
                        outStream.close();
                    }
                } catch (IOException e) {
                }
            }
        }
    }

    public void writeDataToStorage(String files, List<Country> list, int parentId) {
        String storageState = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(storageState)) {
//            File file = new File(Environment.getExternalStorageDirectory(), files + "/" + parentId + ".json");
            File file1 = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS)
                    , files + "/" + parentId + ".json");
            FileOutputStream outputStream = null;
            FileOutputStream fos = null;
            try {
                /*if (!file.exists()) {
                    File parentFile = file.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdir();
                    }
                    file.createNewFile();
                }*/
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

                /*if (fos != null) {
                    try {
                        fos.close();
                    } catch (IOException e) {

                    }
                }*/
            }
        }
    }

    /* Checks if external storage is available for read and write */
    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    /* Checks if external storage is available to at least read */
    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }


    private void getLocaleList() {
        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale availableLocale : availableLocales) {
            try {
                /*Log.e(TAG, "getCountry()：" + availableLocale.getCountry() +
                        "\tgetDisplayCountry()：" + availableLocale.getDisplayCountry() +
                        "\tgetDisplayLanguage()：" + availableLocale.getDisplayLanguage() +
                        "\tgetDisplayName()" + availableLocale.getDisplayName() +
                        "\tgetLanguage()：" + availableLocale.getLanguage() +
                        "\tgetISO3Country()：" + availableLocale.getISO3Country() +
                        "\tgetISO3Language()：" + availableLocale.getISO3Language() +
                        "\tgetDisplayVariant()：" + availableLocale.getDisplayVariant()
                );*/
//                Log.e(TAG, "getLanguage(): " + availableLocale.getLanguage() +
//                        "\tgetDisplayLanguage(): " + availableLocale.getDisplayName());
            } catch (Exception e) {
                Log.e(TAG, "海上生明月,天涯共此时: " + e);
            }

        }
    }

    private void testRxRetrofit() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("productId", 46);
        hashMap.put("phoneId", "355905070668067");
        hashMap.put("accessToken", "0b4d095d-24cb-4350-a027-4eb0dd0b002b");
        hashMap.put("languageId", 1);
        hashMap.put("coinId", 1);
        hashMap.put("osType", 0);
        hashMap.put("versionCode", 16);
        hashMap.put("osVersionName", 7.0);
        hashMap.put("osVersionCode", 24);
        hashMap.put("chn", "google");
        hashMap.put("uuid", "a2adc10d-94a3-41b0-bd6b-bc4da76bad58");
        RxService.createRetrofit().create(ApiService.class)
                .postWithMapParam("goods/detailNew", hashMap)
                .compose(new Transformer<>(GoodsEntity.class))
                .subscribe(new ApiCallBack<GoodsEntity>() {
                    @Override
                    public void onSuccessIsNull() {
                        super.onSuccessIsNull();
                        Log.e(TAG, "onSuccessIsNull: " + Thread.currentThread().getName());
                    }

                    @Override
                    public void onSuccessNotNull(GoodsEntity goodsEntity) {
                        Log.e(TAG, "onSuccessNotNull: " + Thread.currentThread().getName());
                        super.onSuccessNotNull(goodsEntity);
                    }
                });
    }

    private boolean isEnabled() {
        String pkgName = getPackageName();
        String flat = Settings.Secure.getString(getContentResolver(), "enabled_notification_listeners");
        if (!TextUtils.isEmpty(flat)) {
            final String[] names = flat.split(":");
            for (int i = 0; i < names.length; i++) {
                ComponentName cn = ComponentName.unflattenFromString(names[i]);
                if (cn != null) {
                    if (TextUtils.equals(pkgName, cn.getPackageName())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
