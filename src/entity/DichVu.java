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
public class DichVu {
    private String maDV;
    private double VAT;
    private double DV;
    private double phongVIP;

    public DichVu() {
    }
    
    public DichVu(String maDV) {
        this.maDV = maDV;
    }

    public DichVu(String maDV, double VAT, double DV, double phongVIP) {
        this.maDV = maDV;
        this.VAT = VAT;
        this.DV = DV;
        this.phongVIP = phongVIP;
    }

    public String getMaDV() {
        return maDV;
    }

    public double getVAT() {
        return VAT;
    }

    public void setVAT(double VAT) {
        this.VAT = VAT;
    }

    public double getDV() {
        return DV;
    }

    public void setDV(double DV) {
        this.DV = DV;
    }

    public double getPhongVIP() {
        return phongVIP;
    }

    public void setPhongVIP(double phongVIP) {
        this.phongVIP = phongVIP;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.maDV);
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
        final DichVu other = (DichVu) obj;
        return Objects.equals(this.maDV, other.maDV);
    }

    @Override
    public String toString() {
        return "DichVu{" + "maDV=" + maDV + ", VAT=" + VAT + ", DV=" + DV + ", phongVIP=" + phongVIP + '}';
    }
    
    
}
