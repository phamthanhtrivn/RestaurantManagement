/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import model.DonDatBan;

/**
 *
 * @author THANHTRI
 */
public interface DonDatBanService extends GenericService<DonDatBan, String> {

    boolean checkTimeBan(String maBan, LocalDateTime gioHen) throws RemoteException;

    int findSoThuTuHomNay() throws RemoteException;

    List<DonDatBan> timKiemCapNhat(LocalDate date, String phone) throws RemoteException;

    boolean huyDonDatBan(String ma, double tienHoan, LocalDateTime gioHuy) throws RemoteException;

    Object[] getThongTinDonDatBan(String maDDB) throws RemoteException;

    List<Integer> loadNam() throws RemoteException;

    List<DonDatBan> thongKeDonDatBan(String type, Map<String, String> params) throws RemoteException;

    boolean capNhatTTDDBDaNhanVaTaoHoaDon(String maDDB, String maHD) throws RemoteException;

    void capNhatBanTruocGioKhachDen() throws RemoteException;

    void capNhatBanSauGioKhachDen() throws RemoteException;

    public Object timKiemDonDatBanMa(String maDonDatBan, String ngayDB, String ngayKT) throws RemoteException;

    public List<Object[]> timKiemDonDatBanName(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException;

    public List<Object[]> timKiemDonDatBanPhone(String phone, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException;

    public List<Object[]> donDatBanTrongNgay() throws RemoteException;

    List<Object[]> getChiTietDonDatBan(String maDonDatBan) throws RemoteException;

    Object[] timDDB(String ma) throws RemoteException;

    List<Object[]> timChiTietDonDatBan(String maDonDatBan) throws RemoteException;

    DonDatBan getDDBForHD(String maDDB) throws RemoteException;
}
