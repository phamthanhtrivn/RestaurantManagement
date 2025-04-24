/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import model.KhachHang;

/**
 *
 * @author THANHTRI
 */
public interface KhachHangService extends GenericService<KhachHang, String> {

    KhachHang findByPhone(String phone) throws RemoteException;

    KhachHang getKHSDT(String std) throws RemoteException;

    boolean updateDiemLT(String maKH, int diemTL) throws RemoteException;

    boolean updateLoaiKH(String maKH) throws RemoteException;

    String maTuSinh() throws RemoteException;
}
