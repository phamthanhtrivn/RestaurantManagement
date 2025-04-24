/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiKhachHangDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
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
    
        @Override
    public LoaiKhachHang getLoaiKhachHangByName(String tenLoaiKH){
         // Tạo truy vấn JPQL để lấy đối tượng LoaiKhachHang theo tên
        TypedQuery<LoaiKhachHang> query = em.createQuery(
            "SELECT l FROM LoaiKhachHang l WHERE l.tenLoaiKH = :tenLoaiKH", LoaiKhachHang.class);
        query.setParameter("tenLoaiKH", tenLoaiKH);
        
        try {
            return query.getSingleResult(); // Trả về đối tượng LoaiKhachHang
        } catch (Exception e) {
            return null; // Nếu không tìm thấy thì trả về null
        }
    }

}
