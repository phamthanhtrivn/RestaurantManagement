/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.LoaiNhanVien;

/**
 *
 * @author THANHTRI
 */
public interface LoaiNhanVienDAO extends GenericDAO<LoaiNhanVien, String>{
        LoaiNhanVien getLoaiNhanVienByViTri(String viTri);
}
