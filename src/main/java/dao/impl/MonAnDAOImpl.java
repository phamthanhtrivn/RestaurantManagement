/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.MonAnDAO;
import jakarta.persistence.EntityManager;
import model.MonAn;

/**
 *
 * @author THANHTRI
 */
public class MonAnDAOImpl extends GenericDAOImpl<MonAn, String> implements MonAnDAO {
    
    public MonAnDAOImpl(Class<MonAn> clazz) {
        super(clazz);
    }
    
    public MonAnDAOImpl(EntityManager em, Class<MonAn> clazz) {
        super(em, clazz);
    }
    
}
