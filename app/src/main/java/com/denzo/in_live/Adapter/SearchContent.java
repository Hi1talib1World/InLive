package com.denzo.in_live.Adapter;


import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.denzo.fetcher.Adapter.RecyclerBuilder;
import com.denzo.fetcher.Fetcher.Fetcher;
import com.denzo.fetcher.Holder.BaseViewHolder;
import com.denzo.fetcher.enums.Method;
import com.denzo.in_live.Activity.MoviesDetailsActivity;
import com.denzo.in_live.Activity.SeriesActivity;
import com.denzo.in_live.Model.MoviePlayback.MoviePlayBackModel;
import com.denzo.in_live.Model.Series.SeriesModel;
import com.denzo.in_live.Model.search.ContentItem;
import com.denzo.in_live.R;

public class SearchContent extends RecyclerBuilder<ContentItem> {
    public SearchContent(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull ContentItem model, View v) {
        TextView tvCategory=findViewById(R.id.tv_title);
        ImageView imgThumbnail=findViewById(R.id.img_thumbnail);

        tvCategory.setText(model.getName());
        if (model.getPosterUrl()!=null){
            Glide.with(getContext()).load(model.getPosterUrl()).placeholder(shimmer()).into(imgThumbnail);
        }

    }

    @Override
    public void onClick(View view, ContentItem model, int id) {
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
                        else Toast.makeText(getContext(),"Something went wrong",Toast.LENGTH_SHORT).show();
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