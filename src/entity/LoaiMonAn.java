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
public class LoaiMonAn {
    private String maLoaiMon;
    private String tenLoaiMon;
    private String moTa;

    public LoaiMonAn() {
    }
    
    public LoaiMonAn(String maLoaiMon) {
        this.maLoaiMon = maLoaiMon;
    }

    public LoaiMonAn(String maLoaiMon, String tenLoaiMon, String moTa) {
        this.maLoaiMon = maLoaiMon;
        this.tenLoaiMon = tenLoaiMon;
        this.moTa = moTa;
    }

    public String getMaLoaiMon() {
        return maLoaiMon;
    }

    public String getTenLoaiMon() {
        return tenLoaiMon;
    }

    public void setTenLoaiMon(String tenLoaiMon) {
        this.tenLoaiMon = tenLoaiMon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.maLoaiMon);
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
        final LoaiMonAn other = (LoaiMonAn) obj;
        return Objects.equals(this.maLoaiMon, other.maLoaiMon);
    }

    @Override
    public String toString() {
        return "LoaiMonAn{" + "maLoaiMon=" + maLoaiMon + ", tenLoaiMon=" + tenLoaiMon + ", moTa=" + moTa + '}';
    }
    
    
}
