package com.denzo.in_live.Adapter;


import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzo.fetcher.Adapter.RecyclerBuilder;
import com.denzo.fetcher.Holder.BaseViewHolder;
import com.denzo.fetcher.Utils.Utils;
import com.denzo.in_live.Model.search.ContentItem;
import com.denzo.in_live.R;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends RecyclerBuilder<ContentItem> {

    public SearchAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull ContentItem model, View v) {
        TextView tvTitle=findViewById(R.id.tv_title);
        RecyclerView recyclerView=findViewById(R.id.rv_category);
        SearchContent adapter=new SearchContent(getContext(),R.layout.holder_home_category);
        tvTitle.setText(model.getCatName());
        recyclerView.setLayoutManager(Utils.linear(RecyclerView.HORIZONTAL));
        recyclerView.setAdapter(adapter);

        List<ContentItem> list=new ArrayList<>();
        for (ContentItem contentItem : model.getContent()) {
            contentItem.setCategoryType(model.getCatName());
            list.add(contentItem);
        }
        adapter.setList(list);

    }

    @Override
    public void onClick(View view, ContentItem model, int id) {

    }


}