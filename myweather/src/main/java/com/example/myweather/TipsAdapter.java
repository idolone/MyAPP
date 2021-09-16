package com.example.myweather;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TipsAdapter extends RecyclerView.Adapter<TipsAdapter.MyViewHoder> {
    private Context mContext;
    private List<OtherTipsBean> mTips;

    public TipsAdapter(Context mContext, List<OtherTipsBean> mTips) {
        this.mContext = mContext;
        this.mTips = mTips;
    }

    @NonNull
    @Override
    public MyViewHoder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.tips_item_layout,null);
        TipsAdapter.MyViewHoder myViewHolder = new MyViewHoder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHoder holder, int position) {
        OtherTipsBean otherTips = mTips.get(position);

        holder.tvTiltle.setText(otherTips.getAir_tips());
        holder.tvDesc.setText(otherTips.getDesc());
        holder.tvLevel.setText(otherTips.getLevel());


    }

    @Override
    public int getItemCount() {
        return (mTips == null ? 0 : mTips.size());
    }

    class MyViewHoder extends RecyclerView.ViewHolder{

        TextView tvTiltle,tvDesc,tvLevel;

        public MyViewHoder(@NonNull View itemView) {
            super(itemView);
            tvTiltle = itemView.findViewById(R.id.tv_title);
            tvDesc = itemView.findViewById(R.id.tv_desc);
            tvLevel = itemView.findViewById(R.id.tv_level);
        }
    }
}
