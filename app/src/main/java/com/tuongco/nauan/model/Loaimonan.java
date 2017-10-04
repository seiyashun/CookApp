package com.tuongco.nauan.model;

/**
 * Created by Administrator on 03/10/2017.
 */

public class Loaimonan {
    public String maloai;
    public String tenloai;
    public String hinhanh;

    public Loaimonan(String maloai, String tenloai, String hinhanh) {
        this.maloai = maloai;
        this.tenloai = tenloai;
        this.hinhanh = hinhanh;
    }

    public String getMaloai() {
        return maloai;
    }

    public void setMaloai(String maloai) {
        this.maloai = maloai;
    }

    public String getTenloai() {
        return tenloai;
    }

    public void setTenloai(String tenloai) {
        this.tenloai = tenloai;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

}
