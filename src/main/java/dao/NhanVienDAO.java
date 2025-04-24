/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.NhanVien;

/**
 *
 * @author THANHTRI
 */
public interface NhanVienDAO extends GenericDAO<NhanVien, String> {

    NhanVien dangNhap(String username, String password);

    NhanVien getNV(String maNV);

    String maTuSinh(String maLoaiNhanVien);

    NhanVien findById2(String maNV);
}
