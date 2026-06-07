package com.denzo.in_live.Model.Home;

import com.google.gson.annotations.SerializedName;

public class SliderItem{

    @SerializedName("img")
    private String img;

    @SerializedName("name")
    private String name;

    @SerializedName("api_url")
    private String apiUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImg(String img){
        this.img = img;
    }

    public String getImg(){
        return img;
    }

    public void setApiUrl(String apiUrl){
        this.apiUrl = apiUrl;
    }

    public String getApiUrl(){
        return apiUrl;
    }
}