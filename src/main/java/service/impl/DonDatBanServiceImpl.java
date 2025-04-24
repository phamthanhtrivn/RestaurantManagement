/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.DonDatBanDAO;
import dao.GenericDAO;
import java.rmi.RemoteException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import model.DonDatBan;
import service.DonDatBanService;

/**
 *
 * @author THANHTRI
 */
public class DonDatBanServiceImpl extends GenericServiceImpl<DonDatBan, String> implements DonDatBanService {
    private DonDatBanDAO donDatBanDAO;
    
    public DonDatBanServiceImpl(DonDatBanDAO donDatBanDAO) throws RemoteException {
        super(donDatBanDAO);
        this.donDatBanDAO = donDatBanDAO;
    }

    @Override
    public boolean checkTimeBan(String maBan, LocalDateTime gioHen) throws RemoteException {
        return donDatBanDAO.checkTimeBan(maBan, gioHen);
    }

    @Override
    public int findSoThuTuHomNay() throws RemoteException {
        return donDatBanDAO.findSoThuTuHomNay();
    }

    @Override
    public List<DonDatBan> timKiemCapNhat(LocalDate date, String phone) throws RemoteException {
        return donDatBanDAO.timKiemCapNhat(date, phone);
    }

    @Override
    public boolean huyDonDatBan(String ma, double tienHoan, LocalDateTime gioHuy) throws RemoteException {
        return donDatBanDAO.huyDonDatBan(ma, tienHoan, gioHuy);
    }

    @Override
    public Object[] getThongTinDonDatBan(String maDDB) throws RemoteException {
        return donDatBanDAO.getThongTinDonDatBan(maDDB);
    }

    @Override
    public List<Integer> loadNam() throws RemoteException {
        return donDatBanDAO.loadNam();
    }

    @Override
    public List<DonDatBan> thongKeDonDatBan(String type, Map<String, String> params) throws RemoteException {
        return donDatBanDAO.thongKeDonDatBan(type, params);
    }

    @Override
    public boolean capNhatTTDDBDaNhanVaTaoHoaDon(String maDDB, String maHD) throws RemoteException {
        return donDatBanDAO.capNhatTTDDBDaNhanVaTaoHoaDon(maDDB, maHD);
    }

    @Override
    public void capNhatBanTruocGioKhachDen() throws RemoteException {
        donDatBanDAO.capNhatBanTruocGioKhachDen();
    }

    @Override
    public void capNhatBanSauGioKhachDen() throws RemoteException {
        donDatBanDAO.capNhatBanSauGioKhachDen();
    }

    @Override
    public Object timKiemDonDatBanMa(String maDonDatBan, String ngayDB, String ngayKT) throws RemoteException {
        return donDatBanDAO.timKiemDonDatBanMa(maDonDatBan, ngayDB, ngayKT);
    }

    @Override
    public List<Object[]> timKiemDonDatBanName(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException {
        return donDatBanDAO.timKiemDonDatBanName(name, loaiDon, sortKey, sortValue, ngayBD, ngayKT);
    }

    @Override
    public List<Object[]> timKiemDonDatBanPhone(String phone, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException {
        return donDatBanDAO.timKiemDonDatBanPhone(phone, loaiDon, sortKey, sortValue, ngayBD, ngayKT);
    }

    @Override
    public List<Object[]> donDatBanTrongNgay() throws RemoteException {
        return donDatBanDAO.donDatBanTrongNgay();
    }

    @Override
    public List<Object[]> getChiTietDonDatBan(String maDonDatBan) throws RemoteException {
        return donDatBanDAO.getChiTietDonDatBan(maDonDatBan);
    }

    @Override
    public Object[] timDDB(String ma) throws RemoteException {
        return donDatBanDAO.timDDB(ma);
    }

    @Override
    public List<Object[]> timChiTietDonDatBan(String maDonDatBan) throws RemoteException {
        return donDatBanDAO.timChiTietDonDatBan(maDonDatBan);
    }

    @Override
    public DonDatBan getDDBForHD(String maDDB) throws RemoteException {
        return donDatBanDAO.getDDBForHD(maDDB);
    }
    
}
