/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.ArrayList;
import java.util.List;
import model.DonDatBan;

/**
 *
 * @author THANHTRI
 */
public interface DonDatBanDAO extends GenericDAO<DonDatBan, String>{
    public Object timKiemDonDatBanMa(String maDonDatBan, String ngayDB, String ngayKT);

    // Xong
    public List<Object[]> timKiemDonDatBanName(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT);

    // Xong
    public List<Object[]> timKiemDonDatBanPhone(String phone, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT);
    
    
    public List<Object[]> donDatBanTrongNgay();
    
    List<Object[]> getChiTietDonDatBan(String maDonDatBan);
    
    Object[] timDDB(String ma);
    
    
   List<Object[]> timChiTietDonDatBan(String maDonDatBan);

}
