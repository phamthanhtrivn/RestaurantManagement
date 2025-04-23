/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.NhanVienDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.NonUniqueResultException;
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
    
}
