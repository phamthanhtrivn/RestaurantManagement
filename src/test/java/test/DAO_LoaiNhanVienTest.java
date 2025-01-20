package test;

import dao.DAO_LoaiNhanVien;
import util.DataGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.LoaiNhanVien;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DAO_LoaiNhanVienTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

    @Test
    void addLoaiNhanVien() {
        DAO_LoaiNhanVien dao = new DAO_LoaiNhanVien(em);
        LoaiNhanVien loaiNhanVien = DataGenerator.generateLoaiNhanVien();
        boolean result = dao.addLoaiNhanVien(loaiNhanVien);
        assertTrue(dao.addLoaiNhanVien(loaiNhanVien));
    }

    @Test
    void updateLoaiNhanVien() {
        DAO_LoaiNhanVien dao = new DAO_LoaiNhanVien(em);
        LoaiNhanVien loaiNhanVien = DataGenerator.generateLoaiNhanVien();
        dao.addLoaiNhanVien(loaiNhanVien);
        loaiNhanVien.setViTri("new position");
        assertTrue(dao.updateLoaiNhanVien(loaiNhanVien));
    }

    @Test
    void deleteLoaiNhanVien() {
        DAO_LoaiNhanVien dao = new DAO_LoaiNhanVien(em);
        LoaiNhanVien loaiNhanVien = DataGenerator.generateLoaiNhanVien();
        dao.addLoaiNhanVien(loaiNhanVien);
        assertTrue(dao.deleteLoaiNhanVien(loaiNhanVien.getMaLoaiNV()));
    }

    @Test
    void getLNVById() {
        DAO_LoaiNhanVien dao = new DAO_LoaiNhanVien(em);
        LoaiNhanVien loaiNhanVien = DataGenerator.generateLoaiNhanVien();
        dao.addLoaiNhanVien(loaiNhanVien);
        assertNotNull(dao.findByID(loaiNhanVien.getMaLoaiNV()));
    }

    @Test
    void getAllLoaiNhanVien() {
        DAO_LoaiNhanVien dao = new DAO_LoaiNhanVien(em);
        assertNotNull(dao.getAll());
    }
}