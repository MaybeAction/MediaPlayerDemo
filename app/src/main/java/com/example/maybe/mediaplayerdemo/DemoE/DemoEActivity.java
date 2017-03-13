package com.example.maybe.mediaplayerdemo.DemoE;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.maybe.mediaplayerdemo.R;
import com.example.maybe.mediaplayerdemo.VideoUrlRes;

import io.vov.vitamio.MediaPlayer;
import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoEActivity extends AppCompatActivity {
    private TextView tvDownloadRate,tvloadRate;
    private ProgressBar prb;
    private VideoView vv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_e);
        tvDownloadRate= (TextView) findViewById(R.id.demo_e_tvDownloadRate);
        tvloadRate= (TextView) findViewById(R.id.demo_e_tvLoadRate);
        prb= (ProgressBar) findViewById(R.id.demo_e_prb);
        vv= (VideoView) findViewById(R.id.demo_e_vv);

        //第一步，在准备监听中设置缓冲大小
        vv.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                //单位是b，1024b=1kb*512=0.5M
                vv.setBufferSize(1024*512);
            }
        });

        //第二步，在准备监听中监听缓冲状态（开始，结束。下载速率变化）
        vv.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {

                //Mediaplayer对象，状态，改变的值
                switch (what){
                    case MediaPlayer.MEDIA_INFO_BUFFERING_START:
                        startBuffer();//开始缓冲
                        break;

                    case MediaPlayer.MEDIA_INFO_BUFFERING_END:
                        endBuffer();//缓冲结束
                        break;
                    case MediaPlayer.MEDIA_INFO_DOWNLOAD_RATE_CHANGED:
                        //改变下载的速率显示，extra即为当前下载的苏打绿
                        if(extra>=1024){
                            tvDownloadRate.setText(extra/1024+"m/s");
//                            Log.e("ssssss",extra/1024+"m/s");
                        }else{

                        tvDownloadRate.setText(extra+"kb/s");
//                            Log.e("cccccc",extra+"kb/s");
                        }
                        break;
                }
                return false;
            }
        });


        //第三步，在缓冲更新监听中获取缓冲百分比
        vv.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener() {
            @Override
            public void onBufferingUpdate(MediaPlayer mp, int percent) {
                //percent即为当前缓冲的百分比
                tvloadRate.setText(percent+"%");
//                Log.e("vvvvvvv",percent+"%");

            }
        });

        vv.setVideoPath(VideoUrlRes.getTestVideo2());
        vv.start();

        //设置控制器
        MediaController controller=new MediaController(this);
        vv.setMediaController(controller);
    }


    //结束缓冲
    private void endBuffer() {
        //开始播放
        vv.start();
        prb.setVisibility(View.INVISIBLE);
        tvDownloadRate.setVisibility(View.INVISIBLE);
        tvloadRate.setVisibility(View.INVISIBLE);
        //初始化缓冲值
        tvDownloadRate.setText("");
        tvloadRate.setText("");
    }

    private void startBuffer() {
        //如果说处于播放状态，暂停播放
        if(vv.isPlaying()){
            vv.pause();
        }
        //缓冲UI的显示
        prb.setVisibility(View.VISIBLE);
        tvDownloadRate.setVisibility(View.VISIBLE);
        tvloadRate.setVisibility(View.VISIBLE);
    }
}
