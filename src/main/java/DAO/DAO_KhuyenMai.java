package DAO;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.KhuyenMai;

import java.util.List;

@AllArgsConstructor
public class DAO_KhuyenMai {
    private EntityManager em;

    public boolean addKhuyenMai(KhuyenMai khuyenMai){
        EntityTransaction tr = em.getTransaction();
        try{
          tr.begin();
          em.persist(khuyenMai);
          tr.commit();
          return true;
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
            return false;
        }
    }
    public boolean updateKhuyenMai(KhuyenMai khuyenMai){
        EntityTransaction tr = em.getTransaction();
        try{
         tr.begin();
         em.merge(khuyenMai);
         tr.commit();
         return true;
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
            return false;
        }
    }
    public boolean deleteKhuyenMai(String maKhuyenMai){
        EntityTransaction tr = em.getTransaction();
        try{
          KhuyenMai km = em.find(KhuyenMai.class,maKhuyenMai);
          tr.begin();
          em.remove(km);
          tr.commit();
          return true;
        }
        catch (Exception e){
            e.printStackTrace();
            tr.rollback();
            return false;
        }
    }
    public KhuyenMai findByID(String maKhuyenMai){
        return em.find(KhuyenMai.class,maKhuyenMai);
    }
    public List<KhuyenMai> getAll(){
        return em.createQuery("from KhuyenMai",KhuyenMai.class).getResultList();
    }




}
