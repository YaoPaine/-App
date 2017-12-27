package com.yao.lib_common.rxjava.retrofit.model.entity;

import java.util.List;

/**
 * Created by yaopaine on 12/22/17.
 */

public class Result<T> {
    /**
     * message : Success
     * messageCode : 0
     * messageType : 0
     * regionList : [{"languageSite":1,"languageText":"الباحة","parentId":1876,"regionId":2519,"regionName":"Al-Baha{Al-Bahah}","regionType":1},{"languageSite":1,"languageText":"الجوف","parentId":1876,"regionId":2520,"regionName":"Al-Jouf {Al Jawf}","regionType":1},{"languageSite":1,"languageText":"المدينة المنورة","parentId":1876,"regionId":2521,"regionName":"Al-Madinah Al-Monawarah","regionType":1},{"languageSite":1,"languageText":"القصيم","parentId":1876,"regionId":2522,"regionName":"Al-Qassim","regionType":1},{"languageSite":1,"languageText":"الرياض","parentId":1876,"regionId":2523,"regionName":"Al-Riyadh","regionType":1},{"languageSite":1,"languageText":"عسير","parentId":1876,"regionId":2524,"regionName":"Asir{ Aseer}","regionType":1},{"languageSite":1,"languageText":"الشرقية","parentId":1876,"regionId":2525,"regionName":"Eastern Region {Ash-Sharqiyah}","regionType":1},{"languageSite":1,"languageText":"حائل","parentId":1876,"regionId":2526,"regionName":"Hail","regionType":1},{"languageSite":1,"languageText":"جازان","parentId":1876,"regionId":2527,"regionName":"Jizan{Gizan}","regionType":1},{"languageSite":1,"languageText":"مكة المكرمة","parentId":1876,"regionId":2528,"regionName":"Makkah{Mecca}","regionType":1},{"languageSite":1,"languageText":"نجران","parentId":1876,"regionId":2529,"regionName":"Najran","regionType":1},{"languageSite":1,"languageText":"الحدود الشمالية","parentId":1876,"regionId":2530,"regionName":"Northern Borders {Al-Hudud ash-Shamaliyah}","regionType":1},{"languageSite":1,"languageText":"تبوك","parentId":1876,"regionId":2531,"regionName":"Tabuk","regionType":1}]
     * result : 0
     * seq : 1876
     */
    private String message;//Success
    private String messageCode;//0
    private int messageType;//0
    private int result;//0
    private int seq;//1876
    private List<T> regionList;

    public void setRegionList(List<T> regionList) {
        this.regionList = regionList;
    }

    public List<T> getRegionList() {
        return regionList;
    }

    public int getSeq() {
        return seq;
    }
}
