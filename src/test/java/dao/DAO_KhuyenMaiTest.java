package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.KhuyenMai;
import org.junit.jupiter.api.Test;
import util.DataGenerator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAO_KhuyenMaiTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();
    private DAO_KhuyenMai dao_khuyenMai = new DAO_KhuyenMai(em);
    @Test
    void addKhuyenMai() {
        KhuyenMai km = DataGenerator.generateKhuyenMai();
        assertTrue(dao_khuyenMai.addKhuyenMai(km));
    }

    @Test
    void updateKhuyenMai(){
        KhuyenMai km = DataGenerator.generateKhuyenMai();
        dao_khuyenMai.addKhuyenMai(km);
        km.setGiamGia(50);
        assertTrue(dao_khuyenMai.updateKhuyenMai(km));
    }

    @Test
    void deleteKhuyenMai(){
        KhuyenMai km = DataGenerator.generateKhuyenMai();
        dao_khuyenMai.addKhuyenMai(km);
        assertTrue(dao_khuyenMai.deleteKhuyenMai(km.getMaKM()));
    }

    @Test
    void findByID(){
        KhuyenMai km = DataGenerator.generateKhuyenMai();
        dao_khuyenMai.addKhuyenMai(km);
        assertNotNull(dao_khuyenMai.findByID(km.getMaKM()));
    }

    void getAll(){
        assertNotNull(dao_khuyenMai.getAll());
    }




}
