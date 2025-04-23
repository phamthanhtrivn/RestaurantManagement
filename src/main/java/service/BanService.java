/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.util.List;
import model.Ban;

/**
 *
 * @author THANHTRI
 */
public interface BanService extends GenericService<Ban, String> {
    
    List<Ban> danhSachBanTheoMaLoai(String maLoai) throws RemoteException;
    int getSoLuongBanTheoLBvTrangThai(String maLoai, int trangThai) throws RemoteException;
    boolean capNhatTrangThaiBan(String maBan, int tinhTrang) throws RemoteException;
}
