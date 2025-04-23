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
    public List<Ban> danhSachBanTheoMaLoai(String maLoai) {
        return em.createQuery("from Ban b WHERE b.loaiBan.maLB = :maLoai", Ban.class)
                .setParameter("maLoai", maLoai).getResultList();
    }

    @Override
    public int getSoLuongBanTheoLBvTrangThai(String maLB, int trangThai) {
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(b) FROM Ban b WHERE b.loaiBan.maLB = :maLB AND b.tinhTrang = :trangThai", Long.class)
                    .setParameter("maLB", maLB)
                    .setParameter("trangThai", trangThai)
                    .getSingleResult();
            return count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean capNhatTrangThaiBan(String maBan, int tinhTrang) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int result = em.createQuery("UPDATE Ban b SET b.tinhTrang = :tinhTrang WHERE b.maBan =:maBan").setParameter("tinhTrang", tinhTrang).setParameter("maBan", maBan).executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }

        }
        return false;
    }

    @Override
    public List<Ban> getListBanTheoLoai(String maLoai) {
        String query = "from Ban B "
                + "WHERE B.loaiBan.maLB = :maLoai";

        return em.createQuery(query, Ban.class)
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

    @Override
    public Ban getBan(String maBan) {
        String query = "from Ban where maBan = :maBan";
        return em.createQuery(query, Ban.class)
                .setParameter("maBan", maBan)
                .getSingleResult();
    }

}
