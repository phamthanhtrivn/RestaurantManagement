/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.List;
import java.util.Map;
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
}
