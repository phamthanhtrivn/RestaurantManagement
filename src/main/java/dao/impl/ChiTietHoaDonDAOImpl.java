/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.ChiTietHoaDonDAO;
import jakarta.persistence.EntityManager;
import model.ChiTietHoaDon;

/**
 *
 * @author THANHTRI
 */
public class ChiTietHoaDonDAOImpl extends GenericDAOImpl<ChiTietHoaDon, ChiTietHoaDon.ChiTietHoaDonId> implements ChiTietHoaDonDAO {

    public ChiTietHoaDonDAOImpl(Class<ChiTietHoaDon> clazz) {
        super(clazz);
    }

    public ChiTietHoaDonDAOImpl(EntityManager em, Class<ChiTietHoaDon> clazz) {
        super(em, clazz);
    }

    @Override
    public boolean createOrderDetail(ChiTietHoaDon cthd) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

}
