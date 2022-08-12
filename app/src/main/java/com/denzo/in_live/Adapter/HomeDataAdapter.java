package com.denzo.in_live.Adapter;

import static java.security.AccessController.getContext;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.denzo.in_live.Model.Home.ContentItem;
import com.denzo.in_live.Model.Home.DataItem;
import com.denzo.in_live.R;



import java.util.ArrayList;
import java.util.List;

public class HomeDataAdapter extends RecyclerBuilder<DataItem> {

    public HomeDataAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull DataItem model, View v) {
        TextView tvTitle=findViewById(R.id.tv_title);
        RecyclerView recyclerView=findViewById(R.id.rv_category);
        HomeCategoryAdapter adapter=new HomeCategoryAdapter(getContext(),R.layout.holder_home_category);
        tvTitle.setText(model.getName());
        recyclerView.setLayoutManager(Utils.linear(RecyclerView.HORIZONTAL));
        recyclerView.setAdapter(adapter);

        List<ContentItem> list=new ArrayList<>();
        for (ContentItem contentItem : model.getContent()) {
            contentItem.setCategoryType(model.getName());
            list.add(contentItem);
        }
        adapter.setList(list);
    }

    @Override
    public void onClick(View view, DataItem model, int id) {
    }
}