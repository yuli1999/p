package com.example.zk_01;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity{

    private Toolbar mtool;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mtool = findViewById(R.id.tool);

        setSupportActionBar(mtool);

        getSupportActionBar().setDisplayShowTitleEnabled(true);

        getSupportActionBar().setTitle("课时作业");

        mtool.setTitleTextColor(Color.RED);

        //左侧按钮
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //按钮点击
        mtool.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

}
