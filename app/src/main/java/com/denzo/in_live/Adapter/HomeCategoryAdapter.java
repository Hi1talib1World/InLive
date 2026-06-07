package com.denzo.in_live.Adapter;

import static com.denzo.fetcher.Utils.Utils.capitalizeFirstLetter;
import static com.denzo.fetcher.Utils.Utils.dpToPx;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.bumptech.glide.Glide;
import com.denzo.fetcher.Adapter.RecyclerBuilder;
import com.denzo.fetcher.Holder.BaseViewHolder;
import com.denzo.in_live.Activity.MoviesActivity;
import com.denzo.in_live.Activity.VootListingActivity;
import com.denzo.in_live.Model.Home.ContentItem;
import com.denzo.in_live.R;
import com.google.android.material.card.MaterialCardView;


public class HomeCategoryAdapter extends RecyclerBuilder<ContentItem> {
    public HomeCategoryAdapter(Context context, int layout) {
        super(context, layout);
    }
    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull ContentItem model, View v) {
        TextView tvCategory=findViewById(R.id.tv_title);
        ImageView imgThumbnail=findViewById(R.id.img_thumbnail);
        MaterialCardView cardView=findViewById(R.id.card_thumb);

        tvCategory.setText(capitalizeFirstLetter(model.getCategoryName()));
        if (model.getThumbnail()!=null){
            Glide.with(getContext()).load(model.getThumbnail()).placeholder(shimmer()).into(imgThumbnail);
        }
        
        if ("Live TV".equals(model.getCategoryType()))
        {
            // For Live TV channels, use a 1:1 or 16:9 like aspect ratio as in HTML
            // Actually HTML uses 32x20 (horizontal) for channels and 2/3 (vertical) for Trending.
            // But HomeCategoryAdapter is used in rv_category which usually has vertical posters.
            // Let's adjust to horizontal for Live TV
            
            ViewGroup.LayoutParams lp = cardView.getLayoutParams();
            lp.width = dpToPx(160);
            cardView.setLayoutParams(lp);

            ConstraintLayout.LayoutParams imgLp = (ConstraintLayout.LayoutParams) imgThumbnail.getLayoutParams();
            imgLp.dimensionRatio = "16:9";
            imgThumbnail.setLayoutParams(imgLp);
        } else {
            // Default 2:3 ratio for movies/series
            ViewGroup.LayoutParams lp = cardView.getLayoutParams();
            lp.width = dpToPx(128);
            cardView.setLayoutParams(lp);

            ConstraintLayout.LayoutParams imgLp = (ConstraintLayout.LayoutParams) imgThumbnail.getLayoutParams();
            imgLp.dimensionRatio = "2:3";
            imgThumbnail.setLayoutParams(imgLp);
        }
    }

    @Override
    public void onClick(View view, ContentItem model, int id) {
        String cat=model.getType();
        Bundle bundle=new Bundle();
        bundle.putString("ApiUrl",model.getApiUrl());
        bundle.putString("Title",model.getCategoryName());
        if (cat.equals("list"))
            start(MoviesActivity.class,bundle);
        else if (cat.equals("cats"))
            start(VootListingActivity.class,bundle);

    }
}
