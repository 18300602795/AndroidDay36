package com.tomato.z.androidday36.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.NumberPicker;

import com.tomato.z.androidday36.R;

import java.io.IOException;

/**
 * PACKAGE_NAME: com.tomato.z.androidday36.activity
 * FUNCTIONAL_DESCRIPTION：
 * CREATE_BY： 闫
 * CREATE_TIME: 2016/8/4 14:08
 * MODIFICATORY_DESCRIPTION:
 * MODIFY_BY:
 * MODIFICATORY_TIME：
 */
public class VideoPlayerActivity extends Activity {
    SurfaceView sfv;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_layout);
        sfv = (SurfaceView) findViewById(R.id.sfv);
        SurfaceHolder holder = sfv.getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                //初始化MediaPlayer
                player = new MediaPlayer();
                //设置播放的资源
                try {
                    player.setDataSource("http://www.yinguit.com/duomeiti/cc.mp4");
                    //设置播放视频的内容SurfaceHolder，是用来维护视频播放内容的
                    player.setDisplay(holder);
                    //开始播放视频
                    player.prepareAsync();
                    player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                            player.start();
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                if (player != null && player.isPlaying()) {
                    player.stop();
                }
            }
        });

    }
}
