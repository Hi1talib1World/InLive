package com.denzo.in_live.Adapter;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.denzo.in_live.Model.Series.SeasonListModel;
import com.denzo.in_live.R;

public class SpinnerAdapter extends ArrayAdapter<SeasonListModel> {

    LayoutInflater inflate;
    public SpinnerAdapter(@NonNull Context context, int resource, @NonNull List<SeasonListModel> objects) {
        super(context, resource, objects);
        inflate=LayoutInflater.from(context);
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = inflate.inflate(R.layout.spinner_style,parent, false);
        }
        SeasonListModel rowItem = getItem(position);
        TextView txtTitle = convertView.findViewById(R.id.title);
        txtTitle.setText(rowItem.toString());
        ImageView imageView = convertView.findViewById(R.id.icon);
        imageView.setImageResource(R.drawable.ic_arrow_drop_down);
        return convertView;
    }
}