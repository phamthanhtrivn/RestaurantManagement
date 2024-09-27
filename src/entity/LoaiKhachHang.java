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
public class LoaiKhachHang {
    private String maLoaiKH;
    private String tenLoaiKH;
    private String moTa;
    private double giamGia;
    private double giamGiaNS;

    public LoaiKhachHang() {
    }
    
    public LoaiKhachHang(String maLoaiKH) {
        this.maLoaiKH = maLoaiKH;
    }

    public LoaiKhachHang(String maLoaiKH, String tenLoaiKH, String moTa, double giamGia, double giamGiaNS) {
        this.maLoaiKH = maLoaiKH;
        this.tenLoaiKH = tenLoaiKH;
        this.moTa = moTa;
        this.giamGia = giamGia;
        this.giamGiaNS = giamGiaNS;
    }
    
    public String getMaLoaiKH() {
        return maLoaiKH;
    }

    public String getTenLoaiKH() {
        return tenLoaiKH;
    }

    public void setTenLoaiKH(String tenLoaiKH) {
        this.tenLoaiKH = tenLoaiKH;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public double getGiamGia() {
        return giamGia;
    }

    public void setGiamGia(double giamGia) {
        this.giamGia = giamGia;
    }

    public double getGiamGiaNS() {
        return giamGiaNS;
    }

    public void setGiamGiaNS(double giamGiaNS) {
        this.giamGiaNS = giamGiaNS;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.maLoaiKH);
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
        final LoaiKhachHang other = (LoaiKhachHang) obj;
        return Objects.equals(this.maLoaiKH, other.maLoaiKH);
    }

    @Override
    public String toString() {
        return "LoaiKhachHang{" + "maLoaiKH=" + maLoaiKH + ", tenLoaiKH=" + tenLoaiKH + ", moTa=" + moTa + ", giamGia=" + giamGia + ", giamGiaNS=" + giamGiaNS + '}';
    }
    
    
    
    
}
