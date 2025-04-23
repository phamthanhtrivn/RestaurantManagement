/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.LoaiBanDAO;
import java.rmi.RemoteException;
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
    
    
}
