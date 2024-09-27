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
public class Ban {
    private String maBan;
    private int soBan;
    private int soGhe;
    private String tinhTrang;
    private LoaiBan loaiBan;

    public Ban() {
    }
    
    public Ban(String maBan) {
        this.maBan = maBan;
    }

    public Ban(String maBan, int soBan, int soGhe, String tinhTrang, LoaiBan loaiBan) {
        this.maBan = maBan;
        this.soBan = soBan;
        this.soGhe = soGhe;
        this.tinhTrang = tinhTrang;
        this.loaiBan = loaiBan;
    }

    public String getMaBan() {
        return maBan;
    }

    public int getSoBan() {
        return soBan;
    }

    public void setSoBan(int soBan) {
        this.soBan = soBan;
    }

    public int getSoGhe() {
        return soGhe;
    }

    public void setSoGhe(int soGhe) {
        this.soGhe = soGhe;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
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
        hash = 19 * hash + Objects.hashCode(this.maBan);
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
        final Ban other = (Ban) obj;
        return Objects.equals(this.maBan, other.maBan);
    }

    @Override
    public String toString() {
        return "Ban{" + "maBan=" + maBan + ", soBan=" + soBan + ", soGhe=" + soGhe + ", tinhTrang=" + tinhTrang + ", loaiBan=" + loaiBan + '}';
    }
    
    
    
}
