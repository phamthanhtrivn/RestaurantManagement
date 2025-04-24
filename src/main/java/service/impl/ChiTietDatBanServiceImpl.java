/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.ChiTietDatBanDAO;
import java.rmi.RemoteException;
import java.util.List;
import model.ChiTietDatBan;
import service.ChiTietDatBanService;

/**
 *
 * @author THANHTRI
 */
public class ChiTietDatBanServiceImpl extends GenericServiceImpl<ChiTietDatBan, ChiTietDatBan.ChiTietDatBanId> implements ChiTietDatBanService {

    private ChiTietDatBanDAO chiTietDatBanDAO;
    
    public ChiTietDatBanServiceImpl(ChiTietDatBanDAO chiTietDatBanDAO) throws RemoteException {
        super(chiTietDatBanDAO);
        this.chiTietDatBanDAO = chiTietDatBanDAO;
    }
    
    @Override
    public List<ChiTietDatBan> getListByMaDDB(String maDDB) throws RemoteException {
        return chiTietDatBanDAO.getListByMaDDB(maDDB);
    }

    @Override
    public boolean deleteListByMaDDB(String maDDB) throws RemoteException {
        return chiTietDatBanDAO.deleteListByMaDDB(maDDB);
    }

    @Override
    public List<Object[]> getChiTietDonDatBan(String maDDB) throws RemoteException {
        return chiTietDatBanDAO.getChiTietDonDatBan(maDDB);
    }

    @Override
    public boolean luuCTDB(ChiTietDatBan ctdb) throws RemoteException {
        return chiTietDatBanDAO.luuCTDB(ctdb);
    }
    
}
