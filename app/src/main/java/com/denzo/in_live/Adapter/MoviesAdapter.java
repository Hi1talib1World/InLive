package com.denzo.in_live.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.denzo.in_live.Activity.SeriesActivity;
import com.denzo.in_live.Model.Series.SeriesModel;
import com.denzo.in_live.R;
import com.google.android.material.card.MaterialCardView;

public class MoviesAdapter extends RecyclerBuilder<ContentItem> {
    public MoviesAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull ContentItem model, View v) {
        TextView tvTitle=findViewById(R.id.tv_title);
        ImageView imgThumbnail=findViewById(R.id.img_thumbnail);
        LinearLayout lvData=findViewById(R.id.lv_data);
        MaterialCardView cardView=findViewById(R.id.card_thumb);

        setLayoutPrams(lvData, ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT);
        tvTitle.setSingleLine(true);
        setLayoutPrams(cardView,180,130);
        cardView.setRadius(dpToPx(10));
        tvTitle.setText(model.getName());
        Glide.with(getContext()).load(model.getPosterUrl()).placeholder(shimmer()).into(imgThumbnail);
    }

    @Override
    public void onClick(View view, ContentItem model, int id) {
        view.setEnabled(false);
        if (model.getIsSeries().equals("0") || model.getIsSeries().equals("4")){
            Fetcher.ref(model.getApiUrl()).setMethod(Method.GET)
                    .connect(MoviePlayBackModel.class, response -> {
                        view.setEnabled(true);
                        if (response.getObject()!=null)
                        {
                            Bundle bundle=new Bundle();
                            bundle.putSerializable("MoviesPlaybackData",response.getObject());
                            start(MoviesDetailsActivity.class,bundle);
                        }
                        else Toast.makeText(getContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
                    });
        }
        else if (model.getIsSeries().equals("1")){

            Fetcher.ref(model.getApiUrl()).setMethod(Method.GET).connect(SeriesModel.class, response -> {
                view.setEnabled(true);
                SeriesModel seriesModel=response.getObject();
                Bundle bundle=new Bundle();
                bundle.putSerializable("SeriesModel",seriesModel);
                start(SeriesActivity.class,bundle);
            });
        }
    }
}