/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.KhachHangDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.EntityTransaction;
import model.KhachHang;

/**
 *
 * @author THANHTRI
 */
public class KhachHangDAOImpl extends GenericDAOImpl<KhachHang, String> implements KhachHangDAO {
   
    public KhachHangDAOImpl(Class<KhachHang> clazz) {
        super(clazz);
    }
    
    public KhachHangDAOImpl(EntityManager em, Class<KhachHang> clazz) {
        super(em, clazz);
    }

    @Override
    public KhachHang findByPhone(String phone) {
        KhachHang khachHang = null;
        try {
            khachHang = (KhachHang) em.createQuery("FROM KhachHang kh WHERE kh.soDT =: phone", KhachHang.class).setParameter("phone", phone).getSingleResult();
        } catch (NoResultException e) {
            
        }
        return khachHang;
    }
    
    public KhachHang getKHSDT(String std) {
        try{
          String query = "from KhachHang where soDT = :std";
        return em.createQuery(query,KhachHang.class)
                .setParameter("std", std)
                .getSingleResult();
        }
        catch(Exception ex){
            return null;
        }
    }

    @Override
    public boolean updateDiemLT(String maKH, int diemTL) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            String query = "UPDATE KhachHang KH "
                    + "SET diemTL = diemTL + :diemTL "
                    + "WHERE maKH = :maKH";

            int result = em.createQuery(query)
                    .setParameter("maKH", maKH)
                    .setParameter("diemTL", diemTL)
                    .executeUpdate();

            transaction.commit();
            return result > 0;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public boolean updateLoaiKH(String maKH) {
        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            String query = "UPDATE KhachHang KH " +
                    "SET loaiKhachHang.maLoaiKH = " +
                    "    CASE " +
                    "        WHEN diemTL >= 0 AND diemTL < 200 THEN 'LKH1' " +
                    "        WHEN diemTL >= 200 AND diemTL < 300 THEN 'LKH2' " +
                    "        WHEN diemTL >= 300 THEN 'LKH3' " +
                    "        ELSE loaiKhachHang.maLoaiKH " +
                    "    END " +
                    "WHERE maKH = :maKH";

            int updated = em.createQuery(query)
                    .setParameter("maKH", maKH)
                    .executeUpdate();

            transaction.commit();
            return updated > 0;
        } catch (Exception e) {
            transaction.rollback();
            e.printStackTrace();
            return false;
        }
    }
    
        @Override
    public String maTuSinh(){
       try {
        String prefix = "KH";
        String jpql = "SELECT k.maKH FROM KhachHang k ORDER BY k.maKH DESC";
        String lastId = em.createQuery(jpql, String.class)
                          .setMaxResults(1)
                          .getSingleResult()
                          .trim();

        int number = Integer.parseInt(lastId.substring(prefix.length()));
        String newId = prefix + String.format("%06d", number + 1); // 6 chữ số
        return newId;
    } catch (Exception e) {
        return "KH000001";
    }
    }
    
}
