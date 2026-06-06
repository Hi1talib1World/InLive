package com.denzo.in_live.ui.liveTv;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.denzo.fetcher.Fetcher.Fetcher;
import com.denzo.fetcher.Utils.Utils;
import com.denzo.fetcher.enums.Method;
import com.denzo.in_live.Adapter.VootListingAdapter;
import com.denzo.in_live.Model.Voot.VootListModel;
import com.denzo.in_live.R;
import com.denzo.in_live.Utils.Constant;
import com.denzo.in_live.Utils.MockData;
import com.denzo.in_live.fragment.InitFragment;
import com.facebook.shimmer.ShimmerFrameLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LiveTvCategoryFragment extends InitFragment {
    @BindView(R.id.rv_channels)
    RecyclerView rvData;
    @BindView(R.id.live_progress)
    ProgressBar liveProg;
    @BindView(R.id.live_fb)
    ShimmerFrameLayout fbs;

    private VootListingAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_live_tv, container, false);
        ButterKnife.bind(this, root);

        adapter = new VootListingAdapter(getContext(), R.layout.holder_home_title);
        getInitActivity().enableBack(false);
        getInitActivity().showToolbar(true);
        getInitActivity().setTitle("TV Channels");
        getInitActivity().loading(true);
        getInitActivity().showStatusBar();

        rvData.setLayoutManager(Utils.linear(RecyclerView.VERTICAL));
        rvData.setAdapter(adapter);

        Animation fade = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_up);
        rvData.setAnimation(fade);

        fetch();

        return root;
    }

    private void fetch() {
        String url = "https://api.vidflix.net/v2/api/premium/11/?name=home&is_series=4";

        Fetcher.ref(url)
                .setMethod(Method.GET)
                .setMocked(Constant.MOCK ? MockData.TV_CHANNELS_JSON : null)
                .connect(VootListModel.class, response -> {
            getInitActivity().loading(false);
            if (response.getObject() != null && response.getObject().getContent() != null) {
                adapter.setList(response.getObject().getContent());
            }
            liveProg.setVisibility(View.GONE);
            fbs.setVisibility(View.GONE);
        });
    }
}
