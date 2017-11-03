package com.yao.moduleb;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.gson.Gson;
import com.yao.lib_common.model.entity.BaseResult;
import com.yao.lib_common.network.INewService;
import com.yao.lib_common.network.RxService;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.moduleb.model.entity.GoodsEntity;
import com.yao.resource.constants.RouterConstants;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@Route(path = RouterConstants.MODULE_B_ACTIVITY)
public class ModuleBActivity extends BaseActivity {

    @BindView(R2.id.button_module_b)
    Button btn;

    private AttributeWindow mWindow;
    private GoodsEntity goodsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_b_main_activity);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("productId", 46);
        hashMap.put("phoneId", "355905070668067");
        hashMap.put("accessToken", "97dc87cc-76ab-489e-bcb2-f53c3afab183");
        hashMap.put("languageId", 1);
        hashMap.put("coinId", 1);
        hashMap.put("osType", 0);
        hashMap.put("versionCode", 16);
        hashMap.put("osVersionName", 7.0);
        hashMap.put("osVersionCode", 24);
        hashMap.put("chn", "google");
        hashMap.put("uuid", "a2adc10d-94a3-41b0-bd6b-bc4da76bad58");
        RxService.createRetrofit()
                .create(INewService.class).postWithMapParam("goods/detailNew", hashMap)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BaseResult<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BaseResult<String> stringBaseResult) {
//                        Log.d(TAG, "" + stringBaseResult.getData());
                        if (stringBaseResult.getCode() == 0) {
                            String data = stringBaseResult.getData();
                            goodsEntity = new Gson().fromJson(data, GoodsEntity.class);
                        } else {
                            Toast.makeText(ModuleBActivity.this, stringBaseResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick({R2.id.button_module_b})
    public void clickEvent(View view) {
        if ((view.getId() == R.id.button_module_b) && (goodsEntity != null)) {
            /*Intent intent = new Intent();
            intent.setComponent(new ComponentName("com.yao.rxjavaandretrofit", "com.yao.modulec.ModuleCActivity"));
            startActivity(intent);*/
            /*Intent intent = getIntent();
            if (null != intent) {
                UserModel user = (UserModel) intent.getExtras().getSerializable("user");
                if (user != null) {
                    textView.setText("name:" + user.getName() + "===password:" + user.getPassword());
                }
            }*/

            if (mWindow == null) {
                mWindow = new AttributeWindow(this);
                mWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        background(1.0f);
                    }
                });
                mWindow.initWindow(goodsEntity, null);
            }

            if (!mWindow.isShowing()) {
                mWindow.showAsDropDown(btn, 0, 0);
                background(0.3f);
            }
        }
    }

    public void background(float alpha) {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = alpha;
        window.setAttributes(attributes);
    }
}
