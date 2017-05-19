package com.lizhi.microlive.adpter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.lizhi.microlive.R;
import com.lizhi.microlive.model.FunctionItem;

import java.util.List;

/**
 * Created by Administrator on 2017/5/13.
 */

public class FunctionAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<FunctionItem> functionItems;

    private OnFuntionItemClickListener onFunctionItemClickListener;

    public FunctionAdapter(Context mContext, List<FunctionItem> functionItems) {
        this.mContext = mContext;
        this.functionItems = functionItems;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.function_item_layout, parent, false);
        FunctionViewHolder functionViewHolder = new FunctionViewHolder(view);
        return functionViewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        FunctionItem item = functionItems.get(position);
        FunctionViewHolder functionViewHolder = (FunctionViewHolder) holder;
        functionViewHolder.titleTextView.setText(item.getTitle());
        functionViewHolder.subTitleTextView.setText(item.getSubtitle());

        int width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200, mContext.getResources().getDisplayMetrics());
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 200f, mContext.getResources().getDisplayMetrics());

        Glide.with(mContext)
                .load(item.getImgUrl())
//                .placeholder(R.mipmap.ic_launcher) //占位图
//                .error(R.mipmap.ic_launcher)  //出错的占位图
//                .override(width, height) //图片显示的分辨率 ，像素值 可以转化为DP再设置
//                .animate(R.anim.glide_anim)
                .centerCrop()
                .fitCenter()
                .into(functionViewHolder.imageView);
    }

    @Override
    public int getItemCount() {
        return functionItems.size();
    }

    // 设置点击某个 item 的监听
    public interface OnFuntionItemClickListener{
        void onItemClick(FunctionItem data);
    }

    /**
     * 设置某条的监听
     * @param onFunctionItemClickListener
     */
    public void setOnFunctionItemClickListener(OnFuntionItemClickListener onFunctionItemClickListener) {
        this.onFunctionItemClickListener = onFunctionItemClickListener;
    }

    class FunctionViewHolder extends RecyclerView.ViewHolder {

        LinearLayout containerView;
        ImageView imageView;
        TextView titleTextView;
        TextView subTitleTextView;

        public FunctionViewHolder(View itemView) {
            super(itemView);
            containerView = (LinearLayout)itemView.findViewById( R.id.containerView );
            imageView = (ImageView)itemView.findViewById( R.id.imageView );
            titleTextView = (TextView)itemView.findViewById( R.id.titleTextView );
            subTitleTextView = (TextView)itemView.findViewById( R.id.subTitleTextView );

            containerView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(null!=onFunctionItemClickListener){
                        onFunctionItemClickListener.onItemClick(functionItems.get(getLayoutPosition()));
                    }
                }
            });
        }
    }
}
