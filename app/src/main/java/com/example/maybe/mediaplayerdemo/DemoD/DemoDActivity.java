package com.example.maybe.mediaplayerdemo.DemoD;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.maybe.mediaplayerdemo.R;
import com.example.maybe.mediaplayerdemo.VideoUrlRes;

import io.vov.vitamio.widget.MediaController;
import io.vov.vitamio.widget.VideoView;

public class DemoDActivity extends AppCompatActivity {
    private VideoView videoView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demo_d);
            videoView= (VideoView) findViewById(R.id.demo_d_vd);
        videoView.setVideoPath(VideoUrlRes.getTestVideo1());
        videoView.start();

        //实例化控制器(要使用vitamio中的控制器)
        MediaController controller=new MediaController(DemoDActivity.this);
        videoView.setMediaController(controller);
    }
}
