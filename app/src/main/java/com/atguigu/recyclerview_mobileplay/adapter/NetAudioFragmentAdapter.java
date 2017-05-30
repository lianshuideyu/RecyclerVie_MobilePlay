package com.atguigu.recyclerview_mobileplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.atguigu.recyclerview_mobileplay.R;
import com.atguigu.recyclerview_mobileplay.domain.NetAudioBean;

import java.util.List;

/**
 * Created by Administrator on 2017/5/30.
 */

public class NetAudioFragmentAdapter extends RecyclerView.Adapter<NetAudioFragmentAdapter.BaseViewHolder>{
    private  Context mContext;
    private  List<NetAudioBean.ListBean> datas;

    public NetAudioFragmentAdapter(Context context, List<NetAudioBean.ListBean> datas) {
        this.mContext = context;
        this.datas = datas;

    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    class BaseViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public BaseViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);

        }
    }
}
