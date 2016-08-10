package com.tomato.z.androidday36.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.VideoView;

import com.tomato.z.androidday36.R;

import io.vov.vitamio.LibsChecker;
import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;

public class MainActivity extends AppCompatActivity {
VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //VideoView的基本用法 支持的格式MP4，3GP
        vv = (VideoView) findViewById(R.id.vv);
        vv.setVideoPath("http://www.yinguit.com/duomeiti/cc.mp4");
        vv.start();
        //rmvb，avi,mkb...
        if (!LibsChecker.checkVitamioLibs(this))
            return;
        final io.vov.vitamio.widget.VideoView vvv = (io.vov.vitamio.widget.VideoView) findViewById(R.id.vvv);
        vvv.setVideoPath("http://www.yinguit.com/duomeiti/cc.mp4");
        vvv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                vvv.start();
            }
        });
        vvv.setMediaController(new MediaController(this));
    }
}
