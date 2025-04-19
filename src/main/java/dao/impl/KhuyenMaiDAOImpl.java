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
    
}
