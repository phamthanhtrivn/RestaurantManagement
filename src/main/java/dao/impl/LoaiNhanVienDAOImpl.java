/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiNhanVienDAO;
import jakarta.persistence.EntityManager;
import model.LoaiNhanVien;

/**
 *
 * @author THANHTRI
 */
public class LoaiNhanVienDAOImpl extends GenericDAOImpl<LoaiNhanVien, String> implements LoaiNhanVienDAO {
    
    public LoaiNhanVienDAOImpl(Class<LoaiNhanVien> clazz) {
        super(clazz);
    }

    
    public LoaiNhanVienDAOImpl(EntityManager em, Class<LoaiNhanVien> clazz) {
        super(em, clazz);
    }
    
}
