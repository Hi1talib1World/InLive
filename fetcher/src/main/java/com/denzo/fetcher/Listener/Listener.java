package com.denzo.fetcher.Listener;


import com.denzo.fetcher.Model.UserDataModel;

public interface Listener {
    void Listener(boolean success, UserDataModel userDataModel,String body);

}
