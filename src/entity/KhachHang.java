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
public class KhachHang {
    private String maKH;
    private String tenKH;
    private String soDienThoai;
    private String email;
    private Date ngaySinh;
    private Date ngayTao;
    private int diemTL;
    private boolean trangThai;
    private LoaiKhachHang loaiKhachHang;

    public KhachHang() {
    }
    
    public KhachHang(String maKH) {
        this.maKH = maKH;
    }

    public KhachHang(String maKH, String tenKH, String soDienThoai, String email, Date ngaySinh, Date ngayTao, int diemTL, boolean trangThai, LoaiKhachHang loaiKhachHang) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soDienThoai = soDienThoai;
        this.email = email;
        this.ngaySinh = ngaySinh;
        this.ngayTao = ngayTao;
        this.diemTL = diemTL;
        this.trangThai = trangThai;
        this.loaiKhachHang = loaiKhachHang;
    }

    
    
    public String getMaKH() {
        return maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public int getDiemTL() {
        return diemTL;
    }

    public void setDiemTL(int diemTL) {
        this.diemTL = diemTL;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LoaiKhachHang getLoaiKhachHang() {
        return loaiKhachHang;
    }

    public void setLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        this.loaiKhachHang = loaiKhachHang;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.maKH);
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
        final KhachHang other = (KhachHang) obj;
        return Objects.equals(this.maKH, other.maKH);
    }

    @Override
    public String toString() {
        return "KhachHang{" + "maKH=" + maKH + ", tenKH=" + tenKH + ", soDienThoai=" + soDienThoai + ", email=" + email + ", ngaySinh=" + ngaySinh + ", ngayTao=" + ngayTao + ", diemTL=" + diemTL + ", trangThai=" + trangThai + ", loaiKhachHang=" + loaiKhachHang + '}';
    }

    
    
    
}
