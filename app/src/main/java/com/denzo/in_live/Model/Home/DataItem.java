package com.denzo.in_live.Model.Home;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DataItem{

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("content")
    private List<ContentItem> content;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setContent(List<ContentItem> content){
        this.content = content;
    }

    public List<ContentItem> getContent(){
        return content;
    }
}
