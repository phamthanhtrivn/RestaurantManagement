/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import model.NhanVien;

/**
 *
 * @author THANHTRI
 */
public interface NhanVienService extends GenericService<NhanVien, String> {

    NhanVien dangNhap(String username, String password) throws RemoteException;

    NhanVien getNV(String maNV) throws RemoteException;

    String maTuSinh(String maLoaiNhanVien) throws RemoteException;

    NhanVien findById2(String maNV) throws RemoteException;
    
    boolean checkOTP(String maNV, String email, String otp)throws RemoteException;

    boolean updatePassword(String maNV, String pass)throws RemoteException;

    String getOldPass(String maNV)throws RemoteException;

    boolean checkEmail(String maNV, String email)throws RemoteException;

    boolean checkMaNV(String maNV)throws RemoteException;

    String generateOTP(int length)throws RemoteException;

    boolean updateOTP(String maNV, String otp)throws RemoteException;

    boolean deleteOTP(String maNV)throws RemoteException;
}
