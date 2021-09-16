package com.example.mylistviewbaseadapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Map;

public class MyAdapter extends BaseAdapter {

    /**数据集合**/
    List<Map<String,Object>> mlist;
    /**数据集合**/
    LayoutInflater  inflater;

    public MyAdapter(Context context) {
         inflater = LayoutInflater.from(context);
    }

    public void setMlist(List<Map<String, Object>> mlist) {
        this.mlist = mlist;
    }


    @Override
    public int getCount() {
        return mlist.size();
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

//        View view = inflater.inflate(R.layout.item_list,null);
//
//        ImageView logo = (ImageView)view.findViewById(R.id.logo);
//        TextView  title = (TextView)view.findViewById(R.id.title);
//        TextView  version = (TextView)view.findViewById(R.id.version);
//        TextView  size = (TextView)view.findViewById(R.id.size);
//
//        logo.setImageResource((Integer)mlist.get(position).get("logo"));
//        title.setText((String)mlist.get(position).get("title"));
//        version.setText((String)mlist.get(position).get("version"));
//        size.setText((String)mlist.get(position).get("size"));

//        return view;



        ViewHoler viewHoler = new ViewHoler();
        if(convertView == null){

            convertView = inflater.inflate(R.layout.item_list,null);
            ImageView logo = (ImageView)convertView.findViewById(R.id.logo);
            TextView  title = (TextView)convertView.findViewById(R.id.title);
            TextView  version = (TextView)convertView.findViewById(R.id.version);
            TextView  size = (TextView)convertView.findViewById(R.id.size);

            viewHoler.logo = logo;
            viewHoler.title = title;
            viewHoler.version = version;
            viewHoler.size = size;

            convertView.setTag(viewHoler);

        }else{

            viewHoler =(ViewHoler) convertView.getTag();
        }
            Map map = mlist.get(position);
             viewHoler.logo.setImageResource((Integer) map.get("logo"));
             viewHoler.title.setText((String)map.get("title"));
             viewHoler.version.setText((String)map.get("version"));
             viewHoler.size.setText((String)map.get("size"));



        return convertView;
    }

    public class ViewHoler{
        ImageView logo;
        TextView  title;
        TextView  version ;
        TextView  size ;
    }

}
