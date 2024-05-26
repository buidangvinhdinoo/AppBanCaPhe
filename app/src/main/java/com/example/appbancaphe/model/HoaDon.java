package com.example.appbancaphe.model;

import java.sql.Date;

public class HoaDon {
    public String id;
    public String gio_hang_id;
    public String nhan_vien_id; //người tạo hóa đơn
    public int trang_thai; //0: chưa thanh toán, 1: đã thanh toán nhưng chưa giao, 2: khách đã nhận
    public Date ngay_tao;

    //làm sau
}
