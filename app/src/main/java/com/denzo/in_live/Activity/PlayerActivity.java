package com.denzo.in_live.Activity;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;
import androidx.annotation.Nullable;

import com.denzo.in_live.Model.MoviePlayback.VideosItem;
import com.denzo.in_live.R;
import com.denzo.in_live.Utils.Constant;
import com.denzo.in_live.datasource.CustomDataSourcesFactory;
import com.denzo.in_live.dialog.qualitySelector.TrackSelectionDialog;
import com.denzo.in_live.task.FetchZeeDrm;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.PlaybackException;
import com.google.android.exoplayer2.Player;

import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.LoadEventInfo;
import com.google.android.exoplayer2.source.MediaLoadData;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.MediaSourceEventListener;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.dash.DashMediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.ExoTrackSelection;
import com.google.android.exoplayer2.trackselection.RandomTrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelectionArray;
import com.google.android.exoplayer2.ui.AspectRatioFrameLayout;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlayerActivity extends InitActivity implements Player.Listener, View.OnClickListener{
    private PlayerView playerView;
    private SimpleExoPlayer player;
    private DashMediaSource dashMediaSource;
    private VideosItem videosItem;
    private ImageView settings, back;
    private TextView playerTitle;
    private DefaultTrackSelector trackSelector;
    private DataSource.Factory factory;
    private boolean hasContent;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_player);
        if (getIntent().getExtras() != null) {
            videosItem = (VideosItem) getIntent().getExtras().getSerializable("VideoItem");
        }
        playerView=findViewById(R.id.playerView);
        settings=playerView.findViewById(R.id.settings);
        back=playerView.findViewById(R.id.back);
        playerTitle=playerView.findViewById(R.id.player_title);

        settings.setOnClickListener(this);
        if (back != null) back.setOnClickListener(this);

        if (videosItem!=null)
        {
            if (playerTitle != null) playerTitle.setText(videosItem.getTitle());
            if (!TextUtils.isEmpty(videosItem.getLicenseUrl()))
            {
                new FetchZeeDrm(videosItem.getLicenseUrl(),this)
                        .setCallBack(drmSessionManager -> {

                            boolean isHotstar=videosItem.getFileUrl().contains("glamstar");
                            if (isHotstar)
                                factory=new CustomDataSourcesFactory(videosItem.getAgent()==null? Constant.agent:videosItem.getAgent());
                            else factory=buildDataSourceFactory(videosItem.getAgent());
                            dashMediaSource=new DashMediaSource.Factory(factory)
                                    .setDrmSessionManager(drmSessionManager)
                                    .createMediaSource(MediaItem.fromUri(Uri.parse(videosItem.getFileUrl())));
                            if (isHotstar)
                            {
                                dashMediaSource.addEventListener(new Handler(),
                                        new MediaSourceEventListener() {
                                            @Override
                                            public void onLoadCompleted(int windowIndex, @Nullable MediaSource.MediaPeriodId mediaPeriodId, LoadEventInfo loadEventInfo, MediaLoadData mediaLoadData) {
                                                Map<String, List<String>> hashMap=loadEventInfo.responseHeaders;

                                                if (hashMap!=null && loadEventInfo.uri.toString().contains(".mpd")) {
                                                    if (!hashMap.isEmpty()){
                                                        String hdntl="hdntl";
                                                        if (hashMap.get(hdntl)!=null && !hashMap.get(hdntl).isEmpty()){

                                                            HashMap<String,String> hash=new HashMap<>();
                                                            hash.put("cookie", hdntl+"="+hashMap.get(hdntl).get(0));
                                                            hash.put(hdntl, hashMap.get(hdntl).get(0));
                                                            ((CustomDataSourcesFactory)factory).setHeaders(hash);
                                                        }
                                                    }
                                                }
                                            }
                                        }
                                );
                            }

                            loadPlayer(dashMediaSource);

                        }).execute();
            }
            else {
                loadPlayer(mediaSource(videosItem.getFileUrl(),videosItem.getAgent()));
            }
        }
        else {
            Toast.makeText(this,"Something went wrong..",Toast.LENGTH_SHORT).show();
        }

    }


    @Override
    protected void onDestroy() {
        factory=null;
        dashMediaSource=null;
        super.onDestroy();
    }

    @Override
    public void onPlayerError(PlaybackException error) {
        error.printStackTrace();
        Toast.makeText(PlayerActivity.this, "Playback error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
        finish();
    }

    @Override
    public void onTracksChanged(TrackGroupArray trackGroups, TrackSelectionArray trackSelections) {
        updateSettingsVisibility();
    }

    private void updateSettingsVisibility() {
        if (!hasContent && trackSelector != null) {
            hasContent = TrackSelectionDialog.willHaveContent(trackSelector);
            if (hasContent) {
                settings.setVisibility(View.VISIBLE);
            }
        }
    }

    private void showQualityDialog(){
        simplePlayer.pause();
        TrackSelectionDialog trackSelectionDialog =
                TrackSelectionDialog.createForTrackSelector(
                        trackSelector,
                        dismissedDialog -> simplePlayer.resume());

        trackSelectionDialog.show(getSupportFragmentManager(),  null);
    }
    private DefaultTrackSelector trackSelector(){
        ExoTrackSelection.Factory trackSelectionFactory = new RandomTrackSelection.Factory();
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(this, trackSelectionFactory);
        DefaultTrackSelector.ParametersBuilder builder =
                new DefaultTrackSelector.ParametersBuilder(this);
        builder.setMaxVideoSizeSd();

        trackSelector.setParameters(builder.build());
        return trackSelector;
    }

    private void loadPlayer(MediaSource mediaSource){
        trackSelector=trackSelector();
        player = simplePlayer.setTrackSelector(trackSelector).createPlayer(this);
        player.addListener(this);
        player.setMediaSource(mediaSource);
        player.prepare();
        player.setVideoScalingMode(C.VIDEO_SCALING_MODE_SCALE_TO_FIT_WITH_CROPPING);
        playerView.setResizeMode(AspectRatioFrameLayout.RESIZE_MODE_FILL);
        playerView.setUseController(true);
        playerView.setKeepScreenOn(true);
        playerView.setShowBuffering(PlayerView.SHOW_BUFFERING_ALWAYS);
        player.setPlayWhenReady(true);
        playerView.setPlayer(player);
        updateSettingsVisibility();
    }

    @Override
    public void onPlaybackStateChanged(int playbackState) {
        if (playbackState == Player.STATE_BUFFERING) {
            loading(true);
        } else if (playbackState == Player.STATE_READY || playbackState == Player.STATE_IDLE) {
            loading(false);
        }
    }


    @Override
    public void onClick(View v) {
        int id=v.getId();
        if (id==settings.getId()){
            showQualityDialog();
        } else if (back != null && id == back.getId()) {
            finish();
        }
    }
}