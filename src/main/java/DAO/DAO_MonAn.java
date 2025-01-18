package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.LoaiMonAn;
import model.MonAn;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DAO_MonAn {
    private EntityManager em;

    public boolean addMonAn(MonAn monAn){
        EntityTransaction tr =  em.getTransaction();
        try {
            tr.begin();
            em.persist(monAn);
            tr.commit();
            return true;
        }
        catch (Exception e){
            tr.rollback();
            return false;
        }
    }
    public boolean updateMonAn(MonAn monAn){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
                em.merge(monAn);
            tr.commit();
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
        }
        return true;
    }

    public boolean deleteMonAn(String maMon){
        EntityTransaction tr = em.getTransaction();
        try{
         tr.begin();
         MonAn monAn = em.find(MonAn.class,maMon);
         em.remove(monAn);
         tr.commit();

         return true;
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
            return false;
        }
    }

    public MonAn findByID(String maMon){
        return em.find(MonAn.class,maMon);
    }


    public List<MonAn> getAllMonAn(){
        List<MonAn> listMonAn = new ArrayList<>();
        String query1 = "FROM MonAn m";
        String query2 = "SELECT LM FROM LoaiMonAn LM WHERE LM.maLoaiMon = :id";
        listMonAn = em.createQuery(query1,MonAn.class).getResultList();
        listMonAn.stream()
                .map(monAn -> {
                    LoaiMonAn loaiMonAn = em.createQuery(query2,LoaiMonAn.class)
                            .setParameter("id",monAn.getLoaiMonAn().getMaLoaiMon())
                            .getSingleResult();
                    monAn.setLoaiMonAn(loaiMonAn);
                    return monAn;
                }).toList();

        return listMonAn;
    }




}
