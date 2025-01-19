package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import model.CaLamViec;

import java.util.List;

public class DAO_CaLamViec {
    private EntityManager em;

    public boolean addCaLamViec(CaLamViec caLamViec){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(caLamViec);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateCaLamViec(CaLamViec caLamViec){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(caLamViec);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteCaLamViec(String maCaLamViec){
        EntityTransaction tr = em.getTransaction();
        try{
            CaLamViec caLamViec = em.find(CaLamViec.class, maCaLamViec);
            if (caLamViec == null){
                return false;
            }
            tr.begin();
            em.remove(caLamViec);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public CaLamViec findByID(String maCaLamViec){
        return em.find(CaLamViec.class, maCaLamViec);
    }

    public List<CaLamViec> getAll(){
        return em.createQuery("from CaLamViec").getResultList();
    }
}
