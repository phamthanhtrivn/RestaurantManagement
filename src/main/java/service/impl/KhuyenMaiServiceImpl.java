/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.KhuyenMaiDAO;
import java.rmi.RemoteException;
import model.KhuyenMai;
import service.KhuyenMaiService;

/**
 *
 * @author THANHTRI
 */
public class KhuyenMaiServiceImpl extends GenericServiceImpl<KhuyenMai, String> implements KhuyenMaiService {
    private KhuyenMaiDAO khuyenMaiDAO;

    public KhuyenMaiServiceImpl(KhuyenMaiDAO khuyenMaiDAO) throws RemoteException {
        super(khuyenMaiDAO);
        this.khuyenMaiDAO = khuyenMaiDAO;
    }

    @Override
    public String maTuSinh() throws RemoteException {
        return khuyenMaiDAO.maTuSinh();
    }

    @Override
    public KhuyenMai findByTenKM(String tenKM) throws RemoteException {
        return khuyenMaiDAO.findByTenKM(tenKM);
    }
}
