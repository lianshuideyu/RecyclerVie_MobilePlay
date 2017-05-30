package com.atguigu.recyclerview_mobileplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.recyclerview_mobileplay.R;

import java.util.List;

/**
 * Created by Administrator on 2017/5/30.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private final List<String> mDatas;
    private final Context context;

    public MyAdapter(Context context, List<String> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    //定义接口设置点击监听
    public interface OnItemClickLitener {
        void onItemClick(View view, int position);
        void onItemLongClick(View view , int position);
    }
    public OnItemClickLitener listener;

    public void setOnItemClickLitener(OnItemClickLitener listener) {
        this.listener = listener;
    }
    //------------------------------------------------------

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context)
                .inflate(R.layout.item_home, parent, false));

        return holder;
    }

    //当视图滚动到这的时候回调该方法
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.tv.setText(mDatas.get(position));
        //如果设置点击事件，则设置回调
        if(listener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = holder.getLayoutPosition();
                    listener.onItemClick(holder.itemView,pos);
                }
            });

            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    listener.onItemLongClick(holder.itemView,pos);
                    return false;
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public MyViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);

        }
    }
}