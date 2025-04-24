/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.KhachHang;

/**
 *
 * @author THANHTRI
 */
public interface KhachHangDAO extends GenericDAO<KhachHang, String>{
    
    KhachHang findByPhone(String phone);

    KhachHang getKHSDT(String std);
    boolean updateDiemLT(String maKH, int diemTL);
    boolean updateLoaiKH(String maKH);
    String maTuSinh ();
}
