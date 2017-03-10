package com.example.maybe.mediaplayerdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.maybe.mediaplayerdemo.DemoA.DemoAActivity;
import com.example.maybe.mediaplayerdemo.DemoB.DemoBActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView tv_surface,tv_surface1,tv_surface2,tv_surface3,tv_surface4;
    private String []datas = new String[]{
            "Android MediaPlayer + SurfaceView/TextureView",
            "Android VideoView + MediaController",
            "Vitamio MediaPlayer + SurfaceView/TextureView",
            "Vitamio VideoView + MediaController",
            "Vitamio中VideoView Buffer 缓冲处理"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    private void initView() {
        tv_surface= (TextView) findViewById(R.id.tv_surface);
        tv_surface1= (TextView) findViewById(R.id.tv_surface1);
        tv_surface2= (TextView) findViewById(R.id.tv_surface2);
        tv_surface3= (TextView) findViewById(R.id.tv_surface3);
        tv_surface4= (TextView) findViewById(R.id.tv_surface4);

        tv_surface.setText(datas[0]);
        tv_surface1.setText(datas[1]);
        tv_surface2.setText(datas[2]);
        tv_surface3.setText(datas[3]);
        tv_surface4.setText(datas[4]);



        tv_surface.setOnClickListener(this);
        tv_surface1.setOnClickListener(this);
        tv_surface2.setOnClickListener(this);
        tv_surface3.setOnClickListener(this);
        tv_surface4.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_surface:
                Intent intent=new Intent(MainActivity.this, DemoAActivity.class);
                startActivity(intent);
                break;
            case R.id.tv_surface1:
                Intent intent1=new Intent(MainActivity.this, DemoBActivity.class);
                startActivity(intent1);
                break;
            case R.id.tv_surface2:
                Intent intent2=new Intent(MainActivity.this, DemoAActivity.class);
                startActivity(intent2);
                break;
            case R.id.tv_surface3:
                Intent intent3=new Intent(MainActivity.this, DemoAActivity.class);
                startActivity(intent3);
                break;
            case R.id.tv_surface4:
                Intent intent4=new Intent(MainActivity.this, DemoAActivity.class);
                startActivity(intent4);
                break;

        }
    }
}
