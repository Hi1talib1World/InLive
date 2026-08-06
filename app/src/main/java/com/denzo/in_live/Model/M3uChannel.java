package com.denzo.in_live.Model;

import java.io.Serializable;

public class M3uChannel implements Serializable {
    private String name;
    private String url;
    private String logoUrl;
    private String tvgId;

    public M3uChannel(String name, String url, String logoUrl, String tvgId) {
        this.name = name;
        this.url = url;
        this.logoUrl = logoUrl;
        this.tvgId = tvgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getLogoUrl() {
        return logoUrl;
    }

    public void setLogoUrl(String logoUrl) {
        this.logoUrl = logoUrl;
    }

    public String getTvgId() {
        return tvgId;
    }

    public void setTvgId(String tvgId) {
        this.tvgId = tvgId;
    }
}
