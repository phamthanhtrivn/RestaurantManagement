package main;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Persistence;

public class Runner {

    public static void main(String[] args) {
        EntityManager em = Persistence.createEntityManagerFactory("maria-pu").createEntityManager();

        EntityTransaction tr = em.getTransaction();
    }
}
