/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.LoaiBanDAO;
import java.rmi.RemoteException;
import java.util.List;
import model.LoaiBan;
import service.LoaiBanService;

/**
 *
 * @author THANHTRI
 */
public class LoaiBanServiceImpl extends GenericServiceImpl<LoaiBan, String> implements LoaiBanService {
    private LoaiBanDAO loaiBanDAO;

    public LoaiBanServiceImpl(LoaiBanDAO loaiBanDAO) throws RemoteException {
        super(loaiBanDAO);
        this.loaiBanDAO = loaiBanDAO;
    }

    @Override
    public List<LoaiBan> getListLoaiBan() throws RemoteException {
        return loaiBanDAO.getListLoaiBan();
    }

    @Override
    public LoaiBan getLoaiBanTheoMa(String maLB) throws RemoteException {
        return loaiBanDAO.getLoaiBanTheoMa(maLB);
    }

    @Override
    public LoaiBan getLoaiBanByName(String tenLoaiBan) throws RemoteException {
        return loaiBanDAO.getLoaiBanByName(tenLoaiBan);
    }
    
    
}
