package com.example.appbancaphe.adapter;

import android.app.Activity;
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

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.ViewHolder> {
    Activity activity;
    List<Cafe> list;

    public HomeAdapter(Activity activity, List<Cafe> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_in_home, parent);
        if (list.isEmpty()) duLieuMau();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.isEmpty()) duLieuMau();
        Cafe item = list.get(position);
        Picasso.get().load(item.anh)
                .placeholder(R.drawable.default_cafe_img)
                .into(holder.img_cafe);
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

    void duLieuMau() {
        list.add(new Cafe("cf01", "", "Cafe Nâm", 1, 30000, 1));
        list.add(new Cafe("cf02", "", "Cafe Đen", 3, 75000, 0));
        list.add(new Cafe("cf03", "", "Cafe muốn", 2, 35000, 1));
        list.add(new Cafe("cf04", "", "Cafe chồn", 1, 50000, 1));
    }
}
