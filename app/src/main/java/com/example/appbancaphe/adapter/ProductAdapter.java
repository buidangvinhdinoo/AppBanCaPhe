package com.example.appbancaphe.adapter;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbancaphe.model.Cafe;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Activity activity;
    List<Cafe> list;

    public ProductAdapter(Activity activity, List<Cafe> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    void duLieuMau() {
        list.add(new Cafe("cf01", "", "Cafe Nâm", 1, 30000, 1));
        list.add(new Cafe("cf02", "", "Cafe Đen", 3, 75000, 0));
        list.add(new Cafe("cf03", "", "Cafe muốn", 2, 35000, 1));
        list.add(new Cafe("cf04", "", "Cafe chồn", 1, 50000, 1));
    }
}
