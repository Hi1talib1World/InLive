package com.denzo.in_live.Adapter;


import com.denzo.in_live.R;

public class SliderAdapter extends RecyclerBuilder<SliderItem> {
    public SliderAdapter(Context context, int layout) {
        super(context, layout);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position, @NonNull SliderItem model, View v) {
        ImageView imageView=findViewById(R.id.img_slide);
        Glide.with(getContext()).load(model.getImg()).placeholder(shimmer()).into(imageView);
    }

    @Override
    public void onClick(View view, SliderItem model, int id) {

    }
}