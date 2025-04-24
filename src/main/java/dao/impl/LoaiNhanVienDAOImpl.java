/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiNhanVienDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import model.LoaiNhanVien;

/**
 *
 * @author THANHTRI
 */
public class LoaiNhanVienDAOImpl extends GenericDAOImpl<LoaiNhanVien, String> implements LoaiNhanVienDAO {
    
    public LoaiNhanVienDAOImpl(Class<LoaiNhanVien> clazz) {
        super(clazz);
    }

    
    public LoaiNhanVienDAOImpl(EntityManager em, Class<LoaiNhanVien> clazz) {
        super(em, clazz);
    }

    @Override
    public LoaiNhanVien getLoaiNhanVienByViTri(String viTri) {
    // Tạo truy vấn JPQL để lấy đối tượng LoaiNhanVien theo vị trí
    TypedQuery<LoaiNhanVien> query = em.createQuery(
        "SELECT l FROM LoaiNhanVien l WHERE l.viTri = :viTri", LoaiNhanVien.class);
    query.setParameter("viTri", viTri);

    try {
        return query.getSingleResult(); // Trả về đối tượng LoaiNhanVien
    } catch (Exception e) {
        return null; // Nếu không tìm thấy thì trả về null
    }
}

    
}
