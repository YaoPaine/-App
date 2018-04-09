package com.yao.rxjavaandretrofit;

import com.yao.lib_common.sign.SignBuilder;

import java.util.HashMap;

/**
 * @Description
 * @AuthorCreated yaopaine
 * @Version 1.0
 * @Time 2/9/18
 */

public class SingTest {

    public static void main(String[] args) {
        HashMap<String, Object> hashMap = new HashMap<>();
        /*hashMap.put("parentId", paramInt);
        hashMap.put("seq", paramInt);*/
        signRequestParams(hashMap);
        System.out.println("2575d9668de069d5fb9dd741c453a65a".equals("2575d9668de069d5fb9dd741c453a65a"));
    }

    /**
     * {
     * "appChannel": "GooglePlay",
     * "appKey": "android_lk98f83",
     * "appTimestamp": "1518165997565",
     * "appTypeId": "0",
     * "appVersion": "6.3.1",
     * "cookieId": "4f623e19-caa4-4d91-9911-50241a8bcf48",
     * "countryCode": "HK",
     * "currency": "HKD",
     * "lang": "0",
     * "sign": "2575d9668de069d5fb9dd741c453a65a",
     * "terminalType": "1"
     * }
     */
    private static void signRequestParams(HashMap<String, Object> map) {
        // String str = (String)
        // this.mRequestCreatorHelper.getDefaultRequestParams().getParamsMap().get("appVersion");
        //String str = "6.11.2";
        String str = "6.3.1";
        //paramString = "http://app.jollychic.com/address/getRegionByParentNew.do?" + System.currentTimeMillis();
        // paramHashMap = appendDefaultParams(paramHashMap);
        // appChannel
        map.put("appChannel", "GooglePlay");
        // appTypeId
        map.put("appTypeId", "0");
        // appVersion
        map.put("appVersion", "6.3.1");
        // cookieId
        map.put("cookieId", "4f623e19-caa4-4d91-9911-50241a8bcf48");
        // countryCode
        map.put("countryCode", "HK");
        // currency
        map.put("currency", "HKD");
        // lang
        map.put("lang", "0");
        // terminalType
        map.put("terminalType", "1");
        // userId
        /**map.put("userId", "14963605");**/
        // userToken
        /**map.put("userToken", "Mmnk8V5gDSz0pegyf7aUcgQQ");**/
        new SignBuilder().doSign2("", map, str);
    }
}
