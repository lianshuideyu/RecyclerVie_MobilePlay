package com.atguigu.viewpager;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements MyViewPager.OnPagerChangeListener {
    private RadioGroup rg_bottom;
    private MyViewPager my_viewpager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rg_bottom = (RadioGroup) findViewById(R.id.rg_bottom);
        my_viewpager = (MyViewPager)findViewById(R.id.my_viewpager);


        initData();

        rg_bottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int checkedId) {
                Log.e("TAG","checkedId==" + checkedId);
                //方法一
                my_viewpager.moveToPager(checkedId);

            }
        });
        //默认选择0id的RadioButton
        rg_bottom.check(0);

        //实现接口的实现类
//        MyOnPagerChangeListener myOnPagerChangeListener = new MyOnPagerChangeListener();
        //方法一
        my_viewpager.setOnPagerChangeListener(this);
    }

    private void initData() {

        TextView text = new TextView(this);
        //要设置文字
        text.setText("本地视频");
        text.setTextSize(30);
        text.setTextColor(Color.RED);
        my_viewpager.addView(text);

        text = new TextView(this);
        text.setText("本地音频");
        text.setTextSize(30);
        text.setTextColor(Color.RED);
        my_viewpager.addView(text);

        text = new TextView(this);
        text.setText("网络歌曲");
        text.setTextSize(30);
        text.setTextColor(Color.RED);
        my_viewpager.addView(text);

        text = new TextView(this);
        text.setText("网络视频");
        text.setTextSize(30);
        text.setTextColor(Color.RED);
        my_viewpager.addView(text);


        //添加recyclerview页面
        View testView =  View.inflate(this,R.layout.recyclerview,null);
        my_viewpager.addView(testView);

        //为RadioGroup中的RadioButton设置Id
        for (int i = 0; i < rg_bottom.getChildCount(); i++) {
           rg_bottom.getChildAt(i).setId(i);
            //0~5

        }
    }


    @Override
    public void onPagerChanger(int index) {
        rg_bottom.check(index);
    }
}