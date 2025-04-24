/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.KhuyenMai;

/**
 *
 * @author THANHTRI
 */
public interface KhuyenMaiDAO extends GenericDAO<KhuyenMai, String>{
        String maTuSinh ();
        KhuyenMai findByTenKM(String tenKM);
}
