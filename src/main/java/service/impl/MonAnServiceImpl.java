/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.MonAnDAO;
import java.rmi.RemoteException;
import java.util.List;
import model.MonAn;
import service.MonAnService;

/**
 *
 * @author THANHTRI
 */
public class MonAnServiceImpl extends GenericServiceImpl<MonAn, String> implements MonAnService {
    private MonAnDAO monAnDAO;
    
    public MonAnServiceImpl(MonAnDAO monAnDAO) throws RemoteException {
        super(monAnDAO);
        this.monAnDAO = monAnDAO;
    }

    @Override
    public List<MonAn> danhSachMonAnTheoMaLoai(String maLoai) throws RemoteException {
        return monAnDAO.danhSachMonAnTheoMaLoai(maLoai);
    }
    
}
