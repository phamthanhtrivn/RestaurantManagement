package dao;

import util.DataGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.LoaiBan;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DAO_LoaiBanTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

    @Test
    void addLoaiBan() {
        DAO_LoaiBan dao = new DAO_LoaiBan(em);
        LoaiBan loaiBan = DataGenerator.generateLoaiBan();
        boolean result = dao.addLoaiBan(loaiBan);
        assertTrue(result);
    }

    @Test
    void getLBById() {
        DAO_LoaiBan dao = new DAO_LoaiBan(em);
        LoaiBan loaiBan = DataGenerator.generateLoaiBan();
        dao.addLoaiBan(loaiBan);
        LoaiBan result = dao.findByID(loaiBan.getMaLB());
        assertEquals(loaiBan, result);
    }

    @Test
    void updateLoaiBan() {
        DAO_LoaiBan dao = new DAO_LoaiBan(em);
        LoaiBan loaiBan = DataGenerator.generateLoaiBan();
        dao.addLoaiBan(loaiBan);
        loaiBan.setTenLB("Loai ban moi");
        boolean result = dao.updateLoaiBan(loaiBan);
        assertTrue(result);
    }

    @Test
    void deleteLoaiBan() {
        DAO_LoaiBan dao = new DAO_LoaiBan(em);
        LoaiBan loaiBan = DataGenerator.generateLoaiBan();
        dao.addLoaiBan(loaiBan);
        boolean result = dao.deleteLoaiBan(loaiBan.getMaLB());
        assertTrue(result);
    }

    @Test
    void getAllLoaiBan() {
        DAO_LoaiBan dao = new DAO_LoaiBan(em);
        assertNotNull(dao.getAll());
    }

}