package com.denzo.in_live.Activity;

public class VootListingActivity extends InitActivity {
    @BindView(R.id.rv_data)
    RecyclerView rvData;
    private String title;
    private String api;
    private Bundle bundle;
    private VootListModel model;
    private VootListingAdapter adapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voot_listing);
        ButterKnife.bind(this);

        bundle=getIntent().getExtras();
        api=bundle.getString("ApiUrl");
        title=bundle.getString("Title");
        adapter=new VootListingAdapter(this,R.layout.holder_home_title);

        setTitle(title);

        loading(true);
        Fetcher.ref(api)
                .setMethod(Method.GET)
                .connect(VootListModel.class,response -> {
                    loading(false);
                    model=response.getObject();
                    if (model!=null) if (model.getContent()!=null)
                        adapter.setList(model.getContent());
                });

        rvData.setLayoutManager(Utils.linear(RecyclerView.VERTICAL));
        rvData.setAdapter(adapter);
    }
}
