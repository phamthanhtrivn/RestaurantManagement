package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.KhachHang;
import model.LoaiKhachHang;

import java.util.ArrayList;
import java.util.List;

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

    public List<KhachHang> getAllKhachHang() {
        List<KhachHang> listKhachHang = new ArrayList<>();
        String query1 = "FROM KhachHang k";
        String query2 = "SELECT LK FROM LoaiKhachHang LK WHERE LK.maLoaiKH = :id";
        listKhachHang = em.createQuery(query1, KhachHang.class).getResultList();
        listKhachHang.stream()
                .map(khachHang -> {
                    LoaiKhachHang loaiKhachHang = em.createQuery(query2, LoaiKhachHang.class)
                            .setParameter("id", khachHang.getLoaiKhachHang().getMaLoaiKH())
                            .getSingleResult();
                    khachHang.setLoaiKhachHang(loaiKhachHang);
                    return khachHang;
                }).toList();

        return listKhachHang;
    }

}
