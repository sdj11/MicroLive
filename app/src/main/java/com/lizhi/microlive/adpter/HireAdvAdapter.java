package com.lizhi.microlive.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lizhi.microlive.R;
import com.lizhi.microlive.model.HireAdv;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class HireAdvAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<HireAdv> HireAdvItems;

    private OnHireAdvItemClickListener onHireAdvItemClickListener;

    public HireAdvAdapter(Context mContext, List<HireAdv> HireAdvItems) {
        this.mContext = mContext;
        this.HireAdvItems = HireAdvItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.great_goods_item_layout, parent, false);
        HireAdvViewHolder goodsViewHolder = new HireAdvViewHolder(view);
        return goodsViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        HireAdv item = HireAdvItems.get(position);

        HireAdvViewHolder goodsViewHolder = (HireAdvViewHolder) holder;

//        HireAdvViewHolder.firmNameView.setText(item);
//        HireAdvViewHolder.dateView.setText(item);
//        HireAdvViewHolder.postView.setText(item);

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

//        Glide.with(mContext)
//                .load(item.getImageUrl())
////                .placeholder(R.mipmap.ic_launcher) //占位图
////                .error(R.mipmap.ic_launcher)  //出错的占位图
////                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
////                .animate(R.anim.glide_anim)
//                .centerCrop()
//                .fitCenter()
//                .into(HireAdvViewHolder.advImgView);
    }

    @Override
    public int getItemCount() {
        return HireAdvItems.size();
    }

    // 设置点击某个 item 的监听
    public interface OnHireAdvItemClickListener{
        void onItemClick(HireAdv data);
    }

    /**
     * 设置某条的监听
     * @param onHireAdvItemClickListener
     */
    public void setOnHireAdvItemClickListener(OnHireAdvItemClickListener onHireAdvItemClickListener) {
        this.onHireAdvItemClickListener = onHireAdvItemClickListener;
    }

    class HireAdvViewHolder extends RecyclerView.ViewHolder {

        FrameLayout containerView;
        ImageView advImgView;
        TextView firmNameView;
        TextView dateView;
        TextView postView;

        public HireAdvViewHolder(View itemView) {
            super(itemView);
            containerView = (FrameLayout)itemView.findViewById(R.id.containerView);
            advImgView = (ImageView)itemView.findViewById( R.id.advImgView );
            firmNameView = (TextView)itemView.findViewById( R.id.firmNameView );
            dateView = (TextView)itemView.findViewById( R.id.dateView );
            postView = (TextView)itemView.findViewById( R.id.postView );

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null!=onHireAdvItemClickListener){
                        onHireAdvItemClickListener.onItemClick(HireAdvItems.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}