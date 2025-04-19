/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.MonAnDAO;
import jakarta.persistence.EntityManager;
import java.util.List;
import model.MonAn;

/**
 *
 * @author THANHTRI
 */
public class MonAnDAOImpl extends GenericDAOImpl<MonAn, String> implements MonAnDAO {
    
    public MonAnDAOImpl(Class<MonAn> clazz) {
        super(clazz);
    }
    
    public MonAnDAOImpl(EntityManager em, Class<MonAn> clazz) {
        super(em, clazz);
    }

    @Override
    public List<MonAn> danhSachMonAnTheoMaLoai(String maLoai) {
        return em.createQuery("from MonAn ma WHERE ma.loaiMonAn.maLoaiMA = :maLoai", MonAn.class)
                .setParameter("maLoai", maLoai).getResultList();
    }
    
}
