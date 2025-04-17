package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import org.junit.jupiter.api.Test;
import util.DataGenerator;

import static org.junit.jupiter.api.Assertions.*;

class DAO_CaLamViecTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

    @Test
    void addCaLamViec() {
        DAO_CaLamViec dao = new DAO_CaLamViec(em);
        CaLamViec caLamViec = DataGenerator.generateCaLamViec();
        assertTrue(dao.addCaLamViec(caLamViec));
    }

    @Test
    void updateCaLamViec() {
        DAO_CaLamViec dao = new DAO_CaLamViec(em);
        CaLamViec caLamViec = DataGenerator.generateCaLamViec();
        dao.addCaLamViec(caLamViec);
        caLamViec.setTenCa("Ca 2");
        assertTrue(dao.updateCaLamViec(caLamViec));
    }

    @Test
    void deleteCaLamViec() {
        DAO_CaLamViec dao = new DAO_CaLamViec(em);
        CaLamViec caLamViec = DataGenerator.generateCaLamViec();
        dao.addCaLamViec(caLamViec);
        assertTrue(dao.deleteCaLamViec(caLamViec.getMaCa()));
    }

    @Test
    void getCaLamViecById() {
        DAO_CaLamViec dao = new DAO_CaLamViec(em);
        CaLamViec caLamViec = DataGenerator.generateCaLamViec();
        dao.addCaLamViec(caLamViec);
        assertNotNull(dao.findByID(caLamViec.getMaCa()));
    }

    @Test
    void findAll() {
        DAO_CaLamViec dao = new DAO_CaLamViec(em);
        assertNotNull(dao.getAll());
    }
}