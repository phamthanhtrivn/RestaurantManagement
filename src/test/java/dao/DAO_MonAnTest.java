package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.MonAn;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static util.DataGenerator.generateMonAn;

public class DAO_MonAnTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();
    DAO_MonAn dao_monAn = new DAO_MonAn(em);
    @Test
    void addMonAn(){
        MonAn monAn = generateMonAn();
        dao_monAn.addMonAn(monAn);
        assertTrue(dao_monAn.addMonAn(monAn));
    }

    @Test
    void updateMonAn(){
        MonAn monAn = generateMonAn();
        dao_monAn.addMonAn(monAn);
        monAn.setTenMA("new name");
        assertTrue(dao_monAn.updateMonAn(monAn));
    }

    @Test
    void deleteMonAn(){
        MonAn monAn = generateMonAn();
        dao_monAn.addMonAn(monAn);
        assertTrue(dao_monAn.deleteMonAn(monAn.getMaMA()));
    }
    @Test
    void findByID(){
        MonAn monAn = generateMonAn();
        dao_monAn.addMonAn(monAn);
        assertNotNull(dao_monAn.findByID(monAn.getMaMA()));
    }

    @Test
    void getAllMonAn(){
        MonAn monAn = generateMonAn();
        dao_monAn.addMonAn(monAn);
        assertNotNull(dao_monAn.getAllMonAn());
    }
}
