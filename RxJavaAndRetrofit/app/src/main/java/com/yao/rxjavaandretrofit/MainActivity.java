package com.yao.rxjavaandretrofit;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
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
import com.yao.lib_common.model.entity.UserModel;
import com.yao.lib_common.network.INewService;
import com.yao.lib_common.network.RxService;
import com.yao.lib_common.observer.ApiCallBack;
import com.yao.lib_mvp.R2;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.moduleb.model.entity.GoodsEntity;
import com.yao.resource.constants.RouterConstants;

import java.util.HashMap;
import java.util.Locale;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

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
        RxService.createRetrofit().create(INewService.class)
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
