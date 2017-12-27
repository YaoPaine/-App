package com.yao.lib_common.rxjava.retrofit.gson;

import android.text.TextUtils;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.yao.lib_common.rxjava.retrofit.model.entity.Result;

import java.lang.reflect.Type;

/**
 * Created by yaopaine on 12/22/17.
 */

public class ResultJsonDeserializer implements JsonDeserializer<Result<String>> {

    @Override
    public Result<String> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Result<String> result = new Result<>();
        if (jsonElement.isJsonObject()) {
            JsonObject jsonObject = jsonElement.getAsJsonObject();
            /**
             * "message": "Success",
             "messageCode": "0",
             "messageType": 0,
             "regionList":[]
             "result": 0
             "seq": 25998
             */
            String message = jsonObject.get("message").getAsString();
            String messageCode = jsonObject.get("messageCode").getAsString();
            int messageType = jsonObject.get("messageType").getAsInt();
            JsonElement element = jsonObject.get("regionList");
            if (element.isJsonArray()) {
                JsonArray jsonArray = element.getAsJsonArray();
                
            }
        }
        return result;
    }
}
