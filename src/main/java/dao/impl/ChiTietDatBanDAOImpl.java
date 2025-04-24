/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.ChiTietDatBanDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.util.Collections;
import java.util.List;
import model.ChiTietDatBan;

/**
 *
 * @author THANHTRI
 */
public class ChiTietDatBanDAOImpl extends GenericDAOImpl<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId> implements ChiTietDatBanDAO {

    public ChiTietDatBanDAOImpl(Class<ChiTietDatBan> clazz) {
        super(clazz);
    }

    public ChiTietDatBanDAOImpl(EntityManager em, Class<ChiTietDatBan> clazz) {
        super(em, clazz);
    }

    @Override
    public boolean luuCTDB(ChiTietDatBan ctdb) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(ctdb);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<ChiTietDatBan> getListByMaDDB(String maDDB) {
        if (maDDB == null || maDDB.trim().isEmpty()) {
            return Collections.emptyList();
        }
        try {
            TypedQuery<ChiTietDatBan> query = em.createQuery(
                    "FROM ChiTietDatBan ctdb WHERE ctdb.donDatBan.maDDB = :maDDB",
                    ChiTietDatBan.class
            );
            query.setParameter("maDDB", maDDB);
            return query.getResultList();
        } catch (NoResultException e) {
            return Collections.emptyList(); // Không có kết quả -> trả list rỗng
        } catch (Exception e) {
            e.printStackTrace();
            return Collections.emptyList(); // Lỗi khác cũng trả rỗng
        }
    }

    @Override
    public boolean deleteListByMaDDB(String maDDB) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin(); // Bắt đầu transaction

            int deletedCount = em.createQuery("DELETE FROM ChiTietDatBan ctdb WHERE ctdb.donDatBan.maDDB = :maDDB")
                    .setParameter("maDDB", maDDB)
                    .executeUpdate();

            tx.commit(); // Commit nếu thành công
            return deletedCount > 0;
        } catch (Exception e) {
            tx.rollback(); // Rollback nếu có lỗi
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Object[]> getChiTietDonDatBan(String maDDB) {
        String jpql = "SELECT ma.tenMA, ma.gia, ctdb.giaSauGiam, ctdb.soLuong, ctdb.thanhTien "
                + "FROM ChiTietDatBan ctdb "
                + "JOIN ctdb.donDatBan ddb "
                + "JOIN ctdb.monAn ma "
                + "WHERE ddb.maDDB = :maDDB";

        return em.createQuery(jpql, Object[].class)
                .setParameter("maDDB", maDDB)
                .getResultList();
    }
}
