package com.example.appbancaphe.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbancaphe.R;
import com.example.appbancaphe.model.Cafe;
import com.squareup.picasso.Picasso;

import java.util.List;

public class TrangChuAdapter extends RecyclerView.Adapter<TrangChuAdapter.ViewHolder> {
    Context context;
    List<Cafe> list;

    public TrangChuAdapter(Context context, List<Cafe> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = ((Activity)context).getLayoutInflater();
        View view = inflater.inflate(R.layout.item_cafe, parent);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Cafe item = list.get(position);
        if(item.anh != null && !item.anh.isEmpty())
                Picasso.get().load(item.anh).into(holder.img_cafe);
        holder.ten_loai.setText(item.loai);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_cafe;
        TextView ten_loai;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_cafe = itemView.findViewById(R.id.img_cafe);
            ten_loai = itemView.findViewById(R.id.ten_loai);
        }
    }
}
