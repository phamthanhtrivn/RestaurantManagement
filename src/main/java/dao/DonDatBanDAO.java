/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;
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
    
    public Object timKiemDonDatBanMa(String maDonDatBan, String ngayDB, String ngayKT);
    public List<Object[]> timKiemDonDatBanName(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT);
    public List<Object[]> timKiemDonDatBanPhone(String phone, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT);
    public List<Object[]> donDatBanTrongNgay();
    List<Object[]> getChiTietDonDatBan(String maDonDatBan);
    Object[] timDDB(String ma);
    List<Object[]> timChiTietDonDatBan(String maDonDatBan);
    DonDatBan getDDBForHD(String maDDB);
}
