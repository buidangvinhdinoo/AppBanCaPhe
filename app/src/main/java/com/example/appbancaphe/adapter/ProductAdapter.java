package com.example.appbancaphe.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbancaphe.R;
import com.example.appbancaphe.model.Cup;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    Activity activity;
    List<Cup> list;

    public ProductAdapter(Activity activity, List<Cup> list) {
        this.activity = activity;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.item_in_product, parent, false); // Sửa tại đây: truyền false cho attachToRoot
        if (list.isEmpty()) duLieuMau();
        return new ProductAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (list.isEmpty()) duLieuMau();
        Cup item = list.get(position);

        if (item.anh != null && !item.anh.isEmpty()) {
            Picasso.get().load(item.anh)
                    .placeholder(R.drawable.default_cafe_img)
                    .into(holder.img_anh);
        } else {
            holder.img_anh.setImageResource(R.drawable.default_cafe_img); // Sử dụng ảnh mặc định khi đường dẫn ảnh trống hoặc null
        }

        holder.txttenloai.setText(item.loai_id);
        holder.txtgiatien.setText(String.valueOf(item.don_gia));

        holder.ibthemvaogio.setOnClickListener(v -> {});//
        holder.ibxemchitiet.setOnClickListener(v -> {});//
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img_anh;
        TextView txttenloai,
                txtgiatien;
        ImageButton ibthemvaogio,
                ibxemchitiet;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img_anh = itemView.findViewById(R.id.iv_img);
            txttenloai = itemView.findViewById(R.id.tv_type);
            txtgiatien = itemView.findViewById(R.id.tv_price);
            ibthemvaogio = itemView.findViewById(R.id.ib_add);
            ibxemchitiet = itemView.findViewById(R.id.ib_detail);
        }
    }

    void duLieuMau() {
        list.add(new Cup("cf01", "", "Cup Nâm", 1, 30000, 1));
        list.add(new Cup("cf02", "", "Cup Đen", 3, 75000, 0));
        list.add(new Cup("cf03", "", "Cup muốn", 2, 35000, 1));
        list.add(new Cup("cf04", "", "Cup chồn", 1, 50000, 1));
    }
}
