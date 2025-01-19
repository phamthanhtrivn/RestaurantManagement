package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.LoaiKhachHang;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
public class DAO_LoaiKhachHang {
    private EntityManager em;

    public boolean addLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.persist(loaiKhachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateLoaiKhachHang(LoaiKhachHang loaiKhachHang) {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            em.merge(loaiKhachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteLoaiKhachHang(String maLoaiKH) {
        EntityTransaction tr = em.getTransaction();
        try {
            LoaiKhachHang loaiKhachHang = em.find(LoaiKhachHang.class, maLoaiKH);
            if (loaiKhachHang == null) {
                return false;
            }
            tr.begin();
            em.remove(loaiKhachHang);
            tr.commit();
            return true;
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public LoaiKhachHang findByID(String maLoaiKH) {
        return em.find(LoaiKhachHang.class, maLoaiKH);
    }

    public ArrayList<LoaiKhachHang> getAllLKH() {
        return (ArrayList<LoaiKhachHang>) em.createQuery("FROM LoaiKhachHang", LoaiKhachHang.class).getResultList();
    }
}
