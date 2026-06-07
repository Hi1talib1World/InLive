package com.denzo.in_live.Adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import android.content.Context;

import com.bumptech.glide.Glide;
import com.denzo.fetcher.Adapter.RecyclerBuilder;
import com.denzo.fetcher.Holder.BaseViewHolder;
import com.denzo.in_live.Model.Home.SliderItem;
import com.denzo.in_live.R;

public class SliderAdapter extends RecyclerBuilder<SliderItem> {
    public SliderAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull SliderItem model, View v) {
        ImageView imageView=findViewById(R.id.img_slide);
        TextView tvTitle=findViewById(R.id.tv_slide_title);
        TextView tvDesc=findViewById(R.id.tv_slide_desc);
        
        Glide.with(getContext()).load(model.getImg()).placeholder(shimmer()).into(imageView);
        if (model.getName() != null) {
            tvTitle.setText(model.getName());
        }
        tvDesc.setVisibility(View.GONE); // Hide desc for now if not available in model
    }

    @Override
    public void onClick(View view, SliderItem model, int id) {

    }
}