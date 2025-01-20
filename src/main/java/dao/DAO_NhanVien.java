package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.AllArgsConstructor;
import model.LoaiNhanVien;
import model.NhanVien;

import java.util.List;

@AllArgsConstructor
public class DAO_NhanVien {
    private EntityManager em;

    public boolean addNhanVien(NhanVien nhanVien){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.persist(nhanVien);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateNhanVien(NhanVien nhanVien){
        EntityTransaction tr = em.getTransaction();
        try{
            tr.begin();
            em.merge(nhanVien);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteNhanVien(String maNhanVien){
        EntityTransaction tr = em.getTransaction();
        try{
            NhanVien nhanVien = em.find(NhanVien.class, maNhanVien);
            if (nhanVien == null){
                return false;
            }
            tr.begin();
            em.remove(nhanVien);
            tr.commit();
            return true;
        } catch (Exception e){
            tr.rollback();
            e.printStackTrace();
            return false;
        }
    }

    public NhanVien findByID(String maNhanVien) {
        return em.find(NhanVien.class, maNhanVien);
    }

    public List<NhanVien> getAll() {
        List<NhanVien> list = em.createQuery("FROM NhanVien", NhanVien.class).getResultList();
        list.stream().map(nv -> {
            LoaiNhanVien lnv = em.createQuery("FROM LoaiNhanVien WHERE maLoaiNV = :maLoaiNV", LoaiNhanVien.class)
                    .setParameter("maLoaiNV", nv.getLoaiNhanVien().getMaLoaiNV()).getSingleResult();
            nv.setLoaiNhanVien(lnv);
            return nv;
        });
        return list;
    }
}
