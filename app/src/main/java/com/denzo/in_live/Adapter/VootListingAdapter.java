package com.denzo.in_live.Adapter;


import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.denzo.in_live.R;

public class VootListingAdapter extends RecyclerBuilder<ContentItem> {
    public VootListingAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull ContentItem model, View v) {
        TextView tvTitle=findViewById(R.id.tv_title);
        RecyclerView recyclerView=findViewById(R.id.rv_category);
        VootCategoryAdapter adapter=new VootCategoryAdapter(getContext(), R.layout.holder_home_category);
        tvTitle.setText(model.getCatName());
        recyclerView.setLayoutManager(Utils.linear(RecyclerView.HORIZONTAL));
        recyclerView.setAdapter(adapter);

        tvTitle.setTextColor(Color.WHITE);
        List<ContentItem> list=new ArrayList<>();
        for (ContentItem contentItem : model.getContent()) {
            contentItem.setCategoryType(model.getName());
            list.add(contentItem);
        }
        adapter.setList(list);

    }

    @Override
    public void onClick(View view, ContentItem model, int id) {

    }


}