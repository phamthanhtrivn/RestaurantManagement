/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.MonAn;

/**
 *
 * @author THANHTRI
 */
public interface MonAnDAO extends GenericDAO<MonAn, String> {

    List<MonAn> danhSachMonAnTheoMaLoai(String maLoai);

    List<MonAn> getMonTheoLoai(String maLoai);

    MonAn getMonAnTheoMa(String maMA);

    String maTuSinh();
    
}
