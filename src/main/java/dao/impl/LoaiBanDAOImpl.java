/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiBanDAO;
import jakarta.persistence.EntityManager;
import java.util.List;
import model.LoaiBan;

/**
 *
 * @author THANHTRI
 */
public class LoaiBanDAOImpl extends GenericDAOImpl<LoaiBan, String> implements LoaiBanDAO {
    
    public LoaiBanDAOImpl(Class<LoaiBan> clazz) {
        super(clazz);
    }
    
    public LoaiBanDAOImpl(EntityManager em, Class<LoaiBan> clazz) {
        super(em, clazz);
    }

    @Override
    public List<LoaiBan> getListLoaiBan() {
        String query = "from LoaiBan";
        return em.createQuery(query,LoaiBan.class).getResultList();
    }

    @Override
    public LoaiBan getLoaiBanTheoMa(String maLB) {
        String query = "from LoaiBan B "
                + "WHERE B.maLB = :maLB";
        
        return em.createQuery(query,LoaiBan.class)
                .setParameter("maLB", maLB)
                .getSingleResult();
        
    }


    
}
