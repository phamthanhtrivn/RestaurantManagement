/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiMonAnDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import model.LoaiMonAn;

/**
 *
 * @author THANHTRI
 */
public class LoaiMonAnDAOImpl extends GenericDAOImpl<LoaiMonAn, String> implements LoaiMonAnDAO {

    public LoaiMonAnDAOImpl(Class<LoaiMonAn> clazz) {
        super(clazz);
    }

    public LoaiMonAnDAOImpl(EntityManager em, Class<LoaiMonAn> clazz) {
        super(em, clazz);
    }

    @Override
    public LoaiMonAn findByName(String name) {
        return (LoaiMonAn) em.createQuery("FROM LoaiMonAn lma WHERE lma.tenLoaiMA = :name", LoaiMonAn.class).setParameter("name", name).getSingleResult();
    }

    @Override
    public List<LoaiMonAn> getListLoaiMonAn() {
        String query = "from LoaiMonAn";
        return em.createQuery(query, LoaiMonAn.class).getResultList();
    }

    @Override
    public LoaiMonAn getLoaiMonAnByTen(String tenLoaiMA) {
        TypedQuery<LoaiMonAn> query = em.createQuery(
                "SELECT l FROM LoaiMonAn l WHERE l.tenLoaiMA = :tenLoai", LoaiMonAn.class);
        query.setParameter("tenLoai", tenLoaiMA);

        try {
            return query.getSingleResult(); // Trả về đối tượng LoaiMonAn nếu tìm thấy
        } catch (Exception e) {
            return null; // Trả về null nếu không tìm thấy
        }

    }
    
}
