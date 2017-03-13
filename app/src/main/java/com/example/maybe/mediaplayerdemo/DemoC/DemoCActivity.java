package com.example.maybe.mediaplayerdemo.DemoC;

import android.graphics.PixelFormat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.maybe.mediaplayerdemo.R;
import com.example.maybe.mediaplayerdemo.VideoUrlRes;

import java.io.IOException;

import io.vov.vitamio.MediaPlayer;

public class DemoCActivity extends AppCompatActivity {
    private SurfaceView surfaceView;
    private SurfaceHolder surfaceHolder;
    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_c);
        surfaceView= (SurfaceView) findViewById(R.id.demo_c_sv);

        surfaceHolder=surfaceView.getHolder();
        surfaceHolder.setFormat(PixelFormat.RGBA_8888);
        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder surfaceHolder) {
                try {
                     mediaPlayer=new MediaPlayer(DemoCActivity.this);
                     mediaPlayer.setDisplay(surfaceHolder);//绑定
                     mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
                     mediaPlayer.prepareAsync();
                     mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                        @Override
                        public void onPrepared(MediaPlayer mp) {
                                //准备好了之后进行播放
                            mediaPlayer.start();
                        }
                    });


                    //Vitamio5.0需要进行audio处理，才能对在线视频进行播放
                    mediaPlayer.setOnInfoListener(new MediaPlayer.OnInfoListener() {
                        @Override
                        public boolean onInfo(MediaPlayer mp, int what, int extra) {
                            //如果能正常打开再进行audio的处理
                            if(what==MediaPlayer.MEDIA_INFO_FILE_OPEN_OK){
                                mediaPlayer.audioInitedOk(mediaPlayer.audioTrackInit());

                            return true;
                            }
                            return  false;
                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
                //停止并释放资源
                mediaPlayer.stop();
                mediaPlayer.release();
            }
        });
    }
}
