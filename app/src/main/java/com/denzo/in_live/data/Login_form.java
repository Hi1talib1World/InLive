package com.denzo.in_live.data;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.denzo.in_live.R;

public class Login_form extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        getSupportActionBar().setTitle("LogIn !");
    }

    public void btn_login(View view) {

        startActivity(new Intent(getApplicationContext(),Signup_Form.class));
    }
}
