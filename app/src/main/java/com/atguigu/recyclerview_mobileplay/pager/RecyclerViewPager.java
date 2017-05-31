package com.atguigu.recyclerview_mobileplay.pager;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.atguigu.recyclerview_mobileplay.R;
import com.atguigu.recyclerview_mobileplay.adapter.NetAudioFragmentAdapter;
import com.atguigu.recyclerview_mobileplay.basefragment.BaseFragment;
import com.atguigu.recyclerview_mobileplay.domain.NetAudioBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2017/5/30.
 */

public class RecyclerViewPager extends BaseFragment{


    private String netUrl = "http://s.budejie.com/topic/list/jingxuan/1/" +
            "budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592" +
            "&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";

    private RecyclerView mRecyclerView;
    private ProgressBar progressbar;
    private TextView tv_nomedia;
    private List<NetAudioBean.ListBean> datas;
    private NetAudioFragmentAdapter myAdapter;
    @Override
    public View initView() {
        Log.e("TAG", "NetAudioPager-initView");
        View view = View.inflate(context, R.layout.recyclerview, null);

        mRecyclerView = (RecyclerView) view.findViewById(R.id.id_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));

        progressbar = (ProgressBar) view.findViewById(R.id.progressbar);
        tv_nomedia = (TextView) view.findViewById(R.id.tv_nomedia);

        return view;
    }


    @Override
    public void initData() {
        Log.e("TAG", "NetAudioPager-initData");
        super.initData();

        getDataFromNet();
    }

    private void getDataFromNet() {
        RequestParams reques = new RequestParams(netUrl);
        x.http().get(reques, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                LogUtil.e("onSuccess联网成功==" + result);

                processData(result);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {
                LogUtil.e("onError联网失败==" + ex.getMessage());
            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {
                LogUtil.e("onFinished==");
            }
        });

    }

    private void processData(String result) {
        Gson gson = new Gson();
        Log.e("TAG", "下拉刷新--processData" + result);
        NetAudioBean netAudioBean = gson.fromJson(result, NetAudioBean.class);
        List<NetAudioBean.ListBean> list = netAudioBean.getList();
        datas = list;

        if (datas != null && datas.size() > 0) {
            Log.e("TAG","下拉刷新--processData--datas.size()==" + datas.size());
            //集合有数据
            tv_nomedia.setVisibility(View.GONE);

            //设置适配器
            myAdapter = new NetAudioFragmentAdapter(context, datas);

            mRecyclerView.setAdapter(myAdapter);
        } else {
            //么有数据
            tv_nomedia.setVisibility(View.VISIBLE);
        }

        progressbar.setVisibility(View.GONE);

    }
}
