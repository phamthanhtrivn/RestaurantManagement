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
public class DatBan_Mon {
    private MonAn monAn;
    private DonDatBan donDatBan;
    private double thanhTien;
    private int soLuong;

    public DatBan_Mon() {
    }
    
    public DatBan_Mon(MonAn monAn, DonDatBan donDatBan) {
        this.monAn = monAn;
        this.donDatBan = donDatBan;
    }

    public DatBan_Mon(MonAn monAn, DonDatBan donDatBan, double thanhTien, int soLuong) {
        this.monAn = monAn;
        this.donDatBan = donDatBan;
        this.thanhTien = thanhTien;
        this.soLuong = soLuong;
    }

    public MonAn getMonAn() {
        return monAn;
    }

    public DonDatBan getDonDatBan() {
        return donDatBan;
    }

    public double getThanhTien() {
        return thanhTien;
    }

    public void setThanhTien(double thanhTien) {
        this.thanhTien = thanhTien;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.monAn);
        hash = 23 * hash + Objects.hashCode(this.donDatBan);
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
        final DatBan_Mon other = (DatBan_Mon) obj;
        if (!Objects.equals(this.monAn, other.monAn)) {
            return false;
        }
        return Objects.equals(this.donDatBan, other.donDatBan);
    }

    

    @Override
    public String toString() {
        return "DatBan_Mon{" + "monAn=" + monAn + ", donDatBan=" + donDatBan + ", thanhTien=" + thanhTien + ", soLuong=" + soLuong + '}';
    }
    
    
}
