package com.yao.lib_mvp.model;

import com.yao.lib_common.network.RxService;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午3:06
 * @Version:
 */

public abstract class BaseModel implements IModel {

    /**
     * @param tClass
     * @param <T>
     * @return 返回服务接口对象实例
     */
    public <T> T createService(Class<T> tClass) {
        validateServiceInterface(tClass);
        return RxService.createRetrofit().create(tClass);
    }

    public <T> void validateServiceInterface(Class<T> tClass) {
        if (tClass == null) throw new NullPointerException("服务器接口不能为空");

        if (!tClass.isInterface())
            throw new IllegalArgumentException("API declarations must be interfaces.");

        if (tClass.getInterfaces().length > 0)
            throw new IllegalArgumentException("API interfaces must not extend other interfaces.");
    }

}
