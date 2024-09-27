/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class DonDatBan {
    private String maDonDatBan;
    private String tenKH;
    private String soDienThoai;
    private int soLuongKH;
    private double tienCoc;
    private boolean trangThai;
    private LocalDateTime gioHen;
    private double hoanCoc;
    private LocalDateTime gioHuy;
    private NhanVien nhanVien;
    private LoaiBan loaiBan;

    public DonDatBan() {
    }
    
    public DonDatBan(String maDonDatBan) {
        this.maDonDatBan = maDonDatBan;
    }

    public DonDatBan(String maDonDatBan, String tenKH, String soDienThoai, int soLuongKH, double tienCoc, boolean trangThai, LocalDateTime gioHen, double hoanCoc, LocalDateTime gioHuy, NhanVien nhanVien, LoaiBan loaiBan) {
        this.maDonDatBan = maDonDatBan;
        this.tenKH = tenKH;
        this.soDienThoai = soDienThoai;
        this.soLuongKH = soLuongKH;
        this.tienCoc = tienCoc;
        this.trangThai = trangThai;
        this.gioHen = gioHen;
        this.hoanCoc = hoanCoc;
        this.gioHuy = gioHuy;
        this.nhanVien = nhanVien;
        this.loaiBan = loaiBan;
    }

    public String getMaDonDatBan() {
        return maDonDatBan;
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

    public int getSoLuongKH() {
        return soLuongKH;
    }

    public void setSoLuongKH(int soLuongKH) {
        this.soLuongKH = soLuongKH;
    }

    public double getTienCoc() {
        return tienCoc;
    }

    public void setTienCoc(double tienCoc) {
        this.tienCoc = tienCoc;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public LocalDateTime getGioHen() {
        return gioHen;
    }

    public void setGioHen(LocalDateTime gioHen) {
        this.gioHen = gioHen;
    }

    public double getHoanCoc() {
        return hoanCoc;
    }

    public void setHoanCoc(double hoanCoc) {
        this.hoanCoc = hoanCoc;
    }

    public LocalDateTime getGioHuy() {
        return gioHuy;
    }

    public void setGioHuy(LocalDateTime gioHuy) {
        this.gioHuy = gioHuy;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public LoaiBan getLoaiBan() {
        return loaiBan;
    }

    public void setLoaiBan(LoaiBan loaiBan) {
        this.loaiBan = loaiBan;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + Objects.hashCode(this.maDonDatBan);
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
        final DonDatBan other = (DonDatBan) obj;
        return Objects.equals(this.maDonDatBan, other.maDonDatBan);
    }

    @Override
    public String toString() {
        return "DonDatBan{" + "maDonDatBan=" + maDonDatBan + ", tenKH=" + tenKH + ", soDienThoai=" + soDienThoai + ", soLuongKH=" + soLuongKH + ", tienCoc=" + tienCoc + ", trangThai=" + trangThai + ", gioHen=" + gioHen + ", hoanCoc=" + hoanCoc + ", gioHuy=" + gioHuy + ", nhanVien=" + nhanVien + ", loaiBan=" + loaiBan + '}';
    }
    
    
}
