package com.denzo.in_live;

import android.os.Bundle;

import com.denzo.fetcher.Fetcher.Fetcher;
import com.denzo.fetcher.enums.Method;
import com.denzo.in_live.Model.Update.UpdateModel;
import com.denzo.in_live.Utils.Constant;
import com.denzo.in_live.dialog.update.UpdateDialog;
import com.google.android.material.bottomnavigation.BottomNavigationView;


import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.onesignal.OneSignal;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        OneSignal.startInit(this)
                .inFocusDisplaying(OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();
        Fetcher.ref(Constant.update).setMethod(Method.GET).connect(UpdateModel.class, response -> {
            if (response.getObject()!=null)
            {
                if (response.getObject().getMsg().equals("New Update Found"))
                    new UpdateDialog(MainActivity.this,response.getObject());
            }
        });

    }
}