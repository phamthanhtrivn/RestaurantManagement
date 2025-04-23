/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.LoaiKhachHangDAO;
import java.rmi.RemoteException;
import model.LoaiKhachHang;
import service.LoaiKhachHangService;

/**
 *
 * @author THANHTRI
 */
public class LoaiKhachHangServiceImpl extends GenericServiceImpl<LoaiKhachHang, String> implements LoaiKhachHangService {
    private LoaiKhachHangDAO loaiKhachHangDAO;

    public LoaiKhachHangServiceImpl(LoaiKhachHangDAO loaiKhachHangDAO) throws RemoteException {
        super(loaiKhachHangDAO);
        this.loaiKhachHangDAO = loaiKhachHangDAO;
    }
    
}
