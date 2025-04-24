/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.GenericDAO;
import dao.LoaiNhanVienDAO;
import java.rmi.RemoteException;
import model.LoaiNhanVien;
import service.LoaiNhanVienService;

/**
 *
 * @author THANHTRI
 */
public class LoaiNhanVienServiceImpl extends GenericServiceImpl<LoaiNhanVien, String> implements LoaiNhanVienService {
    private LoaiNhanVienDAO loaiNhanVienDAO;

    public LoaiNhanVienServiceImpl(LoaiNhanVienDAO loaiNhanVienDAO) throws RemoteException {
        super(loaiNhanVienDAO);
        this.loaiNhanVienDAO = loaiNhanVienDAO;
    }

    @Override
    public LoaiNhanVien getLoaiNhanVienByViTri(String viTri) throws RemoteException {
        return loaiNhanVienDAO.getLoaiNhanVienByViTri(viTri);
    }
    
    
}
