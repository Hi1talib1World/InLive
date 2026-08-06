package com.denzo.in_live.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.denzo.fetcher.Adapter.RecyclerBuilder;
import com.denzo.fetcher.Holder.BaseViewHolder;
import com.denzo.in_live.Activity.PlayerActivity;
import com.denzo.in_live.Model.M3uChannel;
import com.denzo.in_live.Model.MoviePlayback.VideosItem;
import com.denzo.in_live.R;
import com.google.android.material.card.MaterialCardView;

import static com.denzo.fetcher.Utils.Utils.dpToPx;

public class WorldChannelsAdapter extends RecyclerBuilder<M3uChannel> {

    public WorldChannelsAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull M3uChannel model, View v) {
        TextView tvTitle = findViewById(R.id.tv_title);
        ImageView imgThumbnail = findViewById(R.id.img_thumbnail);
        MaterialCardView cardView = findViewById(R.id.card_thumb);

        if (cardView != null) {
            setLayoutPrams(cardView, 180, 130);
            cardView.setRadius(dpToPx(10));
        }
        
        tvTitle.setSingleLine(true);
        tvTitle.setText(model.getName());
        
        if (model.getLogoUrl() != null && !model.getLogoUrl().isEmpty()) {
            Glide.with(getContext())
                    .load(model.getLogoUrl())
                    .placeholder(shimmer())
                    .into(imgThumbnail);
        } else {
            imgThumbnail.setImageResource(R.drawable.ic_dashboard_black_24dp); // Placeholder
        }
    }

    @Override
    public void onClick(View view, M3uChannel model, int id) {
        VideosItem videosItem = new VideosItem();
        videosItem.setFileUrl(model.getUrl());
        videosItem.setTitle(model.getName());
        
        Intent intent = new Intent(getContext(), PlayerActivity.class);
        intent.putExtra("VideoItem", videosItem);
        getContext().startActivity(intent);
    }
}
