package com.yao.resource.bean;

/**
 * Created by yaopaine on 12/22/17.
 */

public class Region {
    /**
     * languageSite : 1
     * languageText : الباحة
     * parentId : 1876
     * regionId : 2519
     * regionName : Al-Baha{Al-Bahah}
     * regionType : 1
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

    @Override
    public String toString() {
        return "Region{" +
                "languageSite=" + languageSite +
                ", languageText='" + languageText + '\'' +
                ", parentId=" + parentId +
                ", regionId=" + regionId +
                ", regionName='" + regionName + '\'' +
                ", regionType=" + regionType +
                '}';
    }
}
