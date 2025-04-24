/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.BanDAO;
import java.rmi.RemoteException;
import java.util.List;
import model.Ban;
import service.BanService;

/**
 *
 * @author THANHTRI
 */
public class BanServiceImpl extends GenericServiceImpl<Ban, String> implements BanService {
    private BanDAO banDAO;
    
    public BanServiceImpl(BanDAO banDAO) throws RemoteException {
        super(banDAO);
        this.banDAO = banDAO;
    }

    @Override
    public List<Ban> danhSachBanTheoMaLoai(String maLoai) throws RemoteException {
        return banDAO.danhSachBanTheoMaLoai(maLoai);
    }

    @Override
    public int getSoLuongBanTheoLBvTrangThai(String maLoai, int trangThai) throws RemoteException {
        return banDAO.getSoLuongBanTheoLBvTrangThai(maLoai, trangThai);
    }

    @Override
    public boolean capNhatTrangThaiBan(String maBan, int tinhTrang) throws RemoteException {
        return banDAO.capNhatTrangThaiBan(maBan, tinhTrang);
    }

    @Override
    public List<Ban> getListBanTheoLoai(String maLoai) throws RemoteException {
        return banDAO.getListBanTheoLoai(maLoai);
    }

    @Override
    public boolean updateTableState(String maBan, int trangThai) throws RemoteException {
        return banDAO.updateTableState(maBan, trangThai);
    }

    @Override
    public Ban getBan(String maBan) throws RemoteException {
        return banDAO.getBan(maBan);
    }

    @Override
    public String maTuSinh(String maLoaiBan) throws RemoteException {
        return banDAO.maTuSinh(maLoaiBan);
    }
    
}
