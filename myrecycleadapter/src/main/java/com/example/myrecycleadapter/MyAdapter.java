package com.example.myrecycleadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    List<Bean> mlist ;
    LayoutInflater inflater;
    private Context mContext;

    public MyAdapter(Context context,List<Bean> mlist) {
        this.mContext = context;
        this.mlist = mlist;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @org.jetbrains.annotations.NotNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull @org.jetbrains.annotations.NotNull ViewGroup parent, int viewType) {
       View view =  inflater.inflate(R.layout.item_list,null);
       MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull @org.jetbrains.annotations.NotNull MyAdapter.MyViewHolder holder, int position) {
         Bean bean = mlist.get(position);
         holder.mimageView.setImageResource(bean.getResId());
         holder.mtitle.setText(bean.getTitle());
         holder.mversion.setText(bean.getVersion());
         holder.msize.setText(bean.getSize());
         holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Toast.makeText(mContext,"dianj",Toast.LENGTH_SHORT).show();
             }
         });
    }

    @Override
    public int getItemCount() {
        return mlist.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mimageView;
        TextView mtitle;
        TextView mversion;
        TextView msize;
        RelativeLayout relativeLayout;
        public MyViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            this.mimageView = itemView.findViewById(R.id.logo);
            this.mtitle = itemView.findViewById(R.id.title);
            this.mversion = itemView.findViewById(R.id.version);
            this.msize = itemView.findViewById(R.id.size);
            this.relativeLayout = itemView.findViewById(R.id.rl);

        }
    }
}
