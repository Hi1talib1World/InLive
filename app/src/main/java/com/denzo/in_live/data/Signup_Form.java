package com.denzo.in_live.data;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.denzo.in_live.MainActivity;
import com.denzo.in_live.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Signup_Form extends AppCompatActivity {

    EditText emailId, password ,fullId,userId ,cpassword;
    Button btnSignUp;
    TextView tvSignIn;
    FirebaseAuth mFirebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup__form);
        getSupportActionBar().setTitle("Sign Up !");

        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(this);
        mFirebaseAuth = FirebaseAuth.getInstance();
        fullId = findViewById(R.id.full);
        userId = findViewById(R.id.user2);
        emailId = findViewById(R.id.email);
        password = findViewById(R.id.pw);
        cpassword = findViewById(R.id.pwc);
        btnSignUp = findViewById(R.id.button2);
        tvSignIn = findViewById(R.id.textView);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailId.getText().toString();
                String pwd = password.getText().toString();
                String pwdc = cpassword.getText().toString();
                String full = fullId.getText().toString();
                String usr = userId.getText().toString();

                if(email.isEmpty()){
                    emailId.setError("Please enter email id");
                    emailId.requestFocus();
                }else  if(usr.isEmpty()){
                    userId.setError("Please enter your password");
                    userId.requestFocus();
                }else  if(full.isEmpty()){
                    fullId.setError("Please enter your password");
                    fullId.requestFocus();
                }
                else  if(pwdc.isEmpty()){
                    cpassword.setError("Please confirme your password");
                    cpassword.requestFocus();
                }
                else  if(pwd.isEmpty()){
                    password.setError("Please enter your password");
                    password.requestFocus();
                }
                else  if(email.isEmpty() && pwd.isEmpty() && pwdc.isEmpty() && full.isEmpty() && usr.isEmpty() ) {
                    Toast.makeText(Signup_Form.this,"Fields Are Empty!",Toast.LENGTH_SHORT).show();
                }
                else  if(!(email.isEmpty() && pwd.isEmpty() && pwdc.isEmpty() && full.isEmpty() && usr.isEmpty())){
                    mFirebaseAuth.createUserWithEmailAndPassword(email, pwd).addOnCompleteListener(Signup_Form.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(!task.isSuccessful()){
                                Toast.makeText(Signup_Form.this,"SignUp Unsuccessful, Please Try Again",Toast.LENGTH_SHORT).show();
                            }
                            else {
                                startActivity(new Intent(Signup_Form.this, Login_form.class));
                            }
                        }
                    });
                }
                else{
                    Toast.makeText(Signup_Form.this,"Error Occurred!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        tvSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Signup_Form.this,Login_form.class);
                startActivity(i);
            }
        });
    }
}
