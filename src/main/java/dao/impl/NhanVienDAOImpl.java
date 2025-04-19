/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.NhanVienDAO;
import jakarta.persistence.EntityManager;
import model.NhanVien;

/**
 *
 * @author THANHTRI
 */
public class NhanVienDAOImpl extends GenericDAOImpl<NhanVien, String> implements NhanVienDAO {
    
    public NhanVienDAOImpl(Class<NhanVien> clazz) {
        super(clazz);
    }
    
    public NhanVienDAOImpl(EntityManager em, Class<NhanVien> clazz) {
        super(em, clazz);
    }
    
}
