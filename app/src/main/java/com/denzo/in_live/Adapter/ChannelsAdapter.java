package com.denzo.in_live.Adapter;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;

import com.denzo.in_live.Model.Home.ContentItem;

public class ChannelsAdapter extends RecyclerBuilder<ContentItem>{
    public ChannelsAdapter(Context context, int layout){
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull ContentItem model, View v){

    }

    @Override
    public void onClick(View view, ContentItem model, int id){

    }

}
