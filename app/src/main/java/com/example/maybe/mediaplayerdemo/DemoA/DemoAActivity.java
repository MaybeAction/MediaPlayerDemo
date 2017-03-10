package com.example.maybe.mediaplayerdemo.DemoA;

import android.graphics.SurfaceTexture;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.TextureView;

import com.example.maybe.mediaplayerdemo.R;
import com.example.maybe.mediaplayerdemo.VideoUrlRes;

import java.io.IOException;

public class DemoAActivity extends AppCompatActivity {
    private SurfaceView mSurfaceView;
    private    MediaPlayer mediaPlayer;
    private TextureView mTextureView;
    private Surface mSurface;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_a);

        //拿到textureView控件
        mTextureView= (TextureView) findViewById(R.id.ttv);

        //添加监听
        mTextureView.setSurfaceTextureListener(new TextureView.SurfaceTextureListener() {
            @Override
            public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i1) {
                try {
                        //当SurfaceTexture创建好的时候
                        mediaPlayer=new MediaPlayer();
                        mSurface=new Surface(surfaceTexture);//拿到surface实例
                mediaPlayer.setSurface(mSurface);//将surface绑定到mediaplayer身上
                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
                    //异步准备
                    mediaPlayer.prepareAsync();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                    @Override
                    public void onPrepared(MediaPlayer mediaPlayer) {
                        mediaPlayer.start();
                    }
                });
            }


            @Override
            public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i1) {
                        //SurfaceTexture缓冲区大小发生改变的时候触发
            }

            @Override
            public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
                //SurfaceTexture即将销毁时触发此方法
                mediaPlayer.stop();
                mSurface.release();//释放相关资源
                mSurface=null;
                return false;
            }

            @Override
            public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
                //SurfaceTexture更新时触发

            }
        });

        mTextureView.setAlpha(0.5f);//透明度
        mTextureView.setRotation(50.0f);//旋转度

    }
//
//        mSurfaceView= (SurfaceView) findViewById(R.id.sv);
//        //surfaceView中内嵌了一个专门用于绘制的surface，为我们做视频画面的处理
//        //如何拿到surface
//        //surfaceView提供了surfaceHolder接口方位这个surface，getHolder()方法可以得到这个接口
//        SurfaceHolder surfaceHolder=mSurfaceView.getHolder();//画面绘制的持有者
//        //我们怎么知道surface有没有创建好，有没有变化 有没有销毁
//        surfaceHolder.addCallback(new SurfaceHolder.Callback() {
//            @Override
//            public void surfaceCreated(SurfaceHolder surfaceHolder) {
//                        //Surface创建成功时触发
//                //拿到MediaPlayer实例
//                mediaPlayer=new MediaPlayer();
//
//                //绑定surfaceHolder
//                mediaPlayer.setDisplay(surfaceHolder);
//
//                //设置数据源
//                try {
//                    mediaPlayer.setDataSource(VideoUrlRes.getTestVideo1());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//                //准备播放（异步）
//                mediaPlayer.prepareAsync();
//                //设置是否准备好监听
//                mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mediaPlayer) {
//                        //当准备好播放时触发
//
//                        mediaPlayer.start();
//                    }
//                });
//            }
//
//            @Override
//            public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {
//                        //surface大小改变时触发
//            }
//
//            @Override
//            public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
//                        //surface销毁时触发
//                        mediaPlayer.stop();
//                //释放
//                        mediaPlayer.release();
//            }
//        });
//
//
//
//        //如果要看视频图像，需要使用SurfaceView
//    }

}
