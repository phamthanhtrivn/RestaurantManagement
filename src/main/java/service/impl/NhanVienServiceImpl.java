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

    @Override
    public NhanVien getNV(String maNV) throws RemoteException {
        return nhanVienDAO.getNV(maNV);
    }

    @Override
    public String maTuSinh(String maLoaiNhanVien) throws RemoteException {
        return nhanVienDAO.maTuSinh(maLoaiNhanVien);
    }

    @Override
    public NhanVien findById2(String maNV) throws RemoteException {
        return nhanVienDAO.findById2(maNV);
    }

    @Override
    public boolean checkOTP(String maNV, String email, String otp) throws RemoteException {
        return nhanVienDAO.checkOTP(maNV, email, otp);
    }

    @Override
    public boolean updatePassword(String maNV, String pass) throws RemoteException {
        return nhanVienDAO.updatePassword(maNV, pass);
    }

    @Override
    public String getOldPass(String maNV) throws RemoteException {
        return nhanVienDAO.getOldPass(maNV);
    }

    @Override
    public boolean checkEmail(String maNV, String email) throws RemoteException {
        return nhanVienDAO.checkEmail(maNV, email);
    }

    @Override
    public boolean checkMaNV(String maNV) throws RemoteException {
        return nhanVienDAO.checkMaNV(maNV);
    }

    @Override
    public String generateOTP(int length) throws RemoteException {
        return nhanVienDAO.generateOTP(length);
    }

    @Override
    public boolean updateOTP(String maNV, String otp) throws RemoteException {
        return nhanVienDAO.updateOTP(maNV, otp);
    }

    @Override
    public boolean deleteOTP(String maNV) throws RemoteException {
        return nhanVienDAO.deleteOTP(maNV);
    }
    
    
}
