package com.yao.moduleb;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.google.android.flexbox.FlexboxLayout;
import com.google.gson.Gson;
import com.yao.lib_common.retrofit.model.entity.BaseResult;
import com.yao.lib_common.retrofit.network.INewService;
import com.yao.lib_common.retrofit.network.RxService;
import com.yao.lib_mvp.base.BaseActivity;
import com.yao.moduleb.model.entity.AttrValuesEntity;
import com.yao.moduleb.model.entity.AttrsEntity;
import com.yao.moduleb.model.entity.GoodsEntity;
import com.yao.moduleb.model.entity.SkuAttrEntity;
import com.yao.moduleb.model.entity.SkuEntity;
import com.yao.moduleb.view.TagTextView;
import com.yao.resource.constants.RouterConstants;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindDimen;
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

//    @BindView(R2.id.tv_attr_name)
//    TagTextView tvAttrName;

    @BindView(R2.id.flex_layout)
    FlexboxLayout mFlexboxlayout;

    @BindDimen(R2.dimen.dp4)
    int dp4;

    private AttributeWindow mWindow;
    private GoodsEntity goodsEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.module_b_main_activity);

        /*String data = readData().trim();
        if (!TextUtils.isEmpty(data)) {
            goodsEntity = new Gson().fromJson(data, GoodsEntity.class);
        }*/
        for (int i = 0; i < 10; i++) {
            TagTextView tagTextView = (TagTextView) LayoutInflater.from(this).inflate(R.layout.module_b_layout_tag_item, null);
            if (i >= 3 && i % 3 == 0) {
                tagTextView.setText("这是第[" + i + "]个tag");
            } else if (i % 2 == 0) {
                tagTextView.setText("这是[" + i + "]");
            } else {
                tagTextView.setText("[" + i + "]");
            }

            FlexboxLayout.LayoutParams layoutParams = new FlexboxLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            layoutParams.setMargins(dp4, dp4, dp4, dp4);
            mFlexboxlayout.addView(tagTextView, layoutParams);
        }
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
                        Log.e(TAG, "onNext: " + stringBaseResult.getMessage());
                        if (stringBaseResult.getCode() == 0) {
                            String data = stringBaseResult.getData();
                            goodsEntity = new Gson().fromJson(data, GoodsEntity.class);

                            List<AttrsEntity> attrs = goodsEntity.getAttrs();
                            for (AttrsEntity attrsEntity : attrs) {
                                int keyId = attrsEntity.getKeyId();
                                List<AttrValuesEntity> attrValues = attrsEntity.getAttrValues();

                                if (keyId == 5) {
                                    AttrValuesEntity valuesEntity = new AttrValuesEntity();
                                    valuesEntity.setValue("red");
                                    valuesEntity.setValueId(20);
                                    attrValues.add(valuesEntity);

                                    AttrValuesEntity valuesEntity2 = new AttrValuesEntity();
                                    valuesEntity2.setValue("blue");
                                    valuesEntity2.setValueId(21);
                                    attrValues.add(valuesEntity2);

                                } else if (keyId == 6) {
                                    AttrValuesEntity valuesEntity = new AttrValuesEntity();
                                    valuesEntity.setValue("内存: 64g");
                                    valuesEntity.setValueId(389);
                                    attrValues.add(valuesEntity);

                                    AttrValuesEntity valuesEntity2 = new AttrValuesEntity();
                                    valuesEntity2.setValue("内存: 128g");
                                    valuesEntity2.setValueId(390);
                                    attrValues.add(valuesEntity2);

                                    AttrValuesEntity valuesEntity3 = new AttrValuesEntity();
                                    valuesEntity3.setValue("内存:8g");
                                    valuesEntity3.setValueId(391);
                                    attrValues.add(valuesEntity3);

                                    AttrValuesEntity valuesEntity4 = new AttrValuesEntity();
                                    valuesEntity4.setValue("内存: 258g");
                                    valuesEntity4.setValueId(392);
                                    attrValues.add(valuesEntity4);

                                } else if (keyId == 333) {
                                    AttrValuesEntity valuesEntity = new AttrValuesEntity();
                                    valuesEntity.setValue("RAM: 16g");
                                    valuesEntity.setValueId(285);
                                    attrValues.add(valuesEntity);

                                    AttrValuesEntity valuesEntity2 = new AttrValuesEntity();
                                    valuesEntity2.setValue("RAM:  64g");
                                    valuesEntity2.setValueId(185);
                                    attrValues.add(valuesEntity2);

                                    AttrValuesEntity valuesEntity3 = new AttrValuesEntity();
                                    valuesEntity3.setValue("RAM:  128g");
                                    valuesEntity3.setValueId(85);
                                    attrValues.add(valuesEntity3);
                                }
                            }

                            List<SkuEntity> skus = goodsEntity.getSkus();
                            for (int i = 0; i < 8; i++) {
                                SkuEntity skuEntity = new SkuEntity();

                                List<SkuAttrEntity> attrEntities = new ArrayList<>();
                                SkuAttrEntity skuAttrEntity1 = new SkuAttrEntity();
                                skuAttrEntity1.setKeyId(5);

                                SkuAttrEntity skuAttrEntity2 = new SkuAttrEntity();
                                skuAttrEntity2.setKeyId(6);

                                SkuAttrEntity skuAttrEntity3 = new SkuAttrEntity();
                                skuAttrEntity3.setKeyId(333);

                                if (i == 0) {
                                    skuAttrEntity1.setValueId(19);
                                    skuAttrEntity1.setValueName("black");
                                    skuAttrEntity2.setValueId(388);
                                    skuAttrEntity2.setValueName("8g");
                                    skuAttrEntity3.setValueId(285);
                                    skuAttrEntity3.setValueName("RAM: 16g");
                                } else if (i == 1) {
                                    skuAttrEntity1.setValueId(19);
                                    skuAttrEntity1.setValueName("black");
                                    skuAttrEntity2.setValueId(388);
                                    skuAttrEntity2.setValueName("8g");
                                    skuAttrEntity3.setValueId(185);
                                    skuAttrEntity3.setValueName("RAM:  64g");
                                } else if (i == 2) {
                                    skuAttrEntity1.setValueId(19);
                                    skuAttrEntity1.setValueName("black");
                                    skuAttrEntity2.setValueId(389);
                                    skuAttrEntity2.setValueName("内存: 64g");
                                    skuAttrEntity3.setValueId(285);
                                    skuAttrEntity3.setValueName("RAM:  16g");
                                } else if (i == 3) {
                                    skuAttrEntity1.setValueId(19);
                                    skuAttrEntity1.setValueName("black");
                                    skuAttrEntity2.setValueId(389);
                                    skuAttrEntity2.setValueName("内存: 64g");
                                    skuAttrEntity3.setValueId(185);
                                    skuAttrEntity3.setValueName("RAM:  64g");
                                } else if (i == 4) {
                                    skuAttrEntity1.setValueId(19);
                                    skuAttrEntity1.setValueName("black");
                                    skuAttrEntity2.setValueId(390);
                                    skuAttrEntity2.setValueName("内存: 128g");
                                    skuAttrEntity3.setValueId(385);
                                    skuAttrEntity3.setValueName("1g");
                                } else if (i == 5) {
                                    skuAttrEntity1.setValueId(21);
                                    skuAttrEntity1.setValueName("blue");
                                    skuAttrEntity2.setValueId(388);
                                    skuAttrEntity2.setValueName("8g");
                                    skuAttrEntity3.setValueId(385);
                                    skuAttrEntity3.setValueName("1g");
                                } else if (i == 6) {
                                    skuAttrEntity1.setValueId(21);
                                    skuAttrEntity1.setValueName("blue");
                                    skuAttrEntity2.setValueId(389);
                                    skuAttrEntity2.setValueName("内存: 64g");
                                    skuAttrEntity3.setValueId(285);
                                    skuAttrEntity3.setValueName("RAM:  16g");
                                } else if (i == 7) {
                                    skuAttrEntity1.setValueId(21);
                                    skuAttrEntity1.setValueName("blue");
                                    skuAttrEntity2.setValueId(390);
                                    skuAttrEntity2.setValueName("内存: 128g");
                                    skuAttrEntity3.setValueId(185);
                                    skuAttrEntity3.setValueName("RAM:  64g");
                                }
                                attrEntities.add(skuAttrEntity1);
                                attrEntities.add(skuAttrEntity2);
                                attrEntities.add(skuAttrEntity3);
                                skuEntity.setAttrs(attrEntities);
                                skus.add(skuEntity);
                            }

                            for (SkuEntity skuEntity : skus) {
                                StringBuilder builder = new StringBuilder();
                                List<SkuAttrEntity> skuEntityAttrs = skuEntity.getAttrs();
                                for (SkuAttrEntity skuAttrEntity : skuEntityAttrs) {
                                    int keyId = skuAttrEntity.getKeyId();
                                    int valueId = skuAttrEntity.getValueId();
                                    builder.append("#").append(keyId).append("#").append(valueId);
                                }
                                skuEntity.setSkuCombination(builder.toString());
                            }

                        } else {
                            Toast.makeText(ModuleBActivity.this, stringBaseResult.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError: " + e);
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

    private String readData() {
        InputStream inputStream = null;
        ByteArrayOutputStream bos = null;
        try {
            inputStream = getAssets().open("data.txt");
            bos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            while (inputStream.read(buffer) != -1) {
                bos.write(buffer, 0, buffer.length);
            }
            bos.flush();
            return new String(bos.toByteArray());
        } catch (IOException e) {

        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                }
            }
        }

        return "";
    }
}
