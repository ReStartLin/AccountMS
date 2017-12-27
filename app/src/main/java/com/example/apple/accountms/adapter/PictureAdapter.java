package com.example.apple.accountms.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.apple.accountms.R;
import com.example.apple.accountms.model.Picture;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apple on 2017/12/27.
 */

public class PictureAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private ViewHolder holder;
    private List<Picture> pictures;
    public PictureAdapter(String[] titles,int[] images,Context context){
        super();
        pictures = new ArrayList<>();
        inflater = LayoutInflater.from(context);
        for (int i=0;i<images.length;i++){
            Picture picture = new Picture(titles[i],images[i]);
            pictures.add(picture);
        }
    }
    @Override
    public int getCount() {
        if (null != pictures){
            return pictures.size();
        }
        return 0;
    }

    @Override
    public Object getItem(int i) {
        return pictures.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (view == null){
            view = inflater.inflate(R.layout.gvitem,null);
            holder = new ViewHolder();
            holder.title = view.findViewById(R.id.itemTitle);
            holder.image = view.findViewById(R.id.itemImage);
            view.setTag(holder);
        }else{
            holder = (ViewHolder) view.getTag();

        }
        holder.title.setText(pictures.get(i).getTitle());
        holder.image.setImageResource(pictures.get(i).getImageId());
        return view;
    }
    class ViewHolder{
        public TextView title;
        public ImageView image;
    }
}
