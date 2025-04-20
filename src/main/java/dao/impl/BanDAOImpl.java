/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.BanDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import model.Ban;

/**
 *
 * @author THANHTRI
 */
public class BanDAOImpl extends GenericDAOImpl<Ban, String> implements BanDAO {
    
    public BanDAOImpl(Class<Ban> clazz) {
        super(clazz);
    }
    
    public BanDAOImpl(EntityManager em, Class<Ban> clazz) {
        super(em, clazz);
    }

    @Override
    public int getSoLuongBanTheoLBvTrangThai(String maLB, int trangThai) {
        String query = "SELECT COUNT(B) FROM Ban B " +
                       "WHERE B.loaiBan.maLB = :maLB AND B.tinhTrang = :trangThai";

        Long count = em.createQuery(query, Long.class)  
                       .setParameter("maLB", maLB)
                       .setParameter("trangThai", trangThai)
                       .getSingleResult();

    return count.intValue();
}

    @Override
    public List<Ban> getListBanTheoLoai(String maLoai) {
        String query = "from Ban B "
                + "WHERE B.loaiBan.maLB = :maLoai";
        
        return em.createQuery(query,Ban.class)
                .setParameter("maLoai", maLoai)
                .getResultList();
    }

    @Override
    public boolean updateTableState(String maBan, int trangThai) {
        String query = "UPDATE Ban b "
                     + "SET b.tinhTrang = :trangThai "
                     + "WHERE b.maBan = :maBan";

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            // Gọi update
            int updatedCount = em.createQuery(query)
                                 .setParameter("trangThai", trangThai)
                                 .setParameter("maBan", maBan)
                                 .executeUpdate();

            transaction.commit();

            return updatedCount > 0;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }
    
}
