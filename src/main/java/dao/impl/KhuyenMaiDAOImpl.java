/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.KhuyenMaiDAO;
import jakarta.persistence.EntityManager;
import model.KhuyenMai;

/**
 *
 * @author THANHTRI
 */
public class KhuyenMaiDAOImpl extends GenericDAOImpl<KhuyenMai, String> implements KhuyenMaiDAO {

    public KhuyenMaiDAOImpl(Class<KhuyenMai> clazz) {
        super(clazz);
    }

    public KhuyenMaiDAOImpl(EntityManager em, Class<KhuyenMai> clazz) {
        super(em, clazz);
    }

    @Override
    public String maTuSinh() {
        try {
            String prefix = "KM";
            String jpql = "SELECT m.maKM FROM KhuyenMai m ORDER BY m.maKM DESC";
            String lastId = em.createQuery(jpql, String.class)
                    .setMaxResults(1)
                    .getSingleResult()
                    .trim();

            int number = Integer.parseInt(lastId.substring(prefix.length()));
            String newId = prefix + String.format("%03d", number + 1); // MA001, MA002,...
            return newId;
        } catch (Exception e) {
            return "KM001";
        }
    }

    @Override
    public KhuyenMai findByTenKM(String tenKM) {
        try {
            String jpql = "SELECT km FROM KhuyenMai km WHERE km.tenKM = :tenKM";
            return em.createQuery(jpql, KhuyenMai.class)
                    .setParameter("tenKM", tenKM)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        }
    }

}
