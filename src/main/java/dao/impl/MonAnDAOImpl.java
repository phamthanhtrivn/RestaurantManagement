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
    
    @Override
    public List<MonAn> getMonTheoLoai(String maLoai) {
        String query = "select ma from MonAn ma where ma.loaiMonAn.maLoaiMA = :maLoai";
        
        
        return em.createQuery(query,MonAn.class)
                .setParameter("maLoai", maLoai)
                .getResultList();
        
    }
    
    @Override
    public MonAn getMonAnTheoMa(String maMA) {
        String query = "from MonAn ma where ma.maMA = :maMA";
        return em.createQuery(query,MonAn.class)
                .setParameter("maMA",maMA )
                .getSingleResult();
    }
    
        @Override
    public String maTuSinh() {
    try {
        String prefix = "MA";
        String jpql = "SELECT m.maMA FROM MonAn m ORDER BY m.maMA DESC";
        String lastId = em.createQuery(jpql, String.class)
                          .setMaxResults(1)
                          .getSingleResult()
                          .trim();

        int number = Integer.parseInt(lastId.substring(prefix.length()));
        String newId = prefix + String.format("%03d", number + 1); // MA001, MA002,...
        return newId;
    } catch (Exception e) {
        return "MA001";
    }
}
    
}
