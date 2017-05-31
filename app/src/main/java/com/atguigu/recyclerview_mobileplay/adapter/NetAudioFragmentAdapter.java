package com.atguigu.recyclerview_mobileplay.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
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


    /**
     * 视频
     */
    private static final int TYPE_VIDEO = 0;

    /**
     * 图片
     */
    private static final int TYPE_IMAGE = 1;

    /**
     * 文字
     */
    private static final int TYPE_TEXT = 2;

    /**
     * GIF图片
     */
    private static final int TYPE_GIF = 3;

    /**
     * 软件推广
     */
    private static final int TYPE_AD = 4;

    public NetAudioFragmentAdapter(Context context, List<NetAudioBean.ListBean> datas) {
        this.mContext = context;
        this.datas = datas;

    }


    @Override
    public int getItemCount() {
        return datas.size();
    }

    @Override
    public int getItemViewType(int position) {
        int itemViewType = -1;
        NetAudioBean.ListBean listBean = datas.get(position);
        //根据位置，从列表中得到一个数据对象
        String type = listBean.getType();
        if ("video".equals(type)) {
            itemViewType = TYPE_VIDEO;
        } else if ("image".equals(type)) {
            itemViewType = TYPE_IMAGE;
        } else if ("text".equals(type)) {
            itemViewType = TYPE_TEXT;
        } else if ("gif".equals(type)) {
            itemViewType = TYPE_GIF;
        } else {
            itemViewType = TYPE_AD;//广播
        }
        return itemViewType;
    }

    /**
     * 这里创建viewHolder的实例
     * @param parent
     * @param viewType 代表item的类型，当重写了getItemViewType方法后，viewType真是有效
     * @return
     */
    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        BaseViewHolder baseViewHolder = null;
        switch (viewType) {
            case TYPE_VIDEO :
                baseViewHolder = new VideoHoder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_home, parent, false));
                Log.e("TAG","onCreateViewHolder---TYPE_VIDEO");
                break;
            case TYPE_IMAGE :
                baseViewHolder = new ImageHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_home, parent, false));
                break;
            case TYPE_TEXT :
                baseViewHolder = new TextHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_home, parent, false));
                break;
            case TYPE_GIF :
                baseViewHolder = new GifHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_home, parent, false));
                break;
            case TYPE_AD :
                baseViewHolder = new ADHolder(LayoutInflater.from(mContext)
                        .inflate(R.layout.item_home, parent, false));
                break;

        }

        return baseViewHolder;
    }

    /**
     * //当视图滚动到这的时候回调该方法
     * @param holder
     * @param position
     * 在这里设置不同的item的数据,或点击事件
     */
    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        int itemViewType = getItemViewType(position);
        switch (itemViewType) {
            case TYPE_VIDEO :
                holder.tv.setText("视频");

                break;
            case TYPE_IMAGE :
                holder.tv.setText("image");
                break;
            case TYPE_TEXT :
                holder.tv.setText("text");
                break;
            case TYPE_GIF :
                holder.tv.setText("gif");
                break;
            case TYPE_AD :
                holder.tv.setText("AD");
                break;
        }

    }



    class BaseViewHolder extends RecyclerView.ViewHolder{
        TextView tv;

        public BaseViewHolder(View itemView) {
            super(itemView);
            tv = (TextView) itemView.findViewById(R.id.id_num);

        }
    }

    class VideoHoder extends BaseViewHolder{

        public VideoHoder(View itemView) {
            super(itemView);
        }
    }

    class ImageHolder extends BaseViewHolder{

        public ImageHolder(View itemView) {
            super(itemView);
        }
    }

    class TextHolder extends BaseViewHolder{

        public TextHolder(View itemView) {
            super(itemView);
        }
    }

    class GifHolder extends BaseViewHolder{
        public GifHolder(View itemView) {
            super(itemView);
        }
    }

    class ADHolder extends BaseViewHolder{
        public ADHolder(View itemView) {
            super(itemView);
        }
    }
}
