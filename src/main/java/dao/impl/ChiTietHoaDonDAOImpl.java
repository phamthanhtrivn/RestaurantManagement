/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.ChiTietHoaDonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import model.ChiTietHoaDon;
import model.ChiTietHoaDon.ChiTietHoaDonId;

/**
 *
 * @author THANHTRI
 */
public class ChiTietHoaDonDAOImpl extends GenericDAOImpl<ChiTietHoaDon, ChiTietHoaDonId> implements ChiTietHoaDonDAO {

    public ChiTietHoaDonDAOImpl(Class<ChiTietHoaDon> clazz) {
        super(clazz);
    }

    public ChiTietHoaDonDAOImpl(EntityManager em, Class<ChiTietHoaDon> clazz) {
        super(em, clazz);
    }

    @Override
    public List<ChiTietHoaDon> getOrderDetails(String hoaDon) {
        String query = "select CTHD from ChiTietHoaDon CTHD "
                + "WHERE CTHD.hoaDon.maHD = :hoaDon";
        return em.createQuery(query,ChiTietHoaDon.class)
                .setParameter("hoaDon", hoaDon).getResultList();
    }

    @Override
    public boolean saveCTHD(ChiTietHoaDon cthd) {
        String sql = "INSERT INTO chitiethoadon (thanhTien, soLuong, giaSauGiam, hoaDonID, monAnID) " +
                     "VALUES (?, ?, ?, ?, ?)";
        EntityTransaction tr = em.getTransaction();
        boolean result = false;

        try {
            tr.begin();
            em.createNativeQuery(sql)
              .setParameter(1, cthd.getThanhTien())
              .setParameter(2, cthd.getSoLuong())
              .setParameter(3, cthd.getGiaSauGiam())
              .setParameter(4, cthd.getHoaDon().getMaHD())
              .setParameter(5, cthd.getMonAn().getMaMA())
              .executeUpdate();
            tr.commit();
            result = true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public boolean updateSoLuongCTHD(String maHD, String maMonAn, int soLuong) {
        String sql = "UPDATE ChiTietHoaDon CTHD"
                + " Set soLuong = ? "
                + "where hoaDonID = ? and  monAnID = ?";
        EntityTransaction tr = em.getTransaction();
        boolean result = false;

        try {
            tr.begin();
            em.createNativeQuery(sql)
              .setParameter(1, soLuong)
              .setParameter(2, maHD)
              .setParameter(3, maMonAn)
              .executeUpdate();
            tr.commit();
            result = true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    @Override
    public boolean deleteCTHD(String maHD, String maMonAn) {
        String sql = "DELETE from ChiTietHoaDon CTHD"
         + "where hoaDonID = :maHD and  monAnID = :maMonAn";
        EntityTransaction tr = em.getTransaction();
        boolean result = false;

        try {
            tr.begin();
            em.createNativeQuery(sql)
              .setParameter("maHD", maHD)
              .setParameter("maMonAn", maMonAn)
              .executeUpdate();
            tr.commit();
            result = true;
        } catch (Exception e) {
            if (tr.isActive()) {
                tr.rollback();
            }
            e.printStackTrace();
        }
        return result;
    }
    
    



}
