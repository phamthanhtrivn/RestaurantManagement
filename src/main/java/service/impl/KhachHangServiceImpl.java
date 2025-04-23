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
    
}
