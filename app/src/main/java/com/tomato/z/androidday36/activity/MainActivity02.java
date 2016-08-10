package com.tomato.z.androidday36.activity;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tomato.z.androidday36.R;

import java.io.IOException;

/**
 * PACKAGE_NAME: com.tomato.z.androidday36.activity
 * FUNCTIONAL_DESCRIPTION：
 * CREATE_BY： 闫
 * CREATE_TIME: 2016/8/4 11:28
 * MODIFICATORY_DESCRIPTION:
 * MODIFY_BY:
 * MODIFICATORY_TIME：
 */
public class MainActivity02 extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main02);
    }
    public void click(View v){
        //第一步
        final MediaPlayer mpr = new MediaPlayer();
        //加载网络资源
        try {
            //第二步
//            mpr.setDataSource("http://www.yinguit.com/duomeiti/xpg.mps");
            mpr.setDataSource("/mnt/sdcard/yanyuan.mp3");
            //第三步
            mpr.prepare();//本地资源（同步）
            mpr.prepareAsync();//网络资源（异步）
            mpr.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mpr.start();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
