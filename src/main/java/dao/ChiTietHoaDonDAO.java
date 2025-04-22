/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.ChiTietHoaDon;

/**
 *
 * @author THANHTRI
 */
public interface ChiTietHoaDonDAO extends GenericDAO<ChiTietHoaDon, ChiTietHoaDon.ChiTietHoaDonId>{
 
    
    List<ChiTietHoaDon> getOrderDetails(String orderID);
    
    boolean saveCTHD(ChiTietHoaDon cthd);
    
    boolean updateSoLuongCTHD(String maHD,String maMonAn,int soLuong);
    
    boolean deleteCTHD(String maHD,String maMonAn);
    
   
}
