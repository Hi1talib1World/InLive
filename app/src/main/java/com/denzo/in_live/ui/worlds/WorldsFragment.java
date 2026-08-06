package com.denzo.in_live.ui.worlds;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.denzo.fetcher.Utils.Utils;
import com.denzo.in_live.Activity.InitActivity;
import com.denzo.in_live.Adapter.WorldsAdapter;
import com.denzo.in_live.R;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WorldsFragment extends Fragment {

    @BindView(R.id.rv_worlds)
    RecyclerView rvWorlds;
    @BindView(R.id.swipe_refresh)
    SwipeRefreshLayout swipeRefresh;

    private WorldsAdapter adapter;
    private InitActivity initActivity;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_worlds, container, false);
        ButterKnife.bind(this, root);
        initActivity = (InitActivity) getActivity();

        if (initActivity != null) {
            initActivity.setTitle("Worlds");
            initActivity.enableBack(false);
            initActivity.showToolbar(true);
        }

        adapter = new WorldsAdapter(getContext(), R.layout.holder_home_category);
        int mNoOfColumns = initActivity != null ? initActivity.calculateNoOfColumns() : 3;
        rvWorlds.setLayoutManager(com.denzo.fetcher.Utils.Utils.gridLayoutManager(mNoOfColumns));
        rvWorlds.setAdapter(adapter);

        swipeRefresh.setOnRefreshListener(this::loadWorlds);

        loadWorlds();

        return root;
    }

    private void loadWorlds() {
        swipeRefresh.setRefreshing(true);
        AssetManager assetManager = requireContext().getAssets();
        try {
            // Since we added res/streams to assets, they should be in the root or a subfolder if we specified one.
            // In build.gradle: assets.srcDirs += ['src/main/res/streams']
            // This means files in src/main/res/streams are at the root of assets.
            String[] files = assetManager.list("");
            List<String> m3uFiles = new ArrayList<>();
            if (files != null) {
                for (String file : files) {
                    if (file.endsWith(".m3u")) {
                        m3uFiles.add(file);
                    }
                }
            }
            Collections.sort(m3uFiles);
            adapter.setList(m3uFiles);
        } catch (IOException e) {
            e.printStackTrace();
        }
        swipeRefresh.setRefreshing(false);
    }
}
