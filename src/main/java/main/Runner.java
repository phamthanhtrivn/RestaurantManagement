package main;

import DAO.DAO_LoaiMonAn;
import DAO.DAO_MonAn;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;
import model.LoaiMonAn;
import model.MonAn;
import net.datafaker.Faker;

import java.util.List;
import java.util.Random;

public class Runner {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();
        EntityTransaction tr = em.getTransaction();
        DAO_MonAn dao_monAn = new DAO_MonAn(em);


    }
}
