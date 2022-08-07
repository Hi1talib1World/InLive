package com.denzo.in_live.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.denzo.in_live.Activity.InitActivity;


public class InitFragment extends Fragment {
    private InitActivity initActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getActivity() instanceof InitActivity)
            initActivity= (InitActivity) getActivity();
    }

    public InitActivity getInitActivity() {
        return initActivity;
    }
}