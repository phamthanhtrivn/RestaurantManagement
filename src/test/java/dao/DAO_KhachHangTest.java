package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.KhachHang;
import model.LoaiKhachHang;
import org.junit.jupiter.api.Test;
import util.DataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class DAO_KhachHangTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();
    @Test
    void addKhachHang() {
        DAO_KhachHang dao = new DAO_KhachHang(em);
        var loaiKhachHang = em.createQuery("SELECT l FROM LoaiKhachHang l", LoaiKhachHang.class)
                .setMaxResults(1) // Giới hạn kết quả để chỉ lấy một loại
                .getSingleResult();

        KhachHang khachHang = DataGenerator.generateKhachHang();
        khachHang.setLoaiKH(loaiKhachHang);

        boolean result = dao.addKhachHang(khachHang);
        assertTrue(result);
    }

    @Test
    void updateKhachHang() {
        DAO_KhachHang dao = new DAO_KhachHang(em);

        LoaiKhachHang loaiKhachHang = em.createQuery("SELECT l FROM LoaiKhachHang l", LoaiKhachHang.class)
                .setMaxResults(1)
                .getSingleResult();
        if (loaiKhachHang == null) {
            loaiKhachHang = new LoaiKhachHang();
            loaiKhachHang.setTenLoaiKH("Default Type");
            em.getTransaction().begin();
            em.persist(loaiKhachHang);
            em.getTransaction().commit();
        }

        KhachHang khachHang = DataGenerator.generateKhachHang();
        khachHang.setLoaiKH(loaiKhachHang);

        dao.addKhachHang(khachHang);

        khachHang.setTenKH("new customer name");
        assertTrue(dao.updateKhachHang(khachHang));
    }

    @Test
    void deleteKhachHang() {
        DAO_KhachHang dao = new DAO_KhachHang(em);

        var khachHang = em.createQuery("SELECT kh FROM KhachHang kh", KhachHang.class)
                .setMaxResults(1)
                .getSingleResult();

        boolean result = dao.deleteKhachHang(khachHang.getMaKH());
        assertTrue(result);

        assertNull(dao.findByID(khachHang.getMaKH()));
    }



    @Test
    void findByID() {
        DAO_KhachHang dao = new DAO_KhachHang(em);
        KhachHang khachHang = DataGenerator.generateKhachHang();
        boolean result = dao.addKhachHang(khachHang);
        assertNotNull(dao.findByID(khachHang.getMaKH()));
    }

    @Test
    void getAllKhachHang() {
        DAO_KhachHang dao = new DAO_KhachHang(em);
        assertNotNull(dao.getAllKhachHang());
    }
}