/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.LoaiKhachHang;

/**
 *
 * @author THANHTRI
 */
public interface LoaiKhachHangDAO extends GenericDAO<LoaiKhachHang, String>{
    LoaiKhachHang TimLoaiKhachHangTim(String maLoai);
    LoaiKhachHang getLoaiKhachHangByName(String tenLoaiKH);
}
