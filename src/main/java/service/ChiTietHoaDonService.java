/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.util.List;
import model.ChiTietHoaDon;

/**
 *
 * @author THANHTRI
 */
public interface ChiTietHoaDonService extends GenericService<ChiTietHoaDon, ChiTietHoaDon.ChiTietHoaDonId> {

    List<ChiTietHoaDon> getOrderDetails(String orderID) throws RemoteException;

    boolean saveCTHD(ChiTietHoaDon cthd) throws RemoteException;

    boolean updateSoLuongCTHD(String maHD, String maMonAn, int soLuong) throws RemoteException;

    boolean deleteCTHD(String maHD, String maMonAn) throws RemoteException;

}
