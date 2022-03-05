package com.denzo.in_live.Model.Home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Changes{

    @SerializedName("title")
    private String title;

    @SerializedName("fixes")
    private List<String> fixes;

    public void setTitle(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public void setFixes(List<String> fixes){
        this.fixes = fixes;
    }

    public List<String> getFixes(){
        return fixes;
    }
}