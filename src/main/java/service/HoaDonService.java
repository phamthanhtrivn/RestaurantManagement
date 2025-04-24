/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import model.Ban;
import model.HoaDon;

/**
 *
 * @author THANHTRI
 */
public interface HoaDonService extends GenericService<HoaDon, String> {

    List<Integer> loadNam() throws RemoteException;

    List<HoaDon> thongKeHoaDon(String type, Map<String, String> params) throws RemoteException;

    List<Object[]> thongKeMon(String type, Map<String, String> params) throws RemoteException;

    String createMaHD() throws RemoteException;

    List<Object[]> hoaDonTrongNgay() throws RemoteException;

    Object[] timKiemHoaDonTheoMa(String maHoaDon, String ngayDB, String ngayKT) throws RemoteException;

    List<Object[]> timKiemHoaDonTheoTenKH(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException;

    List<Object[]> timKiemHoaDonTheoSTD(String soDienThoai, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException;

    List<Object[]> timKiemHoaDonTheoSoBan(String soBan, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException;

    Object[] timKiemHD(String maHD) throws RemoteException;

    List<Object[]> timKiemCTHD(String maHD) throws RemoteException;

    boolean checkBanVip(String maBan) throws RemoteException;

    HoaDon getHoaDonTheoBanHoatDong(Ban ban) throws RemoteException;

    HoaDon getHoaDonTheoMa(String maHD) throws RemoteException;
}
