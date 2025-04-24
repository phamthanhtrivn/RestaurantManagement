/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.impl;

import dao.HoaDonDAO;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;
import model.Ban;
import model.HoaDon;
import service.HoaDonService;

/**
 *
 * @author THANHTRI
 */
public class HoaDonServiceImpl extends GenericServiceImpl<HoaDon, String> implements HoaDonService {
    private HoaDonDAO hoaDonDAO;
    
    public HoaDonServiceImpl(HoaDonDAO hoaDonDAO) throws RemoteException {
        super(hoaDonDAO);
        this.hoaDonDAO = hoaDonDAO;
    }

    @Override
    public List<Integer> loadNam() throws RemoteException {
        return hoaDonDAO.loadNam();
    }

    @Override
    public List<HoaDon> thongKeHoaDon(String type, Map<String, String> params) throws RemoteException {
        return hoaDonDAO.thongKeHoaDon(type, params);
    }

    @Override
    public List<Object[]> thongKeMon(String type, Map<String, String> params) throws RemoteException {
        return hoaDonDAO.thongKeMon(type, params);
    }

    @Override
    public String createMaHD() throws RemoteException {
        return hoaDonDAO.createMaHD();
    }

    @Override
    public List<Object[]> hoaDonTrongNgay() throws RemoteException {
        return hoaDonDAO.hoaDonTrongNgay();
    }

    @Override
    public Object[] timKiemHoaDonTheoMa(String maHoaDon, String ngayDB, String ngayKT) throws RemoteException {
        return hoaDonDAO.timKiemHoaDonTheoMa(maHoaDon, ngayDB, ngayKT);
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoTenKH(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException {
        return hoaDonDAO.timKiemHoaDonTheoTenKH(name, loaiDon, sortKey, sortValue, ngayBD, ngayKT);
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoSTD(String soDienThoai, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException {
        return hoaDonDAO.timKiemHoaDonTheoSTD(soDienThoai, loaiDon, sortKey, sortValue, ngayBD, ngayKT);
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoSoBan(String soBan, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) throws RemoteException {
        return hoaDonDAO.timKiemHoaDonTheoSoBan(soBan, loaiDon, sortKey, sortValue, ngayBD, ngayKT);
    }

    @Override
    public Object[] timKiemHD(String maHD) throws RemoteException {
        return hoaDonDAO.timKiemHD(maHD);
    }

    @Override
    public List<Object[]> timKiemCTHD(String maHD) throws RemoteException {
        return hoaDonDAO.timKiemCTHD(maHD);
    }

    @Override
    public boolean checkBanVip(String maBan) throws RemoteException {
        return hoaDonDAO.checkBanVip(maBan);
    }

    @Override
    public HoaDon getHoaDonTheoBanHoatDong(Ban ban) throws RemoteException {
        return hoaDonDAO.getHoaDonTheoBanHoatDong(ban);
    }

    @Override
    public HoaDon getHoaDonTheoMa(String maHD) throws RemoteException {
        return hoaDonDAO.getHoaDonTheoMa(maHD);
    }
}
