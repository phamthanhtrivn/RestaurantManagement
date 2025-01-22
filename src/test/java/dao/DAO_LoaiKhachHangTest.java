package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.LoaiKhachHang;
import org.junit.jupiter.api.Test;
import util.DataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class DAO_LoaiKhachHangTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

    @Test
    void addLoaiKhachHang() {
        DAO_LoaiKhachHang dao = new DAO_LoaiKhachHang(em);
        LoaiKhachHang loaiKhachHang = DataGenerator.generateLoaiKhachHang();
        boolean result = dao.addLoaiKhachHang(loaiKhachHang);
        assertTrue(result);
    }

    @Test
    void updateLoaiKhachHang() {
        DAO_LoaiKhachHang dao = new DAO_LoaiKhachHang(em);
        LoaiKhachHang loaiKhachHang = DataGenerator.generateLoaiKhachHang();
        dao.addLoaiKhachHang(loaiKhachHang);
        loaiKhachHang.setTenLoaiKH("Updated Category");
        assertTrue(dao.updateLoaiKhachHang(loaiKhachHang));
    }

    @Test
    void deleteLoaiKhachHang() {
        DAO_LoaiKhachHang dao = new DAO_LoaiKhachHang(em);
        LoaiKhachHang loaiKhachHang = DataGenerator.generateLoaiKhachHang();
        dao.addLoaiKhachHang(loaiKhachHang);
        assertTrue(dao.deleteLoaiKhachHang(loaiKhachHang.getMaLoaiKH()));
    }

    @Test
    void findByID() {
        DAO_LoaiKhachHang dao = new DAO_LoaiKhachHang(em);
        LoaiKhachHang loaiKhachHang = DataGenerator.generateLoaiKhachHang();
        dao.addLoaiKhachHang(loaiKhachHang);
        assertNotNull(dao.findByID(loaiKhachHang.getMaLoaiKH()));
    }

    @Test
    void getAllLKH() {
        DAO_LoaiKhachHang dao = new DAO_LoaiKhachHang(em);
        assertNotNull(dao.getAllLKH());
    }
}