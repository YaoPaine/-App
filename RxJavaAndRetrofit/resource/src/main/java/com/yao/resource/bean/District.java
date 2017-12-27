package com.yao.resource.bean;

/**
 * Created by yaopaine on 12/22/17.
 */

public class District {

    /**
     * languageSite : 1
     * languageText : الضباب
     * parentId : 25998
     * regionId : 235537
     * regionName : Ad Dabab
     * regionType : 3
     */

    private int languageSite;
    private String languageText;
    private int parentId;
    private int regionId;
    private String regionName;
    private int regionType;

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
