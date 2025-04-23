/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiKhachHangDAO;
import jakarta.persistence.EntityManager;
import model.LoaiKhachHang;

/**
 *
 * @author THANHTRI
 */
public class LoaiKhachHangDAOImpl extends GenericDAOImpl<LoaiKhachHang, String> implements LoaiKhachHangDAO {

    public LoaiKhachHangDAOImpl(Class<LoaiKhachHang> clazz) {
        super(clazz);
    }

    public LoaiKhachHangDAOImpl(EntityManager em, Class<LoaiKhachHang> clazz) {
        super(em, clazz);
    }

    @Override
    public LoaiKhachHang TimLoaiKhachHangTim(String maLoai) {
        String query = "from LoaiKhachHang where maLoaiKH = :maLoai";
        return em.createQuery(query,LoaiKhachHang.class)
                .setParameter("maLoai", maLoai)
                .getSingleResult();
    }

}
