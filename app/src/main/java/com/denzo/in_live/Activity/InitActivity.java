package com.denzo.in_live.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.fragment.app.FragmentActivity;

import com.denzo.in_live.R;

import java.util.LinkedHashSet;
import java.util.List;

import javax.sql.DataSource;

public class InitActivity extends FragmentActivity {
    protected SimplePlayer simplePlayer=SimplePlayer.getInstance();
    private boolean isLoading;
    private ShimmerNormalAdapter shimmerNormalAdapter;
    private List<String> noneList=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Fetcher.getInstance().initialize(1);
        Fetcher.getInstance().setToken(new ApiTokenModel());
        if (!BuildConfig.DEBUG){
            LinkedHashSet<String> validPins = new LinkedHashSet<>();
            validPins.add("umg96cxvlJYPfBpol4sdpXnByO4LFzvfskfqsczoYLc=");
            validPins.add("Or16VAUJY3X6Uct3YfVf/l5bsjxFjttst0XHs4vLuDU=");
            validPins.add("3kcNJzkUJ1RqMXJzFX4Zxux5WfETK+uL6Viq9lJNn4o=");
            validPins.add("Y9mvm0exBk1JoQ57f9Vm28jKo5lFm/woKcVxrYxu80o=");
            Fetcher.getInstance().setCerts(validPins);
        }
        shimmerNormalAdapter=new ShimmerNormalAdapter(this, R.layout.shimer_normal);
        for (int i = 0; i < 25; i++) {
            noneList.add(String.valueOf(i));
        }
        shimmerNormalAdapter.setList(noneList);
    }

    protected DataSource.Factory buildDataSourceFactory(){
        return buildDataSourceFactory(null);
    }

    protected DataSource.Factory buildDataSourceFactory(String agent){
        DefaultBandwidthMeter bandwidthMeter = new DefaultBandwidthMeter.Builder(this).build();
        if (TextUtils.isEmpty(agent))
            agent= Constant.agent;
        DefaultHttpDataSourceFactory httpDataSourceFactory = new DefaultHttpDataSourceFactory(
                agent,
                bandwidthMeter,
                DefaultHttpDataSource.DEFAULT_CONNECT_TIMEOUT_MILLIS,
                DefaultHttpDataSource.DEFAULT_READ_TIMEOUT_MILLIS,
                true
        );

        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(this,
                httpDataSourceFactory);
        return dataSourceFactory;
    }

    @Override
    protected void onPause() {
        if (simplePlayer.getPlayer()!=null)
            simplePlayer.pause();
        super.onPause();
    }

    @Override
    protected void onResume() {
        if (simplePlayer.getPlayer()!=null)
            simplePlayer.resume();

        super.onResume();
    }

    @Override
    protected void onDestroy() {
        if (simplePlayer.getPlayer()!=null)
            simplePlayer.destroyPlayer();
        super.onDestroy();
    }

    protected MediaSource mediaSource(String url,String agent) {
        Uri mUri = Uri.parse(url);
        @C.ContentType int type = Util.inferContentType(mUri);
        DataSource.Factory dataSourceFactory = buildDataSourceFactory(agent);
        MediaSource videoSource;
        if (type == C.TYPE_DASH)
            videoSource = new DashMediaSource.Factory(dataSourceFactory).createMediaSource(mUri);
        else if (type == C.TYPE_SS)
            videoSource = new SsMediaSource.Factory(dataSourceFactory).createMediaSource(mUri);
        else if (type == C.TYPE_HLS)
            videoSource = new HlsMediaSource.Factory(dataSourceFactory).createMediaSource(mUri);
        else {
            videoSource = new ProgressiveMediaSource.Factory(dataSourceFactory).createMediaSource(mUri);
        }
        return videoSource;
    }

    public ShimmerNormalAdapter shimmerNormalAdapter(){
        return shimmerNormalAdapter;
    }
    protected Spanned loadHtml(String data){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            return Html.fromHtml(data, Html.FROM_HTML_MODE_COMPACT);
        } else {
            return Html.fromHtml(data);
        }
    }

    public void setTitle(String title){
        TextView textView=findViewById(R.id.title);
        if (textView!=null && title!=null)
        {
            textView.setText(capitalizeFirstLetter(title));
        }
    }

    public void loading(boolean isLoading){
        this.isLoading=isLoading;
        ProgressBar progressBar=findViewById(R.id.progressBar);
        if (progressBar!=null)
            progressBar.setVisibility(getVisibility(isLoading));
    }

    public void showToolbar(boolean visible){
        if (getToolbar()!=null)
            getToolbar().setVisibility(getVisibility(visible));
    }

    public void enableBack(boolean btBack){
        if (getToolbar()!=null)
            findViewById(R.id.btBack).setVisibility(getVisibility(btBack));
    }

    public LinearLayout getToolbar(){
        return findViewById(R.id.toolbar);
    }
    private int getVisibility(boolean visibility){
        return visibility?View.VISIBLE:View.GONE;
    }

    public boolean isLoading() {
        return isLoading;
    }

    public void onToolbarBackPressed(View view) {
        finish();
    }

    protected void hideElements(View... view){
        for (View v : view) {
            v.setVisibility(View.GONE);
        }
    }

    public int calculateNoOfColumns() {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        float dpWidth = displayMetrics.widthPixels / displayMetrics.density;
        int noOfColumns = (int) (dpWidth / 120);
        return noOfColumns;
    }

    public void settingStatusBarTransparent() {
        Window w = getWindow();

        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.KITKAT) {
            w.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            w.setFlags(FLAG_TRANSLUCENT_NAVIGATION, FLAG_TRANSLUCENT_NAVIGATION);
            w.setFlags(FLAG_TRANSLUCENT_STATUS, FLAG_TRANSLUCENT_STATUS);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            w.setNavigationBarColor(Color.BLACK);
            w.getDecorView().setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            w.setStatusBarColor(Color.TRANSPARENT);
        }
    }
    public void showStatusBar(){
        Window w = getWindow();

        w.clearFlags(FLAG_TRANSLUCENT_NAVIGATION);
        w.clearFlags(FLAG_TRANSLUCENT_STATUS);
        w.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
    }
    public Drawable shimmer(){
        return Utils.shimmer();
    }
}