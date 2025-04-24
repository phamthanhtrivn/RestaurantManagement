/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.NhanVienDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
import jakarta.persistence.TypedQuery;
import java.util.Random;
import model.NhanVien;

/**
 *
 * @author THANHTRI
 */
public class NhanVienDAOImpl extends GenericDAOImpl<NhanVien, String> implements NhanVienDAO {

    public NhanVienDAOImpl(Class<NhanVien> clazz) {
        super(clazz);
    }

    public NhanVienDAOImpl(EntityManager em, Class<NhanVien> clazz) {
        super(em, clazz);
    }

    @Override
    public NhanVien dangNhap(String username, String password) {
        try {
            return em.createQuery("FROM NhanVien nv WHERE nv.maNV = :username AND nv.matKhau = :password", NhanVien.class)
                    .setParameter("username", username)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            System.out.println("Không tìm thấy nhân viên với tài khoản hoặc mật khẩu đã nhập.");
            return null;
        } catch (NonUniqueResultException e) {
            System.out.println("Có nhiều hơn một nhân viên trùng tài khoản và mật khẩu!");
            return null;
        } catch (Exception e) {
            System.out.println("Lỗi khi đăng nhập: " + e.getMessage());
            return null;
        }
    }

    public NhanVien getNV(String maNV) {
        String query = "select nv from NhanVien nv where nv.maNV = :maNV";
        
        return em.createQuery(query,NhanVien.class)
                .setParameter("maNV", maNV)
                .getSingleResult();
    }
    
        @Override
   public String maTuSinh(String maLoaiNhanVien) {
    try {
        String prefix;

        switch (maLoaiNhanVien) {
            case "LNV1":
                prefix = "NVQL";
                break;
            case "LNV2":
                prefix = "NVTN";
                break;
            case "LNV3":
                prefix = "NVLT";
                break;
            default:
                throw new IllegalArgumentException("Loại nhân viên không hợp lệ");
        }

        String jpql = "SELECT nv.maNV FROM NhanVien nv WHERE nv.loaiNhanVien.maLoaiNV = :maLoaiNV AND nv.maNV LIKE :prefix ORDER BY nv.maNV DESC";
        String lastId = em.createQuery(jpql, String.class)
                          .setParameter("maLoaiNV", maLoaiNhanVien)
                          .setParameter("prefix", prefix + "%")
                          .setMaxResults(1)
                          .getSingleResult()
                          .trim();

        int number = Integer.parseInt(lastId.substring(prefix.length()));
        String newId = prefix + String.format("%03d", number + 1);
        return newId;

    } catch (Exception e) {
        switch (maLoaiNhanVien) {
            case "LNV1":
                return "NVQL001";
            case "LNV2":
                return "NVTN001";
            case "LNV3":
                return "NVLT001";
            default:
                return "NV001"; 
        }
    }
}
   
   @Override
   public NhanVien  findById2(String maNV) {
       try {
             System.out.println("Đang thực thi truy vấn với maLoaiNV: " + maNV);
        return em.createQuery(
            "SELECT nv FROM NhanVien nv JOIN FETCH nv.loaiNhanVien WHERE nv.maNV = :maNV",
            NhanVien.class)
                 .setParameter("maNV", maNV)
        .getSingleResult();
       } catch (Exception e) { System.out.println("Không tìm thấy nhân viên với mã: " + maNV);
        return null;
       }
   }
   
   @Override
   public boolean checkOTP(String maNV, String email, String otp) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV AND nv.email = :email AND nv.maXacThuc = :otp";
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("maNV", maNV);
        query.setParameter("email", email);
        query.setParameter("otp", otp);

        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean updatePassword(String maNV, String pass) {
        String jpql = "UPDATE NhanVien nv SET nv.matKhau = :pass WHERE nv.maNV = :maNV";
        int updated = em.createQuery(jpql)
                        .setParameter("pass", pass)
                        .setParameter("maNV", maNV)
                        .executeUpdate();
        return updated > 0;
    }

    @Override
    public String getOldPass(String maNV) {
        String jpql = "SELECT nv.matKhau FROM NhanVien nv WHERE nv.maNV = :maNV";
        TypedQuery<String> query = em.createQuery(jpql, String.class);
        query.setParameter("maNV", maNV);

        return query.getResultStream().findFirst().orElse("");
    }

    @Override
    public boolean checkEmail(String maNV, String email) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV AND nv.email = :email";
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("maNV", maNV);
        query.setParameter("email", email);

        return !query.getResultList().isEmpty();
    }

    @Override
    public boolean checkMaNV(String maNV) {
        String jpql = "SELECT nv FROM NhanVien nv WHERE nv.maNV = :maNV";
        TypedQuery<NhanVien> query = em.createQuery(jpql, NhanVien.class);
        query.setParameter("maNV", maNV);

        return !query.getResultList().isEmpty();
    }

    @Override
    public String generateOTP(int length) {
        String numbers = "0123456789";
        Random random = new Random();
        StringBuilder otp = new StringBuilder();
        for (int i = 0; i < length; i++) {
            otp.append(numbers.charAt(random.nextInt(numbers.length())));
        }
        return otp.toString();
    }

    @Override
    public boolean updateOTP(String maNV, String otp) {
        String jpql = "UPDATE NhanVien nv SET nv.maXacThuc = :otp WHERE nv.maNV = :maNV";
        int updated = em.createQuery(jpql)
                        .setParameter("otp", otp)
                        .setParameter("maNV", maNV)
                        .executeUpdate();
        return updated > 0;
    }

    @Override
    public boolean deleteOTP(String maNV) {
        String jpql = "UPDATE NhanVien nv SET nv.maXacThuc = NULL WHERE nv.maNV = :maNV";
        int updated = em.createQuery(jpql)
                        .setParameter("maNV", maNV)
                        .executeUpdate();
        return updated > 0;
    }

    
}
