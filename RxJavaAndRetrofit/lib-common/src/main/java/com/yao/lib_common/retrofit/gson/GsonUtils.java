package com.yao.lib_common.retrofit.gson;

import com.google.gson.Gson;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/9 下午8:16
 * @Version:
 */

public class GsonUtils {
    private static Gson mGSon = new Gson();

    public static Gson instance() {
        return mGSon;
    }
}
