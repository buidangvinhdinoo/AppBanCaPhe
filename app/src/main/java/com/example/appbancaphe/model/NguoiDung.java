package com.example.appbancaphe.model;

import java.sql.Date;
import java.sql.Time;

public class NguoiDung {
    public String _id;
    public String ten_dang_nhap;
    public String email;
    public String mat_khau;
    public String ho_ten;
    public String anh_avatar;
    public int chuc_vu; //vd: 0: khach hang, 1:nhan vien. khong thi doi thanh string cx dc
    public String sdt;
    public Date ngay_sinh;
    public int trang_thai;
    public Date ngay_tao;

    public NguoiDung() {
    }

    public NguoiDung(String _id, String ten_dang_nhap, String email, String mat_khau,
                     String ho_ten, String anh_avatar, int chuc_vu, String sdt, Date ngay_sinh,
                     int trang_thai, Date ngay_tao) {
        this._id = _id;
        this.ten_dang_nhap = ten_dang_nhap;
        this.email = email;
        this.mat_khau = mat_khau;
        this.ho_ten = ho_ten;
        this.anh_avatar = anh_avatar; //link
        this.chuc_vu = chuc_vu;
        this.sdt = sdt;
        this.ngay_sinh = ngay_sinh;
        this.trang_thai = trang_thai;
        this.ngay_tao = ngay_tao;
    }

    static public NguoiDung timKiemEmail(String email) {
        //tim trong database
        return new NguoiDung();
    }
}
