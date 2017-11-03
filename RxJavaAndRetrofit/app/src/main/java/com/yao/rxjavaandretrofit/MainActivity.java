package com.yao.rxjavaandretrofit;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.google.gson.Gson;
import com.yao.lib_common.model.entity.BaseResult;
import com.yao.lib_common.model.entity.UserModel;
import com.yao.lib_common.network.INewService;
import com.yao.lib_common.network.RxService;
import com.yao.lib_mvp.R2;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.resource.constants.RouterConstants;

import java.util.HashMap;
import java.util.Locale;

import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Gson gson = new Gson();
        String jsonNumber = gson.toJson(100);       // 100
        String jsonBoolean = gson.toJson(false);    // false
        String jsonString = gson.toJson("String");

//        Log.e(TAG, "jsonNumber: " + jsonNumber);
//        Log.e(TAG, "jsonBoolean: " + jsonBoolean);
//        Log.e(TAG, "jsonString: " + jsonString);

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

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("productId", 3900);
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
//        String detail = new Gson().toJson(hashMap);
        RxService.createRetrofit().create(INewService.class)
                .postWithMapParam("home/content", hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.e(TAG, "onSubscribe: ");
                    }

                    @Override
                    public void onNext(BaseResult<String> stringBaseResult) {
                        Log.e(TAG, "onNext: " + stringBaseResult.getData());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
                    }

                    @Override
                    public void onComplete() {
                        Log.e(TAG, "onComplete: ");
                    }
                });
    }

    @OnClick({R2.id.button1, R2.id.button2, R2.id.button3})
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
                ARouter.getInstance().build(RouterConstants.MODULE_A_ACTIVITY).with(bundle).navigation();
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
        }
    }
}
