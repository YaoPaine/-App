package com.yao.lib_common.network;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yao.lib_common.model.entity.BaseResult;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * @Description:
 * @Author: YaoPaine
 * @CreateDate: 2017/11/2 下午9:59
 * @Version:
 */

public class ResultJsonDeserializer implements JsonDeserializer<BaseResult<?>> {

    @Override
    public BaseResult<?> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        BaseResult baseResult = new BaseResult();
        if (json.isJsonObject()) {
            JsonObject jsonObject = json.getAsJsonObject();
            int code = jsonObject.get("code").getAsInt();
            baseResult.setCode(code);
            baseResult.setMessage(jsonObject.get("message").getAsString());
            if (code == 0) {
                JsonElement data = jsonObject.get("data");
                if (data != null) {
                    ParameterizedType parameterizedType = (ParameterizedType) typeOfT;
                    Type type = parameterizedType.getActualTypeArguments()[0];
                    baseResult.setData(data.toString());
                }
//                baseResult.setData(context.deserialize(data, type));
            }
        }
        return baseResult;
    }
}
