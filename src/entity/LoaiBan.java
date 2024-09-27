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
public class LoaiBan {
    private String maLoaiBan;
    private String tenLoaiBan;
    private String moTa;

    public LoaiBan() {
    }
    
    public LoaiBan(String maLoaiBan) {
        this.maLoaiBan = maLoaiBan;
    }

    public LoaiBan(String maLoaiBan, String tenLoaiBan, String moTa) {
        this.maLoaiBan = maLoaiBan;
        this.tenLoaiBan = tenLoaiBan;
        this.moTa = moTa;
    }

    public String getMaLoaiBan() {
        return maLoaiBan;
    }

    public String getTenLoaiBan() {
        return tenLoaiBan;
    }

    public void setTenLoaiBan(String tenLoaiBan) {
        this.tenLoaiBan = tenLoaiBan;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.maLoaiBan);
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
        final LoaiBan other = (LoaiBan) obj;
        return Objects.equals(this.maLoaiBan, other.maLoaiBan);
    }

    @Override
    public String toString() {
        return "LoaiBan{" + "maLoaiBan=" + maLoaiBan + ", tenLoaiBan=" + tenLoaiBan + ", moTa=" + moTa + '}';
    }
    
    
}
