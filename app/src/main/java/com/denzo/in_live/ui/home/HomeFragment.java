package com.denzo.in_live.ui.home;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.denzo.in_live.Model.Home.HomeModel;
import com.denzo.in_live.R;

import java.util.HashMap;

public class HomeFragment extends InitFragment {

    private HomeViewModel homeViewModel;
    private HomeDataAdapter dataAdapter;
    private SliderAdapter sliderAdapter;
    @BindView(R.id.rv_data)
    RecyclerView rvData;
    @BindView(R.id.rv_slider)
    RecyclerView rvSlider;
    @BindView(R.id.home_progress)
    ProgressBar homeProg;
    @BindView(R.id.live_fb)
    ShimmerFrameLayout homefbs;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this,root);

        getInitActivity().showToolbar(false);
        Animation fade = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in_long);
        rvSlider.setAnimation(fade);

        new ViewModelProvider(this).get(HomeViewModel.class);
        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);
        dataAdapter=new HomeDataAdapter(getContext(),R.layout.holder_home_title);
        sliderAdapter=new SliderAdapter(getContext(),R.layout.holder_silder);
        rvData.setNestedScrollingEnabled(false);
        rvData.setLayoutManager(Utils.linear(RecyclerView.VERTICAL));
        rvData.setAdapter(dataAdapter);
        SnapHelper snapHelper=new LinearSnapHelper();
        rvSlider.setLayoutManager(Utils.linear(RecyclerView.HORIZONTAL));
        snapHelper.attachToRecyclerView(rvSlider);
        rvSlider.setAdapter(sliderAdapter);
        HashMap<String,String> hashMap=new HashMap<>();

        Fetcher.ref(Constant.home)
                .setMethod(Method.GET)
                .setHeaders(hashMap)
                .connect(HomeModel.class, response -> {
                    if (response.getObject()!=null){
                        dataAdapter.setList(response.getObject().getData());
                        sliderAdapter.setList(response.getObject().getSlider());
//                        homeProg.setVisibility(View.GONE);
                        homefbs.setVisibility(View.GONE);
                    }else Toast.makeText(getContext(),"Something went wrong", Toast.LENGTH_SHORT).show();
                });
        getInitActivity().settingStatusBarTransparent();
        rvSlider.addItemDecoration(new LinePagerIndicatorDecoration());

        return root;
    }

}