/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.DonDatBanDAO;
import jakarta.persistence.EntityManager;
import model.DonDatBan;

/**
 *
 * @author THANHTRI
 */
public class DonDatBanDAOImpl extends GenericDAOImpl<DonDatBan, String> implements DonDatBanDAO{
    
    public DonDatBanDAOImpl(Class<DonDatBan> clazz) {
        super(clazz);
    }
    
    public DonDatBanDAOImpl(EntityManager em, Class<DonDatBan> clazz) {
        super(em, clazz);
    }
    
}
