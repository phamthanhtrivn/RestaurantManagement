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

    int getSoLuongBanTheoLBvTrangThai(String maLB, int trangThai) throws RemoteException;

    List<Ban> danhSachBanTheoMaLoai(String maLoai) throws RemoteException;

    boolean capNhatTrangThaiBan(String maBan, int tinhTrang) throws RemoteException;

    List<Ban> getListBanTheoLoai(String maLoai) throws RemoteException;

    boolean updateTableState(String maBan, int trangThai) throws RemoteException;

    Ban getBan(String maBan) throws RemoteException;

    String maTuSinh(String maLoaiBan) throws RemoteException;
}
