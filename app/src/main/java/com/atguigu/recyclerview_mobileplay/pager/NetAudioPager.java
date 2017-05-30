package com.atguigu.recyclerview_mobileplay.pager;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.atguigu.recyclerview_mobileplay.basefragment.BaseFragment;

/**
 * Created by Administrator on 2017/5/30.
 */

public class NetAudioPager extends BaseFragment{
    private TextView tv;

    @Override
    public View initView() {
        tv = new TextView(context);
        tv.setTextSize(30);
        tv.setTextColor(Color.RED);


        return tv;
    }

    @Override
    public void initData() {
        tv.setText("网络音频");

        super.initData();
    }
}
