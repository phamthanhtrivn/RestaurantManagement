/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.ChiTietHoaDonDAO;
import java.rmi.RemoteException;
import java.util.List;
import model.ChiTietHoaDon;
import service.ChiTietDatBanService;
import service.ChiTietHoaDonService;

/**
 *
 * @author THANHTRI
 */
public class ChiTietHoaDonServiceImpl extends GenericServiceImpl<ChiTietHoaDon, ChiTietHoaDon.ChiTietHoaDonId> implements ChiTietHoaDonService {
    
    private ChiTietHoaDonDAO chiTietHoaDonDAO;
    
    public ChiTietHoaDonServiceImpl(ChiTietHoaDonDAO chiTietHoaDonDAO) throws RemoteException {
        super(chiTietHoaDonDAO);
        this.chiTietHoaDonDAO = chiTietHoaDonDAO;
    }

    @Override
    public List<ChiTietHoaDon> getOrderDetails(String orderID) throws RemoteException {
        return chiTietHoaDonDAO.getOrderDetails(orderID);
    }

    @Override
    public boolean saveCTHD(ChiTietHoaDon cthd) throws RemoteException {
        return chiTietHoaDonDAO.saveCTHD(cthd);
    }

    @Override
    public boolean updateSoLuongCTHD(String maHD, String maMonAn, int soLuong) throws RemoteException {
        return chiTietHoaDonDAO.updateSoLuongCTHD(maHD, maMonAn, soLuong);
    }

    @Override
    public boolean deleteCTHD(String maHD, String maMonAn) throws RemoteException {
        return chiTietHoaDonDAO.deleteCTHD(maHD, maMonAn);
    }

   
}
