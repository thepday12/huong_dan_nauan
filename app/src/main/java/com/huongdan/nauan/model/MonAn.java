package com.huongdan.nauan.model;

public class MonAn {
//     (id INteger PRIMARY KEY AUTOINCREMENT, ten varchar, nguyen_lieu varchar,che_bien varchar, thoi_gian int,loai varchar, hinh_anh varchar, video varchar);

    private int id;
    private String ten;
    private String nguyenLieu;
    private String cheBien;
    private int thoiGian;
    private String loai;
    private String hinhAnh;
    private String video;
    private int yeuThich;

    public MonAn(int id, String ten, String nguyenLieu, String cheBien, int thoiGian, String loai, String hinhAnh) {
        this.id = id;
        this.ten = ten;
        this.nguyenLieu = nguyenLieu;
        this.cheBien = cheBien;
        this.thoiGian = thoiGian;
        this.loai = loai;
        this.hinhAnh = hinhAnh;
    }

    public MonAn(int id, String ten, String nguyenLieu, String cheBien, int thoiGian, String loai, String hinhAnh, String video, int yeuThich) {
        this.id = id;
        this.ten = ten;
        this.nguyenLieu = nguyenLieu;
        this.cheBien = cheBien;
        this.thoiGian = thoiGian;
        this.loai = loai;
        this.hinhAnh = hinhAnh;
        this.video = video;
        this.yeuThich = yeuThich;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getNguyenLieu() {
        return nguyenLieu;
    }

    public void setNguyenLieu(String nguyenLieu) {
        this.nguyenLieu = nguyenLieu;
    }

    public String getCheBien() {
        return cheBien;
    }

    public void setCheBien(String cheBien) {
        this.cheBien = cheBien;
    }

    public int getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(int thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getLoai() {
        return loai;
    }

    public void setLoai(String loai) {
        this.loai = loai;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public int getYeuThich() {
        return yeuThich;
    }

    public void setYeuThich(int yeuThich) {
        this.yeuThich = yeuThich;
    }

    @Override
    public int hashCode() {
//        return super.hashCode();
        return id;
    }

    @Override
    public boolean equals(Object obj) {
        MonAn otherObject = (MonAn) obj;
        return id==otherObject.getId();
    }
}
