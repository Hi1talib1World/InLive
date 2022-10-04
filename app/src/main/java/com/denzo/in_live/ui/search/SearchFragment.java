package com.denzo.in_live.ui.search;

import static java.security.AccessController.getContext;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.denzo.in_live.Adapter.SearchAdapter;
import com.denzo.in_live.Model.search.ContentItem;
import com.denzo.in_live.Model.search.SearchModel;
import com.denzo.in_live.R;
import com.denzo.in_live.Utils.Constant;
import com.denzo.in_live.fragment.InitFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchFragment extends InitFragment {

    private SearchViewModel searchViewModel;
    private SearchAdapter adapter;
    @BindView(R.id.search)
    SearchView searchView;
    @BindView(R.id.rv_search_data)
    RecyclerView rvSearch;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        searchViewModel =
                new ViewModelProvider(this).get(SearchViewModel.class);
        View root = inflater.inflate(R.layout.fragment_search, container, false);
        ButterKnife.bind(this,root);
        adapter=new SearchAdapter(getContext(),R.layout.holder_home_title);
        getInitActivity().enableBack(false);
        getInitActivity().showToolbar(true);
        getInitActivity().setTitle("Search");
        getInitActivity().loading(false);
        getInitActivity().showStatusBar();

        rvSearch.setLayoutManager(Utils.linear(RecyclerView.VERTICAL));
        rvSearch.setAdapter(adapter);

        Animation slide_down = AnimationUtils.loadAnimation(getContext(),R.anim.item_animation_fall_down);
        searchView.setAnimation(slide_down);

        searchView.onActionViewExpanded();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                search(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return root;
    }

    private void search(String query){
        getInitActivity().loading(true);

        if (query==null)
            query="";

        if (query.length()>=3)
            Fetcher.ref(Constant.search+query+"&asset_type=global").setMethod(Method.GET).connect(SearchModel.class, response -> {
                getInitActivity().loading(false);
                List<ContentItem> searchModels=response.getObject().getContent();
                if (searchModels!=null)
                    adapter.setList(searchModels);
            });

    }
}
