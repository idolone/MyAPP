package com.example.mynote;


import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mynote.utils.ToastUtil;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    private List<Note> mList;
    private Context mContext;
    LayoutInflater mLayoutInflater;
    private NoteDbOpenHelper noteDbOpenHelper;

    private int viewType;

    public static int TYPE_LINEAR_LAYOUT = 0;
    public static int TYPE_GRID_LAYOUT = 1;


    public MyAdapter( Context mContext,List<Note> mList) {
        this.mList = mList;
        this.mContext = mContext;
        mLayoutInflater = LayoutInflater.from(mContext);
        noteDbOpenHelper = new NoteDbOpenHelper(mContext);
    }

    public int getViewType() {
        return viewType;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    @Override
    public int getItemViewType(int position) {
        return viewType;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if(viewType == TYPE_LINEAR_LAYOUT)
            view = mLayoutInflater.inflate(R.layout.list_item_layout,null);
        else
            view = mLayoutInflater.inflate(R.layout.list_item_gridlayout,null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Note note = mList.get(position);
        holder.mTvTitle.setText(note.getTitle());
        holder.mTvContent.setText(note.getContent());
        holder.mTvTime.setText(note.getCreatedTime());
        holder.rlContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(mContext,EditActivity.class);

                intent.putExtra("note",note);
                mContext.startActivity(intent);

            }
        });


        holder.rlContainer.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                Dialog dialog = new Dialog(mContext, android.R.style.ThemeOverlay_Material_Dialog_Alert);
                View view = mLayoutInflater.inflate(R.layout.list_item_dialog_layout,null);

                TextView tvDelete = view.findViewById(R.id.tv_delete);
                TextView tvEdit = view.findViewById(R.id.tv_edit);

                tvDelete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int row = noteDbOpenHelper.deleteFromDBByID(note.getId());

                        if(row > 0){
                           // removeData(holder.getPosition());
                            removeData(holder.getAdapterPosition());
                            ToastUtil.toastShort(mContext,"删除成功");
                        }else{
                            ToastUtil.toastShort(mContext,"删除失败");
                        }
                        dialog.dismiss();
                    }

                });

                tvEdit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext,EditActivity.class);

                        intent.putExtra("note",note);
                        mContext.startActivity(intent);
                        dialog.dismiss();
                    }
                });



                dialog.setContentView(view);
                dialog.show();



                return false;
            }
        });



    }

    public void refreshData(List<Note> mNotes) {
        mList = mNotes;
        notifyDataSetChanged();
    }

    public void removeData(int pos){
        mList.remove(pos);
        notifyItemRemoved(pos);
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView mTvTitle;
        TextView mTvContent;
        TextView mTvTime;
        ViewGroup rlContainer;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvTitle = itemView.findViewById(R.id.tv_title);
            this.mTvContent = itemView.findViewById(R.id.tv_content);
            this.mTvTime = itemView.findViewById(R.id.tv_time);
            this.rlContainer = itemView.findViewById(R.id.rl_item_container);

        }
    }
}
