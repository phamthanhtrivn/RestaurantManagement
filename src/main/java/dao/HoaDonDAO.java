/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.util.Map;
import model.Ban;
import model.HoaDon;

/**
 *
 * @author THANHTRI
 */
public interface HoaDonDAO extends GenericDAO<HoaDon, String> {

    List<Integer> loadNam();

    List<HoaDon> thongKeHoaDon(String type, Map<String, String> params);

    List<Object[]> thongKeMon(String type, Map<String, String> params);

    String createMaHD();

    
    
    List<Object[]> hoaDonTrongNgay();

    Object[] timKiemHoaDonTheoMa(String maHoaDon, String ngayDB, String ngayKT);

    List<Object[]> timKiemHoaDonTheoTenKH(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT);

    List<Object[]> timKiemHoaDonTheoSTD(String soDienThoai, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT);

    List<Object[]> timKiemHoaDonTheoSoBan(String soBan, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT);

    Object[] timKiemHD(String maHD);

    List<Object[]> timKiemCTHD(String maHD);

    boolean checkBanVip(String maBan);

    HoaDon getHoaDonTheoBanHoatDong(Ban ban);

    HoaDon getHoaDonTheoMa(String maHD);

}
