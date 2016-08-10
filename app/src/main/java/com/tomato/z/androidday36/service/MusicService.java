package com.tomato.z.androidday36.service;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;

import com.tomato.z.androidday36.activity.MediaPlayerActivity;

import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * PACKAGE_NAME: com.tomato.z.androidday36.service
 * FUNCTIONAL_DESCRIPTION：
 * CREATE_BY： 闫
 * CREATE_TIME: 2016/8/4 14:41
 * MODIFICATORY_DESCRIPTION:
 * MODIFY_BY:
 * MODIFICATORY_TIME：
 */
public class MusicService extends Service {
    MediaPlayer player;

    @Override
    public void onCreate() {
        super.onCreate();
        //初始化MediaPlayer
        player = new MediaPlayer();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return new MyBinder();
    }

    //播放音乐
    public void playMusic() {
        try {
            player.reset();
            player.setDataSource("http://www.yinguit.com/duomeiti/xpg.mp3");
            player.prepareAsync();
            player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    player.start();
                    //更新进度条
                    updataSeekBar();
                }
            });

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseMusic() {
        player.pause();
    }

    public void rePlayMusic() {
        player.start();
    }

    public void seekToPosition(int position) {
        player.seekTo(position);
    }

    public void updataSeekBar(){
        //获取到歌曲当前的总长度
        final int duration = player.getDuration();
        //一秒钟获取一次当前的位置
        final Timer timer = new Timer();
        final TimerTask task = new TimerTask() {
            @Override
            public void run() {
                //获取到当前歌曲的进度
             int currentPosition = player.getCurrentPosition();
                //创建Message
                Message message = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putInt("duration", duration);
                bundle.putInt("currentPosition", currentPosition);
                message.setData(bundle);
                MediaPlayerActivity.handler.sendMessage(message);
            }
        };
        //300毫秒后每隔1000毫秒就执行一次TimerTask
        timer.schedule(task, 300, 1000);
        //当前歌曲播放完成的时候调用，需要将timer和task取消掉
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                timer.cancel();
                task.cancel();
            }
        });
    }

    class MyBinder extends Binder implements IService {

        @Override
        public void callPlayerMusic() {
            playMusic();
        }


        @Override
        public void callPauseMusic() {
            pauseMusic();
        }


        @Override
        public void callRePlayerMusic() {
            rePlayMusic();
        }


        @Override
        public void callSeekToPosition(int position) {
            seekToPosition(position);
        }

    }
}
