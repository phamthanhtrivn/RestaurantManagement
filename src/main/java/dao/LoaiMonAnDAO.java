/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import model.LoaiMonAn;

/**
 *
 * @author THANHTRI
 */
public interface LoaiMonAnDAO extends GenericDAO<LoaiMonAn, String>{
    
    LoaiMonAn findByName(String name);
    List<LoaiMonAn> getListLoaiMonAn();
    LoaiMonAn getLoaiMonAnByTen(String tenLoaiMA);
}
