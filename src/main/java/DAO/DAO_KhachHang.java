package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.KhachHang;
import java.util.ArrayList;

@AllArgsConstructor
public class DAO_KhachHang {
    private EntityManager em;



    public boolean addKhachHang(KhachHang khachHang){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(khachHang);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateKhachHang(KhachHang khachHang){
        EntityTransaction tr =em.getTransaction();
        try {
            tr.begin();
            em.merge(khachHang);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteKhachHang(String maKH) {
        EntityTransaction tr = em.getTransaction();
        try {
            KhachHang kh = em.find(KhachHang.class, maKH);
            if (kh == null) {
                return false;
            }
            tr.begin();
            em.remove(kh);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public KhachHang findByID(String maKH) {
        return em.find(KhachHang.class, maKH);
    }

    public ArrayList<KhachHang> getAllKhachHang() {
        return (ArrayList<KhachHang>) em.createQuery("FROM KhachHang", KhachHang.class).getResultList();
    }

}
