package dao;

import util.DataGenerator;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.Ban;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

class DAO_BanTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

    @Test
    void addBan() {
        DAO_Ban dao_ban = new DAO_Ban(em);
        Ban ban = DataGenerator.generateBan();
        assertTrue(dao_ban.addBan(ban));
    }

    @Test
    void updateBan() {
        DAO_Ban dao_ban = new DAO_Ban(em);
        Ban ban = DataGenerator.generateBan();
        dao_ban.addBan(ban);
        ban.setSoBan(100);
        assertTrue(dao_ban.updateBan(ban));
    }

    @Test
    void deleteBan() {
        DAO_Ban dao_ban = new DAO_Ban(em);
        Ban ban = DataGenerator.generateBan();
        dao_ban.addBan(ban);
        assertTrue(dao_ban.deleteBan(ban.getMaBan()));
    }

    @Test
    void getBanById() {
        DAO_Ban dao_ban = new DAO_Ban(em);
        Ban ban = DataGenerator.generateBan();
        dao_ban.addBan(ban);
        assertNotNull(dao_ban.findByID(ban.getMaBan()));
    }

    @Test
    void getAllBan() {
        DAO_Ban dao_ban = new DAO_Ban(em);
        assertNotNull(dao_ban.getAll());
    }

}