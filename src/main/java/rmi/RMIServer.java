/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package rmi;

import dao.BanDAO;
import dao.ChiTietDatBanDAO;
import dao.ChiTietHoaDonDAO;
import dao.DonDatBanDAO;
import dao.HoaDonDAO;
import dao.KhachHangDAO;
import dao.KhuyenMaiDAO;
import dao.LoaiBanDAO;
import dao.LoaiKhachHangDAO;
import dao.LoaiMonAnDAO;
import dao.LoaiNhanVienDAO;
import dao.MonAnDAO;
import dao.NhanVienDAO;
import dao.impl.BanDAOImpl;
import dao.impl.ChiTietDatBanDAOImpl;
import dao.impl.ChiTietHoaDonDAOImpl;
import dao.impl.DonDatBanDAOImpl;
import dao.impl.HoaDonDAOImpl;
import dao.impl.KhachHangDAOImpl;
import dao.impl.KhuyenMaiDAOImpl;
import dao.impl.LoaiBanDAOImpl;
import dao.impl.LoaiKhachHangDAOImpl;
import dao.impl.LoaiMonAnDAOImpl;
import dao.impl.LoaiNhanVienDAOImpl;
import dao.impl.MonAnDAOImpl;
import dao.impl.NhanVienDAOImpl;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import model.Ban;
import model.ChiTietDatBan;
import model.ChiTietHoaDon;
import model.DonDatBan;
import model.HoaDon;
import model.KhachHang;
import model.KhuyenMai;
import model.LoaiBan;
import model.LoaiKhachHang;
import model.LoaiMonAn;
import model.LoaiNhanVien;
import model.MonAn;
import model.NhanVien;
import service.BanService;
import service.ChiTietDatBanService;
import service.ChiTietHoaDonService;
import service.DonDatBanService;
import service.HoaDonService;
import service.KhachHangService;
import service.KhuyenMaiService;
import service.LoaiBanService;
import service.LoaiKhachHangService;
import service.LoaiMonAnService;
import service.LoaiNhanVienService;
import service.MonAnService;
import service.impl.BanServiceImpl;
import service.impl.ChiTietDatBanServiceImpl;
import service.impl.ChiTietHoaDonServiceImpl;
import service.impl.DonDatBanServiceImpl;
import service.impl.HoaDonServiceImpl;
import service.impl.KhachHangServiceImpl;
import service.impl.KhuyenMaiServiceImpl;
import service.impl.LoaiBanServiceImpl;
import service.impl.LoaiKhachHangServiceImpl;
import service.impl.LoaiMonAnServiceImpl;
import service.impl.LoaiNhanVienServiceImpl;
import service.impl.MonAnServiceImpl;

/**
 *
 * @author THANHTRI
 */
public class RMIServer {
    
    public static void main(String[] args) throws NamingException, RemoteException {
        Context context = new InitialContext();
        
        LocateRegistry.createRegistry(7551);
        
        BanDAO banDAO = new BanDAOImpl(Ban.class);
        ChiTietDatBanDAO chiTietDatBanDAO = new ChiTietDatBanDAOImpl(ChiTietDatBan.class);
        ChiTietHoaDonDAO chiTietHoaDonDAO = new ChiTietHoaDonDAOImpl(ChiTietHoaDon.class);
        DonDatBanDAO donDatBanDAO = new DonDatBanDAOImpl(DonDatBan.class);
        HoaDonDAO hoaDonDAO = new HoaDonDAOImpl(HoaDon.class);
        KhachHangDAO khachHangDAO = new KhachHangDAOImpl(KhachHang.class);
        KhuyenMaiDAO khuyenMaiDAO = new KhuyenMaiDAOImpl(KhuyenMai.class);
        LoaiBanDAO loaiBanDAO = new LoaiBanDAOImpl(LoaiBan.class);
        LoaiKhachHangDAO loaiKhachHangDAO = new LoaiKhachHangDAOImpl(LoaiKhachHang.class);
        LoaiMonAnDAO loaiMonAnDAO = new LoaiMonAnDAOImpl(LoaiMonAn.class);
        LoaiNhanVienDAO loaiNhanVienDAO = new LoaiNhanVienDAOImpl(LoaiNhanVien.class);
        NhanVienDAO nhanVienDAO = new NhanVienDAOImpl(NhanVien.class);
        MonAnDAO monAnDAO = new MonAnDAOImpl(MonAn.class);
        
        BanService banService = new BanServiceImpl(banDAO);
        ChiTietDatBanService chiTietDatBanService = new ChiTietDatBanServiceImpl(chiTietDatBanDAO);
        ChiTietHoaDonService chiTietHoaDonService = new ChiTietHoaDonServiceImpl(chiTietHoaDonDAO);
        DonDatBanService donDatBanService = new DonDatBanServiceImpl(donDatBanDAO);
        HoaDonService hoaDonService = new HoaDonServiceImpl(hoaDonDAO);
        KhachHangService khachHangService = new KhachHangServiceImpl(khachHangDAO);
        KhuyenMaiService khuyenMaiService = new KhuyenMaiServiceImpl(khuyenMaiDAO);
        LoaiBanService loaiBanService = new LoaiBanServiceImpl(loaiBanDAO);
        LoaiKhachHangService loaiKhachHangService = new LoaiKhachHangServiceImpl(loaiKhachHangDAO);
        LoaiMonAnService loaiMonAnService = new LoaiMonAnServiceImpl(loaiMonAnDAO);
        LoaiNhanVienService loaiNhanVienService = new LoaiNhanVienServiceImpl(loaiNhanVienDAO);
        MonAnService monAnService = new MonAnServiceImpl(monAnDAO);
        
        context.bind("rmi://localhost:7551/banService", banService);
        context.bind("rmi://localhost:7551/chiTietDatBanService", chiTietDatBanService);
        context.bind("rmi://localhost:7551/chiTietHoaDonService", chiTietHoaDonService);
        context.bind("rmi://localhost:7551/donDatBanService", donDatBanService);
        context.bind("rmi://localhost:7551/hoaDonService", hoaDonService);
        context.bind("rmi://localhost:7551/khachHangService", khachHangService);
        context.bind("rmi://localhost:7551/khuyenMaiService", khuyenMaiService);
        context.bind("rmi://localhost:7551/loaiBanService", loaiBanService);
        context.bind("rmi://localhost:7551/loaiKhachHangService", loaiKhachHangService);
        context.bind("rmi://localhost:7551/loaiMonAnService", loaiMonAnService);
        context.bind("rmi://localhost:7551/loaiNhanVienService", loaiNhanVienService);
        context.bind("rmi://localhost:7551/monAnService", monAnService);
        
        System.out.println("Server RMI is running...");
        
    }
}
