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
        EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();
        
    }
}
