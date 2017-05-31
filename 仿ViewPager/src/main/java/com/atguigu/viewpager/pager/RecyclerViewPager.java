package com.atguigu.viewpager.pager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.atguigu.viewpager.NetAudioFragmentAdapter;
import com.atguigu.viewpager.domain.NetAudioBean;
import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.common.util.LogUtil;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;


/**
 * Created by Administrator on 2017/5/30.
 */

/**
 * 所有的视图从创建到显示在屏幕上执行的主要过程
 * 如果是普通的View(Button,TextView)
 * 1.构造方法-该类就被初始化了，有三个构造方法
 * 2.测量 measure --> onMeasure()
 * 测量该控件的宽和高，并且使用setMeasuredDimension保存测量结果
 * <p>
 * 3. 绘制-draw()-->onDraw()
 * 把当前控件绘制到屏幕上
 * <p>
 * -----------------------------------------------------
 * 如果该View是一个ViewGroup
 * 1.构造方法-该类就被初始化了，有三个构造方法
 * 2.测量 measure --> onMeasure()
 * 测量该控件的宽和高，并且使用setMeasuredDimension保存测量结果
 * <p>
 * 3.布局 layout-->onLayout
 * 用于指导子视图的位置，而且必须得实现该方法，因为该方法是抽象的。
 * <p>
 * 4.绘制-调用各个孩子绘制
 */

public class RecyclerViewPager extends RelativeLayout {
    private Context context;
    private RecyclerView mRecyclerView;
    private ProgressBar progressbar;
    private TextView tv_nomedia;


    private String netUrl = "http://s.budejie.com/topic/list/jingxuan/1/" +
            "budejie-android-6.2.8/0-20.json?market=baidu&udid=863425026599592" +
            "&appname=baisibudejie&os=4.2.2&client=android&visiting=&mac=98%3A6c%3Af5%3A4b%3A72%3A6d&ver=6.2.8";
    private List<NetAudioBean.ListBean> datas;
    private NetAudioFragmentAdapter myAdapter;

    public RecyclerViewPager(Context context) {
        this(context,null);

    }

    public RecyclerViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("TAG","RecyclerViewPager构造器");
        this.context = context;

    }

    /**
     * 布局加载完成时调用
     */
    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();

        mRecyclerView = (RecyclerView) getChildAt(0);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
        progressbar = (ProgressBar) getChildAt(1);
        tv_nomedia = (TextView) getChildAt(2);

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

}
