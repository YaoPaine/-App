package com.yao.lib_common.rxjava.retrofit.model.entity;

import java.io.Serializable;

/**
 * Created by yaopaine on 12/22/17.
 */

public class Country implements Serializable {

    /**
     * isoCode : AL
     * languageSite : 0
     * languageText :
     * parentId : 0
     * regionId : 1851
     * regionName : Albania
     * regionType : 0
     */

    private String isoCode;//除了country其它均不含此项
    private int languageSite;
    private String languageText;
    private int parentId;
    private int regionId;
    private String regionName;
    private int regionType;

    public String getIsoCode() {
        return isoCode;
    }

    public void setIsoCode(String isoCode) {
        this.isoCode = isoCode;
    }

    public int getLanguageSite() {
        return languageSite;
    }

    public void setLanguageSite(int languageSite) {
        this.languageSite = languageSite;
    }

    public String getLanguageText() {
        return languageText;
    }

    public void setLanguageText(String languageText) {
        this.languageText = languageText;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getRegionType() {
        return regionType;
    }

    public void setRegionType(int regionType) {
        this.regionType = regionType;
    }
}
