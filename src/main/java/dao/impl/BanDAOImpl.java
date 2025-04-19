/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.BanDAO;
import jakarta.persistence.EntityManager;
import model.Ban;

/**
 *
 * @author THANHTRI
 */
public class BanDAOImpl extends GenericDAOImpl<Ban, String> implements BanDAO {
    
    public BanDAOImpl(Class<Ban> clazz) {
        super(clazz);
    }
    
    public BanDAOImpl(EntityManager em, Class<Ban> clazz) {
        super(em, clazz);
    }
    
}
