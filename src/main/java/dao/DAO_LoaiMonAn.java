package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.LoaiMonAn;

import java.util.ArrayList;

@AllArgsConstructor
public class DAO_LoaiMonAn {
    private EntityManager em;
    public boolean addLoaiMonAn(LoaiMonAn loaiMonAn){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(loaiMonAn);
            tr.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
            return false;
        }
    }

    public boolean updateLoaiMonAn(LoaiMonAn loaiMonAn){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(loaiMonAn);
            tr.commit();
            return true;
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
            return false;
        }
    }
    public boolean deleteLoaiMonAn(String maLoaiMon){
        EntityTransaction tr = em.getTransaction();
        try {
          LoaiMonAn loaiMonAn = em.find(LoaiMonAn.class,maLoaiMon);
          if (loaiMonAn == null) {
              return false;
          }
          tr.begin();
          em.remove(loaiMonAn);
          tr.commit();
          return true;
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
            return false;
        }
    }

    public LoaiMonAn findByID(String maLoaiMon){
        return em.find(LoaiMonAn.class,maLoaiMon);
    }

    public ArrayList<LoaiMonAn> getAll(){
        return (ArrayList<LoaiMonAn>) em.createQuery("From LoaiMonAn",LoaiMonAn.class).getResultList();
    }


}
