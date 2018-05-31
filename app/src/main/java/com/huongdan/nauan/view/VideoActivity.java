package com.huongdan.nauan.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;
import com.huongdan.nauan.R;
import com.huongdan.nauan.utils.Global;

public class VideoActivity extends YouTubeBaseActivity {
    private YouTubePlayerView youTubePlayerView;
    private String videoId="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        findView();
        configView();
    }


    public void findView() {
        youTubePlayerView = findViewById(R.id.youtubePlayer);
    }

    public void configView() {
        Intent intent = getIntent();
        String name = intent.getStringExtra(Global.EXTRA_NAME_KEY);
        videoId = intent.getStringExtra(Global.EXTRA_ID_KEY);

        youTubePlayerView.initialize(Global.DEVELOPER_KEY, new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {

                youTubePlayer.setPlaybackEventListener(new YouTubePlayer.PlaybackEventListener() {
                    @Override
                    public void onPlaying() {

                    }

                    @Override
                    public void onPaused() {

                    }

                    @Override
                    public void onStopped() {

                    }

                    @Override
                    public void onBuffering(boolean b) {

                    }

                    @Override
                    public void onSeekTo(int i) {

                    }
                });
                youTubePlayer.setPlayerStateChangeListener(new YouTubePlayer.PlayerStateChangeListener() {
                    @Override
                    public void onLoading() {
                    }

                    @Override
                    public void onLoaded(String s) {

                    }

                    @Override
                    public void onAdStarted() {

                    }

                    @Override
                    public void onVideoStarted() {

                    }

                    @Override
                    public void onVideoEnded() {
                    }

                    @Override
                    public void onError(YouTubePlayer.ErrorReason errorReason) {

                    }
                });
                youTubePlayer.loadVideo(videoId);
            }

            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
            }
        });
    }



}
