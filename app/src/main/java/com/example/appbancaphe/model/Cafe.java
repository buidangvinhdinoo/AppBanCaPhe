package com.example.appbancaphe.model;

public class Cafe {
    public String id;
    public String anh; //link
    public String loai; //
    public int kich_co; //1: nhỏ, 2: vừa, 3: lớn
    public double don_gia;
    public int trang_thai; //0: bán hết, 1: đang bán

    public Cafe() {
    }

    public Cafe(String id, String anh, String loai, int kich_co, double don_gia, int trang_thai) {
        this.id = id;
        this.anh = anh;
        this.loai = loai;
        this.kich_co = kich_co;
        this.don_gia = don_gia;
        this.trang_thai = trang_thai;
    }

    public Cafe timKiem(String _id){
        return null; //làm sau
    }
}
