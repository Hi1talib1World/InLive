package com.denzo.in_live.Model.Home;

import com.google.gson.annotations.SerializedName;

public class AppVer{

    @SerializedName("latest_v")
    private String latestV;

    public void setLatestV(String latestV){
        this.latestV = latestV;
    }

    public String getLatestV(){
        return latestV;
    }
}