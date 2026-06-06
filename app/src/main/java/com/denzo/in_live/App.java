package com.denzo.in_live;

import android.app.Application;
import com.onesignal.OneSignal;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        // Initialization code here
        OneSignal.initWithContext(this, "941d0378-4580-480d-967b-59ebf0d91f71");
    }
}
