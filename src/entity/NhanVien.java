/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.util.Date;
import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class NhanVien {
    private String maNV;
    private String tenNV;
    private String CCCD;
    private String soDienThoai;
    private String tenTaiKhoan;
    private String matKhau;
    private boolean trangThai;
    private LoaiNhanVien loaiNhanVien;
    private Date ngaySinh;

    public NhanVien() {
    }
    
    public NhanVien(String maNV) {
        this.maNV = maNV;
    }

    public NhanVien(String maNV, String tenNV, String CCCD, String soDienThoai, String tenTaiKhoan, String matKhau, boolean trangThai, LoaiNhanVien loaiNhanVien, Date ngaySinh) {
        this.maNV = maNV;
        this.tenNV = tenNV;
        this.CCCD = CCCD;
        this.soDienThoai = soDienThoai;
        this.tenTaiKhoan = tenTaiKhoan;
        this.matKhau = matKhau;
        this.trangThai = trangThai;
        this.loaiNhanVien = loaiNhanVien;
        this.ngaySinh = ngaySinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTenNV() {
        return tenNV;
    }

    public void setTenNV(String tenNV) {
        this.tenNV = tenNV;
    }

    public String getCCCD() {
        return CCCD;
    }

    public void setCCCD(String CCCD) {
        this.CCCD = CCCD;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getTenTaiKhoan() {
        return tenTaiKhoan;
    }

    public void setTenTaiKhoan(String tenTaiKhoan) {
        this.tenTaiKhoan = tenTaiKhoan;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiNhanVien getLoaiNhanVien() {
        return loaiNhanVien;
    }

    public void setLoaiNhanVien(LoaiNhanVien loaiNhanVien) {
        this.loaiNhanVien = loaiNhanVien;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.maNV);
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
        final NhanVien other = (NhanVien) obj;
        return Objects.equals(this.maNV, other.maNV);
    }

    @Override
    public String toString() {
        return "NhanVien{" + "maNV=" + maNV + ", tenNV=" + tenNV + ", CCCD=" + CCCD + ", soDienThoai=" + soDienThoai + ", tenTaiKhoan=" + tenTaiKhoan + ", matKhau=" + matKhau + ", trangThai=" + trangThai + ", loaiNhanVien=" + loaiNhanVien + ", ngaySinh=" + ngaySinh + '}';
    }

    
    
}
