/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.KhachHangDAO;
import java.rmi.RemoteException;
import model.KhachHang;
import service.KhachHangService;

/**
 *
 * @author THANHTRI
 */
public class KhachHangServiceImpl extends GenericServiceImpl<KhachHang, String> implements KhachHangService {
    private KhachHangDAO khachHangDAO;
    
    public KhachHangServiceImpl(KhachHangDAO khachHangDAO) throws RemoteException {
        super(khachHangDAO);
        this.khachHangDAO = khachHangDAO;
    }

    @Override
    public KhachHang findByPhone(String phone) throws RemoteException {
        return khachHangDAO.findByPhone(phone);
    }

    @Override
    public KhachHang getKHSDT(String std) throws RemoteException {
        return khachHangDAO.getKHSDT(std);
    }

    @Override
    public boolean updateDiemLT(String maKH, int diemTL) throws RemoteException {
        return khachHangDAO.updateDiemLT(maKH, diemTL);
    }

    @Override
    public boolean updateLoaiKH(String maKH) throws RemoteException {
        return khachHangDAO.updateLoaiKH(maKH);
    }

    @Override
    public String maTuSinh() throws RemoteException {
        return khachHangDAO.maTuSinh();
    }
    
}
