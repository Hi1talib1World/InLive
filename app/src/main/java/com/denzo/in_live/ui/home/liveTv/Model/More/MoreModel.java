package com.denzo.in_live.ui.home.liveTv.Model.More;

public class MoreModel implements Serializable {

    private String title;
    private String data;

    public MoreModel() {
    }

    public MoreModel(String title, String data) {
        this.title = title;
        this.data = data;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @NonNull
    @Override
    public String toString() {
        return title;
    }
}