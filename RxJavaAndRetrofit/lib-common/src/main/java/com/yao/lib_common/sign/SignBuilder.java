package com.yao.lib_common.sign;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Pattern;

import com.google.gson.Gson;

public class SignBuilder {

    // private Parameter parameter;
    private Data data;
    private SaltHandle handle;
    private String salt;

    /**
     * 对hashMap中的参数进行签名
     *
     * @param paramString1
     * @param paramHashMap
     * @param paramString2
     */
    public void doSign(String paramString1, HashMap<String, Object> paramHashMap, String paramString2) {
        if (functionA(paramHashMap, paramString2)) {
            functionB(paramHashMap);
            // paramString1 = c();
            Data data = getParameter();
            paramHashMap.put(data.d(), data.e());// key="appTimestamp",value=时间
            paramHashMap.put(data.f(), data.g());// key="appKey",value="sign"
            paramHashMap.put(data.h(), sign(paramHashMap, data.i()));// key="android_lk98f83",flvjk342589fdgjl34m9sdufg234oiy
        }
        while (paramHashMap == null) {
            return;
        }
        paramHashMap.put("E101", getSaltString() + "," + paramHashMap.get(getSaltString()) + "," + paramString2);
    }

    public String sign(HashMap<String, Object> paramHashMap, String paramString) {
        // Object localObject1 = paramHashMap.keySet();
        Set<String> keySet = paramHashMap.keySet();
        // localObject1 = (String[]) ((Set) localObject1).toArray(new String[((Set)
        // localObject1).size()]);
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        // Arrays.sort((Object[]) localObject1);
        Arrays.sort(keyArray);
        StringBuilder localStringBuilder = new StringBuilder();

        int j = keyArray.length;
        int i = 0;
        while (i < j) {
            // Object localObject2 = localObject1[i];
            String key = keyArray[i];
            localStringBuilder.append(key);
            Object value = paramHashMap.get(key);
            localStringBuilder.append(wipeDotZero(value));
            i += 1;
        }
        // paramHashMap = paramString + localStringBuilder.toString() + paramString;
        String param = paramString + localStringBuilder.toString() + paramString;
        try {
            // paramString = new String(paramHashMap.getBytes(this.data.c()));// "UTF-8"
            String str = new String(param.getBytes(getParameter().c()));
            // paramHashMap = paramString;
            param = str;
        } catch (Exception e) {
        }
        // return a(paramHashMap, getParameter().a());
        return md5(param, getParameter().a());
    }

    /**
     * @param paramString1 被加密数据
     * @param paramString2 "MD5"
     * @return 进行MD5加密
     */
    private String md5(String paramString1, String paramString2) {
        if (null == paramString1 || "".equals(paramString1)) {
            return null;
        }
        byte[] arrayOfByte = paramString1.getBytes();
        // paramString1 = paramString2;
        try {
            if (null == paramString2 || "".equals(paramString2)) {
                paramString2 = getParameter().b();
            }
            MessageDigest digest = MessageDigest.getInstance(paramString2);
            digest.update(arrayOfByte);
            String sign = doMD5(digest.digest());
            return sign;
        } catch (NoSuchAlgorithmException e) {
        }
        return null;
    }

    private String doMD5(byte[] paramArrayOfByte) {
        String str1 = "";
        int i = 0;
        while (i < paramArrayOfByte.length) {
            String str3 = Integer.toHexString(paramArrayOfByte[i] & 0xFF);
            String str2 = str1;
            if (str3.length() == 1) {
                str2 = str1 + "0";
            }
            str1 = str2 + str3;
            i += 1;
        }
        return str1;
    }

    /**
     * @param paramObject
     * @return 去除paramObject末尾的.0
     */
    public <T> Object wipeDotZero(Object paramObject) {
        Object localObject;
        if (!(paramObject instanceof Double)) {
            localObject = paramObject;
            if (!(paramObject instanceof Float)) {
            }
        } else {
            String str = paramObject.toString();
            localObject = paramObject;
            if (str.endsWith(".0")) {
                localObject = str.substring(0, str.length() - 2);
            }
        }
        return localObject;
    }

    private boolean judge(Object paramObject) {
        if ((paramObject instanceof String)) {
            paramObject = (String) paramObject;
            if (paramObject == null || "".equals(paramObject) || (match((String) paramObject))) {
            }
        }
        while (((paramObject instanceof Double)) || ((paramObject instanceof Integer))
                || ((paramObject instanceof Long)) || ((paramObject instanceof Short))
                || ((paramObject instanceof Byte)) || ((paramObject instanceof Float))
                || ((paramObject instanceof Boolean)) || ((paramObject instanceof Character))) {
            return true;
            // return false;
        }
        return false;
    }

    /**
     * @param paramString
     * @return
     */
    private boolean match(String paramString) {
        if (paramString == null || "".equals(paramString)) {
            return true;
        }
        return Pattern.compile("\\{.*\\}|\\[.*\\]").matcher(paramString).matches();
    }

    /**
     * appVersion
     *
     * @return 获取盐字符串
     */
    private String getSaltString() {
        if (null == salt && "".equals(salt)) {
            this.salt = getSaltHandle().handle("291 336 336 258 303 342 345 315 333 330");
        }
        return this.salt;// 这个salt处理后就是"appVersion"
    }

    /**
     * @return 盐处理对象
     */
    private SaltHandle getSaltHandle() {
        if (this.handle == null) {
            this.handle = new SaltHandle();
        }
        return this.handle;
    }

    /**
     * @return
     */
    private Data getParameter() {
        if (this.data == null) {
            // SaltHandle handler = getSaltHandle();
            // a locala = new a();
            this.data = new Data();
            // this.a.a(localb.a(locala.a()));MD5
            this.data.a("MD5");
            // this.a.b(localb.a(locala.b()));SHA-256
            this.data.b("SHA-256");
            // this.a.c(localb.a(locala.c()));UTF-8
            this.data.c("UTF-8");
            // this.a.d(localb.a(locala.d()));appTimestamp
            this.data.d("appTimestamp");
            // this.a.e(localb.a(locala.e()));appKey
            this.data.e("appKey");
            // this.a.f(localb.a(locala.g()));android_lk98f83
            this.data.f("android_lk98f83");
            // this.a.g(localb.a(locala.f()));sign
            this.data.g("sign");
            // this.a.h(localb.a(locala.h()));flvjk342589fdgjl34m9sdufg234oiy
            this.data.h("flvjk342589fdgjl34m9sdufg234oiy");
        }
        return this.data;
    }

    /**
     * 移除HashMap中 value为null的key-value
     *
     * @param paramHashMap 请求参数
     */
    private void functionB(HashMap<String, Object> paramHashMap) {
        if (paramHashMap != null) {
            ArrayList<String> localArrayList = new ArrayList<>();
            Iterator<String> localIterator = paramHashMap.keySet().iterator();
            while (localIterator.hasNext()) {
                String str = localIterator.next();
                if (paramHashMap.get(str) == null) {
                    localArrayList.add(str);
                }
            }
            int i = 0;
            while (i < localArrayList.size()) {
                paramHashMap.remove(localArrayList.get(i));
                i += 1;
            }
        }
    }

    /**
     * @param params1
     * @param paramsMap
     * @return 判断版本名称是否相等
     */
    public boolean functionA(HashMap<String, Object> paramHashMap, String appVersion) {
        if ((paramHashMap != null) && (paramHashMap.get(getSaltString()) != null) && (appVersion != null)
                && (!appVersion.equals(""))) {
            return appVersion.equalsIgnoreCase(paramHashMap.get(getSaltString()).toString().trim());
        }
        return false;
    }

    /**
     * @param url          http://app.jollychic.com/address/getRegionByParentNew.do?" +
     *                     System.currentTimeMillis()
     * @param paramHashMap
     * @param appVersion   appVersion 6.11.2
     */
    public void doSign2(String url, HashMap<String, Object> paramHashMap, String appVersion) {
        // if (functionA(paramHashMap, appVersion)) {// 判断版本名称是否相等
        functionB(paramHashMap); // 移除HashMap中 value为null的key-value
        // paramString1 = c();
        Data data = getParameter();
        // paramHashMap.put(data.d(), data.e());// key="appTimestamp",value=时间
        // paramHashMap.put(data.f(), data.g());// key="appKey",value="sign"
        // paramHashMap.put(data.h(), sign(paramHashMap, data.i()));//
        // key="android_lk98f83",flvjk342589fdgjl34m9sdufg234oiy

        // paramHashMap.put("appTimestamp", System.currentTimeMillis() + "");
        paramHashMap.put("appTimestamp", "1513929431686");
        paramHashMap.put("appKey", "android_lk98f83");
        paramHashMap.put("sign", sign(paramHashMap, data.i()));
        // }
        Gson gson = new Gson();
        System.out.println(gson.toJson(paramHashMap));
    }

    /**
     * 请求参数的拼装
     *
     * @param paramString
     * @param map         包含parentId，seq
     */
    private void signRequestParams(String paramString, HashMap<String, Object> map) {
        // String str = (String)
        // this.mRequestCreatorHelper.getDefaultRequestParams().getParamsMap().get("appVersion");
        String str = "6.11.2";
        paramString = "http://app.jollychic.com/address/getRegionByParentNew.do?" + System.currentTimeMillis();
        // paramHashMap = appendDefaultParams(paramHashMap);
        // appChannel
        map.put("appChannel", "GooglePlay");
        // appTypeId
        map.put("appTypeId", "0");
        // appVersion
        map.put("appVersion", "6.11.2");
        // cookieId
        map.put("cookieId", "511cad5b-6ae2-49a0-b644-b69a2660ba94");
        // countryCode
        map.put("countryCode", "HK");
        // currency
        map.put("currency", "HKD");
        // lang
        map.put("lang", "0");
        // terminalType
        map.put("terminalType", "1");
        // userId
        map.put("userId", "14963605");
        // userToken
        map.put("userToken", "Mmnk8V5gDSz0pegyf7aUcgQQ");
        doSign2(paramString, map, str);
    }

    /**
     * jollychic 调用Region接口的方法（class AddressRemoteApiVolley类）
     *
     * @param paramInt parentId
     */
    public HashMap<String, Object> getRegionList(int paramInt) {
        // 调用signRequestParams();
        String paramString = "http://app.jollychic.com/address/getRegionByParentNew.do?" + System.currentTimeMillis();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("parentId", paramInt);
        hashMap.put("seq", paramInt);
        signRequestParams(paramString, hashMap);
        return hashMap;
    }

}
