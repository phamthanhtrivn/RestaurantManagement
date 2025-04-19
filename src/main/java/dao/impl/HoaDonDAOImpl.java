/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.HoaDonDAO;
import jakarta.persistence.EntityManager;
import model.HoaDon;

/**
 *
 * @author THANHTRI
 */
public class HoaDonDAOImpl extends GenericDAOImpl<HoaDon, String> implements HoaDonDAO {
    
    public HoaDonDAOImpl(Class<HoaDon> clazz) {
        super(clazz);
    }
    
    public HoaDonDAOImpl(EntityManager em, Class<HoaDon> clazz) {
        super(em, clazz);
    }
    
}
