package com.denzo.in_live.ui.worlds;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.denzo.fetcher.Utils.Utils;
import com.denzo.in_live.Activity.InitActivity;
import com.denzo.in_live.Adapter.WorldChannelsAdapter;
import com.denzo.in_live.Model.M3uChannel;
import com.denzo.in_live.R;
import com.denzo.in_live.Utils.M3uParser;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorldChannelsFragment extends Fragment {

    @BindView(R.id.rv_channels)
    RecyclerView rvChannels;
    @BindView(R.id.pb_loading)
    ProgressBar pbLoading;

    private WorldChannelsAdapter adapter;
    private String fileName;
    private InitActivity initActivity;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            fileName = getArguments().getString("fileName");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_world_channels, container, false);
        ButterKnife.bind(this, root);
        initActivity = (InitActivity) getActivity();

        if (initActivity != null) {
            initActivity.setTitle(fileName != null ? fileName.replace(".m3u", "").toUpperCase() : "Channels");
            initActivity.enableBack(true);
        }

        adapter = new WorldChannelsAdapter(getContext(), R.layout.holder_home_category);
        int mNoOfColumns = initActivity != null ? initActivity.calculateNoOfColumns() : 3;
        rvChannels.setLayoutManager(com.denzo.fetcher.Utils.Utils.gridLayoutManager(mNoOfColumns));
        rvChannels.setAdapter(adapter);

        loadChannels();

        return root;
    }

    private void loadChannels() {
        if (fileName == null) return;
        
        pbLoading.setVisibility(View.VISIBLE);
        new Thread(() -> {
            try {
                InputStream is = requireContext().getAssets().open(fileName);
                List<M3uChannel> channels = M3uParser.parse(is);
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> {
                        adapter.setList(channels);
                        pbLoading.setVisibility(View.GONE);
                    });
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (getActivity() != null) {
                    getActivity().runOnUiThread(() -> pbLoading.setVisibility(View.GONE));
                }
            }
        }).start();
    }
}
