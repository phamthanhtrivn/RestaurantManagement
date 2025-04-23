/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.Ban;

/**
 *
 * @author THANHTRI
 */
public interface BanDAO extends GenericDAO<Ban, String>{
    
    List<Ban> danhSachBanTheoMaLoai(String maLoai);
    int getSoLuongBanTheoLBvTrangThai(String maLoai, int trangThai);
    boolean capNhatTrangThaiBan(String maBan, int tinhTrang);
}
