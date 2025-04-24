/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.LoaiMonAnDAO;
import java.rmi.RemoteException;
import java.util.List;
import model.LoaiMonAn;
import service.LoaiMonAnService;

/**
 *
 * @author THANHTRI
 */
public class LoaiMonAnServiceImpl extends GenericServiceImpl<LoaiMonAn, String> implements LoaiMonAnService {
    private LoaiMonAnDAO loaiMonAnDAO;

    public LoaiMonAnServiceImpl(LoaiMonAnDAO loaiMonAnDAO) throws RemoteException {
        super(loaiMonAnDAO);
        this.loaiMonAnDAO = loaiMonAnDAO;
    }

    @Override
    public LoaiMonAn findByName(String name) throws RemoteException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<LoaiMonAn> getListLoaiMonAn() throws RemoteException {
        return loaiMonAnDAO.getListLoaiMonAn();
    }

    @Override
    public LoaiMonAn getLoaiMonAnByTen(String tenLoaiMA) throws RemoteException {
        return loaiMonAnDAO.getLoaiMonAnByTen(tenLoaiMA);
    }
    
    
}
