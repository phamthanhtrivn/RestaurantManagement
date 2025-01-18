package main;

import DAO.DAO_KhachHang;
import DAO.DAO_LoaiKhachHang;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

import model.KhachHang;
import model.LoaiKhachHang;
import net.datafaker.Faker;

public class Runner {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

        EntityTransaction tr = em.getTransaction();

    }
}
