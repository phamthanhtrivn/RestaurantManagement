/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiBanDAO;
import jakarta.persistence.EntityManager;
import java.util.List;
import model.LoaiBan;

/**
 *
 * @author THANHTRI
 */
public class LoaiBanDAOImpl extends GenericDAOImpl<LoaiBan, String> implements LoaiBanDAO {

    public LoaiBanDAOImpl(Class<LoaiBan> clazz) {
        super(clazz);
    }

    public LoaiBanDAOImpl(EntityManager em, Class<LoaiBan> clazz) {
        super(em, clazz);
    }

    @Override
    public List<LoaiBan> getListLoaiBan() {
        String query = "from LoaiBan";
        return em.createQuery(query, LoaiBan.class).getResultList();
    }

    @Override
    public LoaiBan getLoaiBanTheoMa(String maLB) {
        String query = "from LoaiBan B "
                + "WHERE B.maLB = :maLB";

        return em.createQuery(query, LoaiBan.class)
                .setParameter("maLB", maLB)
                .getSingleResult();

    }

    @Override
    public LoaiBan getLoaiBanByName(String tenLoaiBan) {
        try {
            // JPQL để tìm loại bàn dựa trên tên loại bàn
            String jpql = "SELECT lb FROM LoaiBan lb WHERE lb.tenLB = :tenLoaiBan";
            LoaiBan loaiBan = em.createQuery(jpql, LoaiBan.class)
                    .setParameter("tenLoaiBan", tenLoaiBan)
                    .getSingleResult();
            return loaiBan;
        } catch (Exception e) {
            // Nếu không tìm thấy loại bàn hoặc có lỗi, trả về null hoặc có thể ném lỗi tùy thuộc vào yêu cầu
            return null;
        }
    }

}
