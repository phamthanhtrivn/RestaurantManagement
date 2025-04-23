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

   
}
