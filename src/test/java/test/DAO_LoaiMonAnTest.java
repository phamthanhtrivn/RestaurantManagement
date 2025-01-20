package test;

import dao.DAO_LoaiMonAn;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Persistence;
import model.LoaiMonAn;
import org.junit.jupiter.api.Test;
import util.DataGenerator;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DAO_LoaiMonAnTest {
    private EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();
    DAO_LoaiMonAn dao_loaiMonAn = new DAO_LoaiMonAn(em);

    @Test
    void addLoaiMonAn(){
        LoaiMonAn loaiMonAn = DataGenerator.generateLoaiMonAn();
        assertTrue(dao_loaiMonAn.addLoaiMonAn(loaiMonAn));
    }

    @Test
    void updateLoaiMonAn(){
        LoaiMonAn loaiMonAn = DataGenerator.generateLoaiMonAn();
        dao_loaiMonAn.addLoaiMonAn(loaiMonAn);
        loaiMonAn.setTenLoaiMon("new name");
        assertTrue(dao_loaiMonAn.updateLoaiMonAn(loaiMonAn));
    }


    @Test
    void deleteLoaiMonAn(){
        LoaiMonAn loaiMonAn = DataGenerator.generateLoaiMonAn();
        dao_loaiMonAn.addLoaiMonAn(loaiMonAn);
        assertTrue(dao_loaiMonAn.deleteLoaiMonAn(loaiMonAn.getMaLoaiMon()));
    }



    @Test
    void findByID(){
        LoaiMonAn loaiMonAn = DataGenerator.generateLoaiMonAn();
        dao_loaiMonAn.addLoaiMonAn(loaiMonAn);
        assertNotNull(dao_loaiMonAn.findByID(loaiMonAn.getMaLoaiMon()));
    }


    @Test
    void getAllLoaiMonAn(){
        LoaiMonAn loaiMonAn = DataGenerator.generateLoaiMonAn();
        dao_loaiMonAn.addLoaiMonAn(loaiMonAn);
        assertTrue(dao_loaiMonAn.getAll() != null);
    }
}
