package com.tomato.z.androidday36.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.view.View;
import android.widget.SeekBar;

import com.tomato.z.androidday36.R;
import com.tomato.z.androidday36.service.IService;
import com.tomato.z.androidday36.service.MusicService;

/**
 * PACKAGE_NAME: com.tomato.z.androidday36.activity
 * FUNCTIONAL_DESCRIPTION：
 * CREATE_BY： 闫
 * CREATE_TIME: 2016/8/4 14:35
 * MODIFICATORY_DESCRIPTION:
 * MODIFY_BY:
 * MODIFICATORY_TIME：
 */
public class MediaPlayerActivity extends Activity {
    SeekBar seekBar;
    public static Handler handler;
    MyConn conn;
    IService iservice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_media_player);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        //通过服务启动
        handler = new Handler(){
            //当接收到消息后执行该方法
            public void handleMessage(Message message){
                Bundle bundle = message.getData();
                int duration = bundle.getInt("duration");
                int currentPosition = bundle.getInt("currentPosition");
                //更新seekBar
                seekBar.setMax(duration);
                seekBar.setProgress(currentPosition);
            }
        };
        Intent intent = new Intent(this, MusicService.class);
        startService(intent);
        conn = new MyConn();
        bindService(intent, conn, BIND_AUTO_CREATE);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                iservice.callSeekToPosition(progress);
            }
            //开始拖动
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
//                iservice.callSeekToPosition(seekBar.getProgress());
            }
            //停止拖动
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                iservice.callSeekToPosition(seekBar.getProgress());
            }
        });
    }


    public void click1(View view) {
        iservice.callPlayerMusic();
    }

    public void click2(View view) {
        iservice.callPauseMusic();
    }

    public void click3(View view) {
        iservice.callRePlayerMusic();
    }
    class MyConn implements ServiceConnection{



        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iservice = (IService)service;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }
}
