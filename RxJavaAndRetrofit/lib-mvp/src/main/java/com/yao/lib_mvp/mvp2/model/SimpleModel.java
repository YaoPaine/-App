package com.yao.lib_mvp.mvp2.model;

import com.yao.lib_common.rxjava.retrofit.network.ApiService;
import com.yao.lib_common.rxjava.retrofit.network.RxService;
import com.yao.lib_common.rxjava.retrofit.transformer.Transformer;
import com.yao.lib_mvp.mvp2.model.entity.GoodsEntity;

import java.util.HashMap;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/12/7 上午9:29
 * @Version:
 */

public class SimpleModel implements IMvpBaseModel {

    @Inject
    public SimpleModel() {

    }

    public Observable<GoodsEntity> requestData() {
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

        return RxService.createRetrofit()
                .create(ApiService.class)
                .postWithMapParam("goods/detailNew", hashMap)
                .compose(new Transformer<>(GoodsEntity.class));
    }

    public void interruptHttp() {

    }
}
