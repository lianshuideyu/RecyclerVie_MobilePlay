package com.atguigu.viewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * Created by Administrator on 2017/5/31.
 */

public class MyViewPager extends ViewGroup {

    /**
     * 手势识别器
     */
    private GestureDetector detector;
    private Scroller scroller;

    public MyViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
        Log.e("TAG","MyViewPager构造器");
        scroller = new Scroller(context);

        detector = new GestureDetector(context,new GestureDetector.SimpleOnGestureListener(){

            @Override
            public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
                /**
                 * 移动视图内部内容的，根据相对距离
                 */
//                Log.e("TAG","distanceX=="+distanceX);
                /**
                 * x,在X轴移动的距离，负是从左到右是负的
                 * y,在Y轴移动的距离
                 */
                scrollBy((int)distanceX,0);
                /**
                 * 根据指定的坐标移动
                 */
                //scrollTo(x,y);
                return true;
            }
        });
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("TAG","MyViewPager---onMeasure");
        //遍历出7个一级孩子，分别测量，这7个孩子会递归测量自己的孩子
        int size = MeasureSpec.getSize(widthMeasureSpec);//父视图要给当前控件（MyViewPager）的宽
        int mode = MeasureSpec.getMode(widthMeasureSpec);//父视图要给当前控件的模式

        //内部还有孩子
        int newWidthMeasureSpec = MeasureSpec.makeMeasureSpec(size, mode);//给MyViewPager的孩子的
//        Log.e("TAG","widthMeasureSpec=="+widthMeasureSpec+",size=="+size+",mode=="+mode);
        for(int i = 0; i < getChildCount(); i++) {
            View childAt = getChildAt(i);
            //测量每个孩子
            childAt.measure(widthMeasureSpec,heightMeasureSpec);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        Log.e("TAG","MyViewPager---onLayout");
        for (int i = 0; i < getChildCount(); i++) {

            //遍历得到所有孩子
            View childAt = getChildAt(i);
            //指定位置
            childAt.layout(i * getWidth(), 0, (1 + i) * getWidth(), getHeight());
        }
    }


    //记录起始坐标
    private float startX;
    /**
     * 6个页面的下标位置
     */
    private int index;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        super.onTouchEvent(event);//调用父类方法
        detector.onTouchEvent(event);

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                //1.记录按下的坐标
                startX = event.getX();//0
                Log.e("TAG","onTouchEvent-ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.e("TAG","onTouchEvent-ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.e("TAG","onTouchEvent-ACTION_UP");
                //2.记录end坐标
                float endX = event.getX();

                int tempIndex = index;
                if(endX - startX > getWidth() / 2) {
                    tempIndex--;
                }else if(startX - endX > getWidth()/2) {
                    tempIndex++;
                }


                //根据下标移动到对应的页面
                moveToPager(tempIndex);

                break;

        }

        return true;
    }

    /**
     * 根据下标位置移动到对应的页面
     * @param tempIndex
     */
    public void moveToPager(int tempIndex) {
        //屏蔽越界
        if(tempIndex < 0) {
            tempIndex = 0;
        }
        if(tempIndex > getChildCount()-1) {
            tempIndex = getChildCount() - 1;
        }
        //赋值给外界
        index = tempIndex;
        //scrollTo(index*getWidth(),0);

        int distanceX = index * getWidth() - getScrollX();

        scroller.startScroll(getScrollX(),getScrollY(),distanceX,0,500);

        if(listener != null) {
  //调用的方法本质是MyOnPagerChangeListener类的onPagerChanger，而不是接口OnPagerChangeListener的onPagerChanger
            listener.onPagerChanger(index);
        }

        //重新绘制
        invalidate();//不只导致onDraw，还有调用computeScroll
    }

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(scroller.computeScrollOffset()) {
            float currX = scroller.getCurrX();

            scrollTo((int) currX,0);

            invalidate();
        }

    }

    //1.写接口
    //2.传入接口
    //3.调用传入接口实现类的方法
    //4.使用

    private OnPagerChangeListener listener;

    public interface OnPagerChangeListener{
        /**
         * 当页面下标改变的时候回调该方法
         * @param index 页面的下标位置
         */
        public void onPagerChanger(int index);
    }

    public void setOnPagerChangeListener(OnPagerChangeListener listener){
        this.listener = listener;
    }
}
