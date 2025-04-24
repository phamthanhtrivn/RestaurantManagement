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
    int getSoLuongBanTheoLBvTrangThai(String maLB, int trangThai);
    List<Ban> danhSachBanTheoMaLoai(String maLoai);
    boolean capNhatTrangThaiBan(String maBan, int tinhTrang);
    List<Ban> getListBanTheoLoai(String maLoai);
    boolean updateTableState(String maBan, int trangThai);
    Ban getBan(String maBan);
    String maTuSinh(String maLoaiBan);
    
}
