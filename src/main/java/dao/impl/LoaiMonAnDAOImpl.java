/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.LoaiMonAnDAO;
import jakarta.persistence.EntityManager;
import model.LoaiMonAn;

/**
 *
 * @author THANHTRI
 */
public class LoaiMonAnDAOImpl extends GenericDAOImpl<LoaiMonAn, String> implements LoaiMonAnDAO {

    public LoaiMonAnDAOImpl(Class<LoaiMonAn> clazz) {
        super(clazz);
    }

    public LoaiMonAnDAOImpl(EntityManager em, Class<LoaiMonAn> clazz) {
        super(em, clazz);
    }

}
