package com.denzo.in_live.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;

import com.denzo.fetcher.Adapter.RecyclerBuilder;
import com.denzo.fetcher.Holder.BaseViewHolder;
import com.denzo.in_live.R;
import com.denzo.in_live.Utils.Utils;

public class WorldsAdapter extends RecyclerBuilder<String> {

    public WorldsAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull String model, View v) {
        TextView tvTitle = findViewById(R.id.tv_title);
        // Remove .m3u extension for display
        String displayName = model.replace(".m3u", "").toUpperCase();
        tvTitle.setText(displayName);
        
        // Adjust layout if needed, using holder_home_category which has a card and title
        View cardView = findViewById(R.id.card_thumb);
        if (cardView != null) {
            setLayoutPrams(cardView, 180, 100);
        }
    }

    @Override
    public void onClick(View view, String model, int id) {
        Bundle bundle = new Bundle();
        bundle.putString("fileName", model);
        Navigation.findNavController(view).navigate(R.id.action_navigation_worlds_to_worldChannelsFragment, bundle);
    }
}
