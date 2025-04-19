/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiBanDAO;
import jakarta.persistence.EntityManager;
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
    
}
