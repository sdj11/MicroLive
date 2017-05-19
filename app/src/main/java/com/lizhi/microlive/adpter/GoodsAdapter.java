package com.lizhi.microlive.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lizhi.microlive.R;
import com.lizhi.microlive.model.Goods;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class GoodsAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<Goods> goodsItems;

    private OnGoodsItemClickListener onGoodsItemClickListener;

    public GoodsAdapter(Context mContext, List<Goods> goodsItems) {
        this.mContext = mContext;
        this.goodsItems = goodsItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.great_goods_item_layout, parent, false);
        GoodsAdapter.GoodsViewHolder goodsViewHolder = new GoodsAdapter.GoodsViewHolder(view);
        return goodsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        Goods item = goodsItems.get(position);

        GoodsViewHolder goodsViewHolder = (GoodsViewHolder) holder;
        goodsViewHolder.brandView.setText(item.getBrand());
        goodsViewHolder.amountView.setText(item.getAmount());
        goodsViewHolder.goodsTitleView.setText(item.getTitle());
        goodsViewHolder.typeView.setText(item.getType());

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

        Glide.with(mContext)
                .load(item.getImageUrl())
//                .placeholder(R.mipmap.ic_launcher) //占位图
//                .error(R.mipmap.ic_launcher)  //出错的占位图
//                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
//                .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
                .into(goodsViewHolder.imgView);
    }

    @Override
    public int getItemCount() {
        return goodsItems.size();
    }

    // 设置点击某个 item 的监听
    public interface OnGoodsItemClickListener{
        void onItemClick(Goods data);
    }

    /**
     * 设置某条的监听
     * @param onGoodsItemClickListener
     */
    public void setOnGoodsItemClickListener(OnGoodsItemClickListener onGoodsItemClickListener) {
        this.onGoodsItemClickListener = onGoodsItemClickListener;
    }

    class GoodsViewHolder extends RecyclerView.ViewHolder {

        RelativeLayout containerView;
        TextView brandView;
        TextView goodsTitleView;
        TextView typeView;
        TextView amountView;
        ImageView imgView;

        public GoodsViewHolder(View itemView) {
            super(itemView);
            containerView = (RelativeLayout)itemView.findViewById( R.id.containerView );
            brandView = (TextView)itemView.findViewById( R.id.brandView );
            goodsTitleView = (TextView)itemView.findViewById( R.id.goodsTitleView );
            typeView = (TextView)itemView.findViewById( R.id.typeView );
            amountView = (TextView)itemView.findViewById( R.id.amountView );
            imgView = (ImageView)itemView.findViewById( R.id.imgView );

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null!=onGoodsItemClickListener){
                        onGoodsItemClickListener.onItemClick(goodsItems.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}