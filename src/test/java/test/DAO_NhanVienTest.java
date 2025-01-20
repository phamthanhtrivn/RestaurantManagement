package test;

import dao.DAO_NhanVien;
import util.DataGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.NhanVien;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAO_NhanVienTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

    @Test
    void addNhanVien() {
        DAO_NhanVien dao = new DAO_NhanVien(em);
        NhanVien nhanVien = DataGenerator.generateNhanVien();
        assertTrue(dao.addNhanVien(nhanVien));
    }

    @Test
    void updateNhanVien() {
        DAO_NhanVien dao = new DAO_NhanVien(em);
        NhanVien nhanVien = DataGenerator.generateNhanVien();
        dao.addNhanVien(nhanVien);
        nhanVien.setHoTenNV("new name");
        assertTrue(dao.updateNhanVien(nhanVien));
    }

    @Test
    void deleteNhanVien() {
        DAO_NhanVien dao = new DAO_NhanVien(em);
        NhanVien nhanVien = DataGenerator.generateNhanVien();
        dao.addNhanVien(nhanVien);
        assertTrue(dao.deleteNhanVien(nhanVien.getMaNV()));
    }

    @Test
    void getNVById() {
        DAO_NhanVien dao = new DAO_NhanVien(em);
        NhanVien nhanVien = DataGenerator.generateNhanVien();
        dao.addNhanVien(nhanVien);
        assertNotNull(dao.findByID(nhanVien.getMaNV()));
    }

    @Test
    void getAllNhanVien() {
        DAO_NhanVien dao = new DAO_NhanVien(em);
        assertNotNull(dao.getAll());
    }

}