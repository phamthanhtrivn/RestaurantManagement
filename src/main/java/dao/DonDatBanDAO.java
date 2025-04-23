/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import model.DonDatBan;

/**
 *
 * @author THANHTRI
 */
public interface DonDatBanDAO extends GenericDAO<DonDatBan, String>{
    
    boolean checkTimeBan(String maBan, LocalDateTime gioHen);
    int findSoThuTuHomNay();
    List<DonDatBan> timKiemCapNhat(LocalDate date, String phone);
    boolean huyDonDatBan(String ma, double tienHoan, LocalDateTime gioHuy);
    Object[] getThongTinDonDatBan(String maDDB);
    List<Integer> loadNam();
    List<DonDatBan> thongKeDonDatBan(String type, Map<String, String> params);
    boolean capNhatTTDDBDaNhanVaTaoHoaDon(String maDDB, String maHD);
    void capNhatBanTruocGioKhachDen();
    void capNhatBanSauGioKhachDen();
}
