/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.BanDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import java.util.List;
import model.Ban;

/**
 *
 * @author THANHTRI
 */
public class BanDAOImpl extends GenericDAOImpl<Ban, String> implements BanDAO {

    public BanDAOImpl(Class<Ban> clazz) {
        super(clazz);
    }

    public BanDAOImpl(EntityManager em, Class<Ban> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Ban> danhSachBanTheoMaLoai(String maLoai) {
        return em.createQuery("from Ban b WHERE b.loaiBan.maLB = :maLoai", Ban.class)
                .setParameter("maLoai", maLoai).getResultList();
    }

    @Override
    public int getSoLuongBanTheoLBvTrangThai(String maLB, int trangThai) {
        try {
            Long count = em.createQuery(
                    "SELECT COUNT(b) FROM Ban b WHERE b.loaiBan.maLB = :maLB AND b.tinhTrang = :trangThai", Long.class)
                    .setParameter("maLB", maLB)
                    .setParameter("trangThai", trangThai)
                    .getSingleResult();
            return count.intValue();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public boolean capNhatTrangThaiBan(String maBan, int tinhTrang) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            int result = em.createQuery("UPDATE Ban b SET b.tinhTrang = :tinhTrang WHERE b.maBan =:maBan").setParameter("tinhTrang", tinhTrang).setParameter("maBan", maBan).executeUpdate();
            tx.commit();
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }

        }
        return false;
    }

    @Override
    public List<Ban> getListBanTheoLoai(String maLoai) {
        String query = "from Ban B "
                + "WHERE B.loaiBan.maLB = :maLoai";

        return em.createQuery(query, Ban.class)
                .setParameter("maLoai", maLoai)
                .getResultList();
    }

    @Override
    public boolean updateTableState(String maBan, int trangThai) {
        String query = "UPDATE Ban b "
                + "SET b.tinhTrang = :trangThai "
                + "WHERE b.maBan = :maBan";

        EntityTransaction transaction = em.getTransaction();
        try {
            transaction.begin();

            // Gọi update
            int updatedCount = em.createQuery(query)
                    .setParameter("trangThai", trangThai)
                    .setParameter("maBan", maBan)
                    .executeUpdate();

            transaction.commit();

            return updatedCount > 0;
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Ban getBan(String maBan) {
        String query = "from Ban where maBan = :maBan";
        return em.createQuery(query, Ban.class)
                .setParameter("maBan", maBan)
                .getSingleResult();
    }

    @Override
    public String maTuSinh(String maLoaiBan) {
        try {
            String prefix;

            // Xác định tiền tố dựa trên loại bàn
            switch (maLoaiBan) {
                case "LB001":
                    prefix = "T1";  // Loại bàn LB001 có tiền tố là "T1"
                    break;
                case "LB002":
                    prefix = "T2";  // Loại bàn LB002 có tiền tố là "T2"
                    break;
                case "LB003":
                    prefix = "T3";  // Loại bàn LB003 có tiền tố là "VP"
                    break;
                default:
                    throw new IllegalArgumentException("Loại bàn không hợp lệ");
            }

            // Truy vấn tìm mã bàn mới nhất của loại bàn này
            String jpql = "SELECT b.maBan FROM Ban b WHERE b.loaiBan.maLB = :maLB AND b.maBan LIKE :prefix ORDER BY b.maBan DESC";
            String lastId = em.createQuery(jpql, String.class)
                    .setParameter("maLB", maLoaiBan)
                    .setParameter("prefix", prefix + "%")
                    .setMaxResults(1)
                    .getSingleResult()
                    .trim();

            // Lấy số cuối cùng trong mã bàn và tăng thêm 1
            int number = Integer.parseInt(lastId.substring(prefix.length()));
            String newId = prefix + String.format("%03d", number + 1);

            return newId;

        } catch (Exception e) {
            // Trả về mã mặc định khi không có mã bàn nào, bắt đầu từ T1, T2, VP tùy theo loại bàn
            switch (maLoaiBan) {
                case "LB001":
                    return "T1001";
                case "LB002":
                    return "T2001";
                case "LB003":
                    return "VP001";
                default:
                    return "T1001";  // Mặc định trả về mã bàn T1 nếu loại bàn không hợp lệ
            }
        }
    }

}
