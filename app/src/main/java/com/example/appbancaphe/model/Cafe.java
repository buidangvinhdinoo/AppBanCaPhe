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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAnh() {
        return anh;
    }

    public void setAnh(String anh) {
        this.anh = anh;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public int getKich_co() {
        return kich_co;
    }

    public void setKich_co(int kich_co) {
        this.kich_co = kich_co;
    }

    public double getDon_gia() {
        return don_gia;
    }

    public void setDon_gia(double don_gia) {
        this.don_gia = don_gia;
    }

    public int getTrang_thai() {
        return trang_thai;
    }

    public void setTrang_thai(int trang_thai) {
        this.trang_thai = trang_thai;
    }
}
