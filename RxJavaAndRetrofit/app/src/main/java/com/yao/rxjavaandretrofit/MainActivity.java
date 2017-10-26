package com.yao.rxjavaandretrofit;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.alibaba.android.arouter.launcher.ARouter;
import com.yao.lib_common.model.UserModel;
import com.yao.lib_mvp.BaseActivity;
import com.yao.lib_mvp.R2;
import com.yao.resource.constants.RouterConstants;

import java.util.Locale;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Locale[] availableLocales = Locale.getAvailableLocales();
        for (Locale availableLocale : availableLocales) {
            try {
                Log.e(TAG, "getCountry()：" + availableLocale.getCountry() +
                        "\tgetDisplayCountry()：" + availableLocale.getDisplayCountry() +
                        "\tgetDisplayLanguage()：" + availableLocale.getDisplayLanguage() +
                        "\tgetDisplayName()" + availableLocale.getDisplayName() +
                        "\tgetLanguage()：" + availableLocale.getLanguage() +
                        "\tgetISO3Country()：" + availableLocale.getISO3Country() +
                        "\tgetISO3Language()：" + availableLocale.getISO3Language() +
                        "\tgetDisplayVariant()：" + availableLocale.getDisplayVariant()
                );
            } catch (Exception e) {
                Log.e(TAG, "onCreate: " + e);
            }

        }
    }

    @OnClick({R2.id.button1, R2.id.button2, R2.id.button3})
    public void clickEvent(View view) {
        Log.e(TAG, "clickEvent: ");
//        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.button1:
                ARouter.getInstance().build(RouterConstants.MODULE_A_ACTIVITY).navigation();
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

                break;
        }
    }
}
