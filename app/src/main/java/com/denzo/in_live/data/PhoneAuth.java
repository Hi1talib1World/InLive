package com.denzo.in_live.data;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denzo.in_live.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class PhoneAuth extends AppCompatActivity implements View.OnClickListener {

    EditText etPhone, etOtp;
    Button btSendOtp, btResendOtp, btVerifyOtp;
    private FirebaseAuth mAuth;
    String mVerificationId;
    PhoneAuthProvider.ForceResendingToken mResendToken;
    PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone__form);
        getSupportActionBar().setTitle("Phone Auth !");
        initFields();
        mAuth = FirebaseAuth.getInstance();



    }
    void initFireBaseCallbacks() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(PhoneAuthCredential credential) {
                Toast.makeText(PhoneAuth.this, "Verification Complete", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(PhoneAuth.this, "Verification Failed", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCodeSent(String verificationId,
                                   PhoneAuthProvider.ForceResendingToken token) {
                Toast.makeText(PhoneAuth.this, "Code Sent", Toast.LENGTH_SHORT).show();
            }
        };
    }
    void initFields() {
        etPhone = findViewById(R.id.et_phone);
        etOtp = findViewById(R.id.et_otp);
        btSendOtp = findViewById(R.id.bt_send_otp);
        btResendOtp = findViewById(R.id.bt_resend_otp);
        btVerifyOtp = findViewById(R.id.bt_verify_otp);
        btResendOtp.setOnClickListener(this);
        btVerifyOtp.setOnClickListener(this);
        btSendOtp.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_send_otp:
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        etPhone.getText().toString(),        // Phone number to verify
                        1,                 // Timeout duration
                        TimeUnit.MINUTES,   // Unit of timeout
                        this,               // Activity (for callback binding)
                        mCallbacks);        // OnVerificationStateChangedCallbacks
                break;
            case R.id.bt_resend_otp:
                PhoneAuthProvider.getInstance().verifyPhoneNumber(
                        etPhone.getText().toString(),        // Phone number to verify
                        1  ,               // Timeout duration
                        TimeUnit.MINUTES,   // Unit of timeout
                        this,               // Activity (for callback binding)
                        mCallbacks,         // OnVerificationStateChangedCallbacks
                        mResendToken);             // Force Resending Token from callbacks
                break;
            case R.id.bt_verify_otp:
                PhoneAuthCredential credential = PhoneAuthProvider.getCredential(mVerificationId, etOtp.getText().toString());
                mAuth.signInWithCredential(credential)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(PhoneAuth.this, "Verification Success", Toast.LENGTH_SHORT).show();
                                    FirebaseUser user = task.getResult().getUser();

                                } else {
                                    if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                        Toast.makeText(PhoneAuth.this, "Verification Failed, Invalid credentials", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                        });
                break;

        }
    }


    public void onCodeSent(String verificationId,
                           PhoneAuthProvider.ForceResendingToken token) {
        Toast.makeText(PhoneAuth.this, "Code Sent succesfully", Toast.LENGTH_SHORT).show();
        mVerificationId = verificationId; //Add this line to save //verification Id
        mResendToken = token; //Add this line to save the resend token

    }
}
