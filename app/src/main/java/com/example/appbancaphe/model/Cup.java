package com.example.appbancaphe.model;

public class Cup {
    public String id;
    public String anh; //link
    public String loai_id; //
    public int kich_co; //1: nhỏ, 2: vừa, 3: lớn
    public double don_gia;
    public int trang_thai; //0: bán hết, 1: đang bán

    public Cup() {
    }

    public Cup(String id, String anh, String loai_id, int kich_co, double don_gia, int trang_thai) {
        this.id = id;
        this.anh = anh;
        this.loai_id = loai_id;
        this.kich_co = kich_co;
        this.don_gia = don_gia;
        this.trang_thai = trang_thai;
    }

    //để public thì ko cần geter với seter
}
