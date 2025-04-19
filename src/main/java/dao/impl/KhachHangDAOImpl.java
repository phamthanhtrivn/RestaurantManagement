/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.KhachHangDAO;
import jakarta.persistence.EntityManager;
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
    
}
