package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.LoaiNhanVien;

import java.util.List;

@AllArgsConstructor
public class DAO_LoaiNhanVien {
    private EntityManager em;

    public boolean addLoaiNhanVien(LoaiNhanVien loaiNhanVien){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(loaiNhanVien);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLoaiNhanVien(LoaiNhanVien loaiNhanVien){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(loaiNhanVien);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLoaiNhanVien(String maLoaiNhanVien){
        EntityTransaction tr = em.getTransaction();
        try{
            LoaiNhanVien loaiNhanVien = em.find(LoaiNhanVien.class, maLoaiNhanVien);
            if (loaiNhanVien == null){
                return false;
            }
            tr.begin();
            em.remove(loaiNhanVien);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public LoaiNhanVien findByID(String maLoaiNhanVien){
        return em.find(LoaiNhanVien.class, maLoaiNhanVien);
    }

    public List<LoaiNhanVien> getAll(){
        return em.createQuery("FROM LoaiNhanVien", LoaiNhanVien.class).getResultList();
    }
}
