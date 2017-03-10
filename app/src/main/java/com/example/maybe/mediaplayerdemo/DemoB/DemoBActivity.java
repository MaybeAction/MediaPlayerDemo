package com.example.maybe.mediaplayerdemo.DemoB;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.maybe.mediaplayerdemo.R;
import com.example.maybe.mediaplayerdemo.VideoUrlRes;

public class DemoBActivity extends AppCompatActivity {
    private VideoView mVideoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_b);

        mVideoView= (VideoView) findViewById(R.id.vv);
        //设置数据源
        mVideoView.setVideoPath(VideoUrlRes.getTestVideo1());
        mVideoView.start();
        //实例化控制器
        MediaController controller=new MediaController(this);
        mVideoView.setMediaController(controller);
    }
}
