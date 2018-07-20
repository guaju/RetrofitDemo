package com.guaju.rxjava_retrofit.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.guaju.rxjava_retrofit.R;
import com.guaju.rxjava_retrofit.api.FarmerApi;
import com.guaju.rxjava_retrofit.bean.Goods;

import java.util.ArrayList;

/**
 * Created by guaju on 2018/7/20.
 */

public class GoodsListAdapter extends RecyclerView.Adapter<GoodsListAdapter.GoodsViewHolder> {
     private ArrayList<Goods> goods;
     private Context context;

    public GoodsListAdapter(Context context,ArrayList<Goods> goods) {
        this.goods = goods;
        this.context = context;
    }


    @Override
    public GoodsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview = View.inflate(context, R.layout.item, null);
        return new GoodsViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(GoodsViewHolder holder, int position) {
        Goods goods = this.goods.get(position);
        holder.setContent(goods);
    }

    @Override
    public int getItemCount() {
        return goods.size();
    }

    public  class GoodsViewHolder extends RecyclerView.ViewHolder{

        private final TextView tv_title;
        private final TextView tv_price;
        private final ImageView iv;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            tv_title = (TextView)(itemView).findViewById(R.id.tv_title);
            tv_price = (TextView)(itemView).findViewById(R.id.tv_price);
            iv = (ImageView) (itemView).findViewById(R.id.iv);
        }

        public void  setContent(Goods goods){
            tv_title.setText(goods.getName());
            tv_price.setText(goods.getPresell_price());
            Glide.with(context).load(FarmerApi.BASE_URL+goods.getImg()).into(iv);
        }


    }

}
