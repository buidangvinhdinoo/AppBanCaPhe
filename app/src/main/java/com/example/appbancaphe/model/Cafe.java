package com.example.appbancaphe.model;

public class Cafe {
    public String _id;
    public String anh; //link
    public int loai;
    public int kich_co;
    public double don_gia;
    public int trang_thai; //ban het, dang ban

    public Cafe() {
    }

    public Cafe(String _id, String anh, int loai, int kich_co, double don_gia, int trang_thai) {
        this.anh = anh;
        this._id = _id;
        this.loai = loai;
        this.kich_co = kich_co;
        this.don_gia = don_gia;
        this.trang_thai = trang_thai;
    }

    public Cafe timKiem(String _id){
        return null; //
    }
}
