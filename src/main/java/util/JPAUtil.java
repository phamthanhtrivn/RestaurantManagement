/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 *
 * @author THANHTRI
 */
public class JPAUtil {
    private static EntityManagerFactory emf;
    
    static {
        emf = Persistence.createEntityManagerFactory("maria-pu");
    }
    
    public static EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
}
