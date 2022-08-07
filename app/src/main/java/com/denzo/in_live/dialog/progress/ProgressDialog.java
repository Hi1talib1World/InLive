package com.denzo.in_live.dialog.progress;

import android.app.Dialog;
import android.content.Context;

import androidx.annotation.NonNull;

import com.denzo.in_live.R;


public class ProgressDialog extends Dialog {
    public ProgressDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_progress);
        setCancelable(false);
        show();
    }
}