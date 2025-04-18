package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.LoaiBan;

import java.util.List;

@AllArgsConstructor
public class DAO_LoaiBan {
    private EntityManager em;

    public boolean addLoaiBan(LoaiBan loaiBan){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(loaiBan);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLoaiBan(LoaiBan loaiBan){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(loaiBan);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLoaiBan(String maLoaiBan){
        EntityTransaction tr = em.getTransaction();
        try{
            LoaiBan loaiBan = em.find(LoaiBan.class, maLoaiBan);
            if (loaiBan == null){
                return false;
            }
            tr.begin();
            em.remove(loaiBan);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public LoaiBan findByID(String maLoaiBan){
        return em.find(LoaiBan.class, maLoaiBan);
    }

    public List<LoaiBan> getAll(){
        return em.createQuery("FROM LoaiBan", LoaiBan.class).getResultList();
    }
}
