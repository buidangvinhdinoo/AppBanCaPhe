package com.example.appbancaphe.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.appbancaphe.R;
import com.example.appbancaphe.model.Cafe;

import java.util.List;

public class TrangChuAdapter extends BaseAdapter {
    Context context;
    List<Cafe> list;

    public TrangChuAdapter(Context context, List<Cafe> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Cafe getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.item_cafe, parent);
            holder = new ViewHolder(convertView);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return null;
    }

    static class ViewHolder{
        ImageView img_cafe;
        TextView ten_loai;

        public ViewHolder(View view) {
            img_cafe = view.findViewById(R.id.img_cafe);
            ten_loai = view.findViewById(R.id.ten_loai);
        }
    }
}
