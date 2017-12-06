package com.yao.lib_common.rxjava.retrofit.gson;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yao.lib_common.rxjava.retrofit.model.entity.BaseResult;

import java.lang.reflect.Type;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午9:59
 * @Version:
 */

public class ResultJsonDeserializer implements JsonDeserializer<BaseResult<String>> {

    @Override
    public BaseResult<String> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseResult<String> baseResult = new BaseResult<>();
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            int code = jsonObject.get("code").getAsInt();
            baseResult.setCode(code);
            baseResult.setMessage(jsonObject.get("message").getAsString());
            if (code == BaseResult.getSuccessCod()) {
                JsonElement data = jsonObject.get("data");
                if (data != null) {
//                    ParameterizedType parameterizedType = (ParameterizedType) typeOfT;
//                    Type type = parameterizedType.getActualTypeArguments()[0];
//                    context.deserialize(data, ((ParameterizedType) typeOfT).getActualTypeArguments()[0]);//获取定义的泛型
                    baseResult.setData(data.toString());
                }
//                baseResult.setData(context.deserialize(data, type));
            }
        }
        return baseResult;
    }
}
