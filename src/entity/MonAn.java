/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class MonAn {
    private String maMon;
    private String tenMon;
    private String hinhAnh;
    private String moTa;
    private double gia;
    private boolean trangThai;
    private LoaiMonAn loaiMonAn;
    private KhuyenMai khuyenMai;

    public MonAn() {
    }
    
    public MonAn(String maMon) {
        this.maMon = maMon;
    }

    public MonAn(String maMon, String tenMon, String hinhAnh, String moTa, double gia, boolean trangThai, LoaiMonAn loaiMonAn, KhuyenMai khuyenMai) {
        this.maMon = maMon;
        this.tenMon = tenMon;
        this.hinhAnh = hinhAnh;
        this.moTa = moTa;
        this.gia = gia;
        this.trangThai = trangThai;
        this.loaiMonAn = loaiMonAn;
        this.khuyenMai = khuyenMai;
    }

    public String getMaMon() {
        return maMon;
    }


    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGia() {
        return gia;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiMonAn getLoaiMonAn() {
        return loaiMonAn;
    }

    public void setLoaiMonAn(LoaiMonAn loaiMonAn) {
        this.loaiMonAn = loaiMonAn;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 41 * hash + Objects.hashCode(this.maMon);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final MonAn other = (MonAn) obj;
        return Objects.equals(this.maMon, other.maMon);
    }

    @Override
    public String toString() {
        return "MonAn{" + "maMon=" + maMon + ", tenMon=" + tenMon + ", hinhAnh=" + hinhAnh + ", moTa=" + moTa + ", gia=" + gia + ", trangThai=" + trangThai + ", loaiMonAn=" + loaiMonAn + ", khuyenMai=" + khuyenMai + '}';
    }

    
    
    
}
