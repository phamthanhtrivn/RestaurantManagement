package main;

import dao.ChiTietDatBanDAO;
import dao.ChiTietHoaDonDAO;
import dao.HoaDonDAO;
import dao.MonAnDAO;
import dao.impl.ChiTietDatBanDAOImpl;
import dao.impl.ChiTietHoaDonDAOImpl;
import dao.impl.HoaDonDAOImpl;
import dao.impl.MonAnDAOImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.ChiTietDatBan;
import model.ChiTietHoaDon;
import model.ChiTietHoaDon.ChiTietHoaDonId;
import model.HoaDon;
import model.MonAn;

public class Runner {

    public static void main(String[] args) {
    HoaDonDAO dao_hoadon = new HoaDonDAOImpl(HoaDon.class);
    MonAnDAO dao_monan = new MonAnDAOImpl(MonAn.class);
    ChiTietHoaDonDAO dao_cthd = new ChiTietHoaDonDAOImpl(ChiTietHoaDon.class);

    boolean kq =   dao_cthd.delete(new ChiTietHoaDonId(new HoaDon("HD250422001"), new MonAn("MA009")));
    System.out.println(kq);
    }
}
