package com.denzo.in_live.Model.Voot;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class VootListModel{

    @SerializedName("msg")
    private String msg;

    @SerializedName("code")
    private String code;

    @SerializedName("asset_type")
    private String assetType;

    @SerializedName("type")
    private String type;

    @SerializedName("is_pagination")
    private String isPagination;

    @SerializedName("content")
    private List<ContentItem> content;

    @SerializedName("status")
    private String status;

    public void setMsg(String msg){
        this.msg = msg;
    }

    public String getMsg(){
        return msg;
    }

    public void setCode(String code){
        this.code = code;
    }

    public String getCode(){
        return code;
    }

    public void setAssetType(String assetType){
        this.assetType = assetType;
    }

    public String getAssetType(){
        return assetType;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setIsPagination(String isPagination){
        this.isPagination = isPagination;
    }

    public String getIsPagination(){
        return isPagination;
    }

    public void setContent(List<ContentItem> content){
        this.content = content;
    }

    public List<ContentItem> getContent(){
        return content;
    }

    public void setStatus(String status){
        this.status = status;
    }

    public String getStatus(){
        return status;
    }

    @Override
    public String toString(){
        return
                "VootListModel{" +
                        "msg = '" + msg + '\'' +
                        ",code = '" + code + '\'' +
                        ",asset_type = '" + assetType + '\'' +
                        ",type = '" + type + '\'' +
                        ",is_pagination = '" + isPagination + '\'' +
                        ",content = '" + content + '\'' +
                        ",status = '" + status + '\'' +
                        "}";
    }
}
