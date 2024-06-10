package com.example.appbancaphe.Adapter_Package;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.appbancaphe.DAOModel.DAOLuuHD;
import com.example.appbancaphe.Model.LuuHoaDon;
import com.example.appbancaphe.R;

import java.util.ArrayList;

public class AdapterTKDT extends RecyclerView.Adapter<AdapterTKDT.ViewHolder>{

    ArrayList<LuuHoaDon> listHoaDon;
    Context context;
    DAOLuuHD daoLuuHD;

    public AdapterTKDT(Context context, ArrayList<LuuHoaDon> listHoaDon){
        this.context = context;
        this.listHoaDon = listHoaDon;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_thongkedoanhthu, parent, false);
        daoLuuHD = new DAOLuuHD(view.getContext());
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        LuuHoaDon hoaDon = listHoaDon.get(position);
        holder.txtTkdtTenSP.setText(hoaDon.getTenKhachHang());
        double doanhThu = hoaDon.getThanhTien();
        String outDoanhThu = String.format("%,.0f", doanhThu);
        String subDoanhThu = outDoanhThu.substring(0, (outDoanhThu.length() - 4));
        holder.txtTkdtThanhTien.setText(subDoanhThu + "K VNĐ");

        holder.itemView.setOnClickListener(v -> {
//                Tạo dialog
            Dialog dialog = new Dialog(v.getContext());
            dialog.setContentView(R.layout.dialog_thanh_toan);
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

//                    Ánh xạ
            TextView txtHoaDonTitle = dialog.findViewById(R.id.txtHoaDonTitle);
            EditText btnHoaDonHuy = dialog.findViewById(R.id.btnHoaDonHuy);
            EditText btnHoaDonXN = dialog.findViewById(R.id.btnHoaDonXN);

            TextView txtHDTenNV = dialog.findViewById(R.id.txtHDTenNV);
            TextView txtHDTenKH = dialog.findViewById(R.id.txtHDTenKH);
            TextView txtHDNgayBan = dialog.findViewById(R.id.txtHDNgayBan);
            RecyclerView recycle_hoaDon = dialog.findViewById(R.id.recycle_hoaDon);
            TextView txtHDTongTien = dialog.findViewById(R.id.txtHDTongTien);

            ArrayList<LuuHoaDon> listHoaDon2 = daoLuuHD.getHDofMaHD(hoaDon.getMaHoaDon());

            txtHoaDonTitle.setText("Chi tiết hóa đơn");
            txtHDTenNV.setText(listHoaDon2.get(0).getTenUser());
            txtHDTenKH.setText(listHoaDon2.get(0).getTenKhachHang());
            txtHDNgayBan.setText(listHoaDon2.get(0).getNgayLapHD());

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(dialog.getContext());
            recycle_hoaDon.setLayoutManager(linearLayoutManager);
            AdapterThongTinHD adapterThongTinHD = new AdapterThongTinHD(dialog.getContext(), listHoaDon2);
            recycle_hoaDon.setAdapter(adapterThongTinHD);

            double doanhThu1 = daoLuuHD.tongThuHD(hoaDon.getMaHoaDon());
            String outDoanhThu1 = String.format("%,.0f", doanhThu1);
            txtHDTongTien.setText(outDoanhThu1 + " VNĐ");


            btnHoaDonXN.setVisibility(View.GONE);
            btnHoaDonHuy.setText("Đóng");
            btnHoaDonHuy.setOnClickListener(v1 -> dialog.dismiss());

            dialog.show();
        });
    }

    @Override
    public int getItemCount() {
        return listHoaDon.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtTkdtTenSP, txtTkdtThanhTien;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTkdtTenSP = itemView.findViewById(R.id.txtTkdtTenSP);
            txtTkdtThanhTien = itemView.findViewById(R.id.txtTkdtThanhTien);
        }
    }
}
