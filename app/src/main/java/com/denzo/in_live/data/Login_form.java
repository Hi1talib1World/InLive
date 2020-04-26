package com.denzo.in_live.data;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.denzo.in_live.R;

public class Login_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("LogIn !");
    }
}
