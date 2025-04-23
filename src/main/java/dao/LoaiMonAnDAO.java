/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.LoaiMonAn;

/**
 *
 * @author THANHTRI
 */
public interface LoaiMonAnDAO extends GenericDAO<LoaiMonAn, String>{
    
    LoaiMonAn findByName(String name);
}
