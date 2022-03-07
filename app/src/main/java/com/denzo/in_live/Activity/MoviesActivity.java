
package com.denzo.in_live.Activity;

import android.os.Bundle;

import androidx.recyclerview.widget.RecyclerView;

import com.denzo.in_live.Model.Movies.MoviesModel;
import com.denzo.in_live.R;

import javax.annotation.Nullable;

import butterknife.BindView;
import io.netty.util.Recycler;

public class MoviesActivity extends InitActivity {
    private String api;
    private MoviesAdapter adapter;
    @BindView(R.id.rv_movies)
    RecyclerView rvMovies;
    private MoviesModel Mm;
    private RecyclerView.LayoutManager mLayoutManager;
    private boolean is_pagination;
    private String title;
    private Bundle bundle;
    int i=1;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState){
        super.onCreate(savedInstancesState);
        setContentView(R.layout.activity_movies);
        ButterKnife.bind(this);
        bundle=getIntent().getExtras();
        api=bundle.getString("ApiUrl");
        title=bundle.getString("Title");
        int mNoOfColumns = calculateNoOfColumns();
        mLayoutManager=Utils.gridLayoutManager(mNoOfColumns);
        adapter=new MoviesAdapter(this,R.layout.holder_home_category);
        setTitle(title);

        rvMovies.setLayoutManager(mLayoutManager);
        rvMovies.setAdapter(adapter);
        fetch(0);
        rvMovies.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                if (dy > 0) {
                    final int visibleThreshold = 16;

                    GridLayoutManager layoutManager = (GridLayoutManager)rvMovies.getLayoutManager();
                    int lastItem  = layoutManager.findLastCompletelyVisibleItemPosition();
                    int currentTotalCount = layoutManager.getItemCount();

                    if(currentTotalCount <= lastItem + visibleThreshold){
                        if (is_pagination && !isLoading())
                        {
                            i++;
                            fetch(i);
                        }
                    }

                }
            }
        });

    }

    private void fetch(int page){
        loading(true);
        String url;
        if (page!=0)
        {
            url=api+"&page="+page;
        }
        else url=api;

        Fetcher.ref(url).setMethod(Method.GET).connect(MoviesModel.class,response -> {
            moviesModel=response.getObject();
            if (moviesModel!=null)
            {
                loading(false);
                is_pagination=moviesModel.getIsPagination().equals("1");
                if (moviesModel.getContent()!=null && page==0)
                    adapter.setList(moviesModel.getContent());
                else adapter.addItems(moviesModel.getContent());
//                rvMovies.scheduleLayoutAnimation();
            }
        });

    }

}