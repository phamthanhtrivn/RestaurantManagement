/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.ChiTietDatBanDAO;
import jakarta.persistence.EntityManager;
import model.ChiTietDatBan;

/**
 *
 * @author THANHTRI
 */
public class ChiTietDatBanDAOImpl extends GenericDAOImpl<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId> implements ChiTietDatBanDAO{
    
    public ChiTietDatBanDAOImpl(Class<ChiTietDatBan> clazz) {
        super(clazz);
    }
    
    public ChiTietDatBanDAOImpl(EntityManager em, Class<ChiTietDatBan> clazz) {
        super(em, clazz);
    }
}
