/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author THANHTRI
 */
public class HoaDon {
    private String maHD;
    private Date ngayLap;
    private double tongTien;
    private double tongTienTT;
    private boolean trangThai;
    private DichVu dichVu;
    private NhanVien nhanVien;
    private DonDatBan donDatBan;
    private Ban ban;
    private KhuyenMai khuyenMai;
    private LocalDateTime gioVao;
    private LocalDateTime gioRa;

    public HoaDon() {
    }
    
    public HoaDon(String maHD) {
        this.maHD = maHD;
    }

    public HoaDon(String maHD, Date ngayLap, double tongTien, double tongTienTT, boolean trangThai, DichVu dichVu, NhanVien nhanVien, DonDatBan donDatBan, Ban ban, KhuyenMai khuyenMai, LocalDateTime gioVao, LocalDateTime gioRa) {
        this.maHD = maHD;
        this.ngayLap = ngayLap;
        this.tongTien = tongTien;
        this.tongTienTT = tongTienTT;
        this.trangThai = trangThai;
        this.dichVu = dichVu;
        this.nhanVien = nhanVien;
        this.donDatBan = donDatBan;
        this.ban = ban;
        this.khuyenMai = khuyenMai;
        this.gioVao = gioVao;
        this.gioRa = gioRa;
    }

    public String getMaHD() {
        return maHD;
    }

    public Date getNgayLap() {
        return ngayLap;
    }

    public void setNgayLap(Date ngayLap) {
        this.ngayLap = ngayLap;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public double getTongTienTT() {
        return tongTienTT;
    }

    public void setTongTienTT(double tongTienTT) {
        this.tongTienTT = tongTienTT;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    public DichVu getDichVu() {
        return dichVu;
    }

    public void setDichVu(DichVu dichVu) {
        this.dichVu = dichVu;
    }

    public NhanVien getNhanVien() {
        return nhanVien;
    }

    public void setNhanVien(NhanVien nhanVien) {
        this.nhanVien = nhanVien;
    }

    public DonDatBan getDonDatBan() {
        return donDatBan;
    }

    public void setDonDatBan(DonDatBan donDatBan) {
        this.donDatBan = donDatBan;
    }

    public Ban getBan() {
        return ban;
    }

    public void setBan(Ban ban) {
        this.ban = ban;
    }

    public KhuyenMai getKhuyenMai() {
        return khuyenMai;
    }

    public void setKhuyenMai(KhuyenMai khuyenMai) {
        this.khuyenMai = khuyenMai;
    }

    public LocalDateTime getGioVao() {
        return gioVao;
    }

    public void setGioVao(LocalDateTime gioVao) {
        this.gioVao = gioVao;
    }

    public LocalDateTime getGioRa() {
        return gioRa;
    }

    public void setGioRa(LocalDateTime gioRa) {
        this.gioRa = gioRa;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.maHD);
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
        final HoaDon other = (HoaDon) obj;
        return Objects.equals(this.maHD, other.maHD);
    }

    @Override
    public String toString() {
        return "HoaDon{" + "maHD=" + maHD + ", ngayLap=" + ngayLap + ", tongTien=" + tongTien + ", tongTienTT=" + tongTienTT + ", trangThai=" + trangThai + ", dichVu=" + dichVu + ", nhanVien=" + nhanVien + ", donDatBan=" + donDatBan + ", ban=" + ban + ", khuyenMai=" + khuyenMai + ", gioVao=" + gioVao + ", gioRa=" + gioRa + '}';
    }

    
    
    
    
}
