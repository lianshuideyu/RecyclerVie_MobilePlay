package com.atguigu.recyclerview_mobileplay.pager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.recyclerview_mobileplay.R;
import com.atguigu.recyclerview_mobileplay.adapter.MyAdapter;
import com.atguigu.recyclerview_mobileplay.basefragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/5/30.
 */

public class RecyclerViewPager extends BaseFragment{


    private String url = "http://s.budejie.com/topic/list/jingxuan/1/budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";

    private RecyclerView mRecyclerView;
    private List<String> mDatas;
    private MyAdapter adapter;

    private ProgressBar progressbar;
    private TextView tv_nomedia;
    @Override
    public View initView() {
        Log.e("TAG", "NetAudioPager-initView");
        View view = View.inflate(context, R.layout.recyclerview, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);

        return view;
    }

    @Override
    public void initData() {

        mDatas = new ArrayList<>();
        for (int i = 'A'; i < 'z'; i++)
        {
            mDatas.add("" + (char) i);
        }

        adapter = new MyAdapter(context,mDatas);
        //1.线性布局类型
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        mRecyclerView.setAdapter(adapter);

        super.initData();
    }
}
