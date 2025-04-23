/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.NhanVienDAO;
import java.rmi.RemoteException;
import model.NhanVien;
import service.NhanVienService;

/**
 *
 * @author THANHTRI
 */
public class NhanVienServiceImpl extends GenericServiceImpl<NhanVien, String> implements NhanVienService {
    private NhanVienDAO nhanVienDAO;

    public NhanVienServiceImpl(NhanVienDAO nhanVienDAO) throws RemoteException {
        super(nhanVienDAO);
        this.nhanVienDAO = nhanVienDAO;
    }

    @Override
    public NhanVien dangNhap(String username, String password) throws RemoteException {
        return nhanVienDAO.dangNhap(username, password);
    }
    
    
}
