package com.atguigu.viewpager.pager;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.TextView;


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

public class NetAudioPager extends LinearLayout {
    private final Context context;
    private TextView tv;
    private Paint paint;

    public NetAudioPager(Context context) {
        this(context,null);

    }

    public NetAudioPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("TAG","NetAudioPager构造器");
        this.context = context;
        //初始化视图
        initView();
    }

    private void initView() {
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setTextSize(30);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawText("网络音频",0,0,paint);
    }
}
