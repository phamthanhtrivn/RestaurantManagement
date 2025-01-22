package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.Ban;
import model.LoaiBan;

import java.util.List;

@AllArgsConstructor
public class DAO_Ban {
    private EntityManager em;

    public boolean addBan(Ban ban){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(ban);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateBan(Ban ban){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(ban);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteBan(String maBan){
        EntityTransaction tr = em.getTransaction();
        try{
            Ban ban = em.find(Ban.class, maBan);
            if (ban == null){
                return false;
            }
            tr.begin();
            em.remove(ban);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public Ban findByID(String maBan){
        return em.find(Ban.class, maBan);
    }

    public List<Ban> getAll(){
        List<Ban> list = em.createQuery("FROM Ban", Ban.class).getResultList();
        list.stream().map(ban -> {
            LoaiBan lb = em.createQuery("FROM LoaiBan LB WHERE LB.maLB = :maLoaiBan", LoaiBan.class)
                    .setParameter("maLoaiBan", ban.getLoaiBan().getMaLB())
                    .getSingleResult();
            ban.setLoaiBan(lb);
            return ban;
        });
        return list;
    }
}
