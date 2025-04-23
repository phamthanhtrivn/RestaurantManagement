/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.KhachHangDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
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
    
}
