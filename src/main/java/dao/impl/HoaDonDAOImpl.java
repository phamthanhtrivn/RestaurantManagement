/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.HoaDonDAO;
import jakarta.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import model.Ban;
import model.HoaDon;

/**
 *
 * @author THANHTRI
 */
public class HoaDonDAOImpl extends GenericDAOImpl<HoaDon, String> implements HoaDonDAO {
    
    public HoaDonDAOImpl(Class<HoaDon> clazz) {
        super(clazz);
    }
    
    public HoaDonDAOImpl(EntityManager em, Class<HoaDon> clazz) {
        super(em, clazz);
    }

    @Override
    public List<Object[]> hoaDonTrongNgay() {
        String query =  "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE ngayLap = '2024-12-13' ";
        return em.createQuery(query,Object[].class).getResultList();
    }

    @Override
    public Object[] timKiemHoaDonTheoMa(String maHoaDon, String ngayDB, String ngayKT) {
        String query =  "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
        + "FROM HoaDon HD "
        + "WHERE HD.maHD = :maHoaDon ";
        if(ngayDB.trim().length() > 0 && ngayKT.trim().length() > 0){
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT";
            return em.createQuery(query,Object[].class)
                    .setParameter("maHoaDon", maHoaDon)
                    .setParameter("ngayDB",ngayDB )
                    .setParameter("ngayKT", ngayKT)
                .getSingleResult();
        }
        return em.createQuery(query,Object[].class).setParameter("maHoaDon", maHoaDon)
                .getSingleResult();
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoTenKH(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        String query =  "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE LOWER(HD.khachHang.tenKH) LIKE LOWER(:name) ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
             query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
             return em.createQuery(query,Object[].class)
                     .setParameter("name", "%" + name + "%")
                     .setParameter("trangThai", loaiDon)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
           
        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query,Object[].class)
                     .setParameter("name", "%" + name + "%")
                    .getResultList();
        } else if (loaiDon != -1 ) {
          query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
                       return em.createQuery(query,Object[].class)
                     .setParameter("name", "%" + name + "%")
                     .setParameter("trangThai", loaiDon)
                     .getResultList();
        }  else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
                         return em.createQuery(query,Object[].class)
                     .setParameter("name", "%" + name + "%")
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoSTD(String soDienThoai, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
               String query =  "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE HD.khachHang.soDT = :soDienThoai ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
             query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
             return em.createQuery(query,Object[].class)
                     .setParameter("soDienThoai", soDienThoai)
                     .setParameter("trangThai", loaiDon)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
           
        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query,Object[].class)
                     .setParameter("soDienThoai", soDienThoai)
                    .getResultList();
        } else if (loaiDon != -1 ) {
          query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
                       return em.createQuery(query,Object[].class)
                     .setParameter("soDienThoai", soDienThoai)
                     .setParameter("trangThai", loaiDon)
                     .getResultList();
        }  else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
                         return em.createQuery(query,Object[].class)
                     .setParameter("soDienThoai", soDienThoai)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoSoBan(String soBan, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        String query =  "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE HD.ban.soBan = :soBan ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
             query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
             return em.createQuery(query,Object[].class)
                     .setParameter("soBan", soBan)
                     .setParameter("trangThai", loaiDon)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
           
        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query,Object[].class)
                     .setParameter("soBan", soBan)
                    .getResultList();
        } else if (loaiDon != -1 ) {
          query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
                       return em.createQuery(query,Object[].class)
                     .setParameter("soBan", soBan)
                     .setParameter("trangThai", loaiDon)
                     .getResultList();
        }  else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
                         return em.createQuery(query,Object[].class)
                     .setParameter("soBan", soBan)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
        }
        return null;
    }

    @Override
    public Object[] timKiemHD(String maHD) {
            String query = "SELECT B.soBan, HD.ngayLap, NV.hoTenNV, KH.tenKH, " +
                           "HD.trangThai, HD.tongTien, " +
                           "HD.tongTien * 0.08 AS thueVAT, " +
                           "HD.tongTien * 0.05 AS phiPhucVu, " +
                           "HD.giamGiaTV, DDB.tienCoc, B.loaiBan, HD.tongTienTT, " +
                           "HD.gioVao, HD.gioRa " +
                           "FROM HoaDon HD " +
                           "LEFT JOIN HD.ban B " +
                           "LEFT JOIN HD.nhanVien NV " +
                           "LEFT JOIN HD.khachHang KH " +
                           "LEFT JOIN HD.donDatBan DDB " +
                           "WHERE HD.maHD = :maHD";
        return em.createQuery(query,Object[].class)
                .setParameter("maHD",maHD )
                .getSingleResult();
    }

    @Override
    public List<Object[]> timKiemCTHD(String maHD) {
       String query = "SELECT CTHD.monAn.tenMA,CTHD.monAn.gia,giaSauGiam,soLuong,thanhTien "
               + "from ChiTietHoaDon CTHD "
               + "WHERE CTHD.hoaDon.maHD = :maHD";
       
      List<Object[]> resultList =   em.createQuery(query,Object[].class)
               .setParameter("maHD", maHD)
               .getResultList();
       
        List<Object[]> modifiedList = new ArrayList<>();
        int soThuTu = 1;
        for (Object[] row : resultList) {
            Object[] newRow = new Object[row.length + 1];
            newRow[0] = soThuTu++;
            System.arraycopy(row, 0, newRow, 1, row.length);
            modifiedList.add(newRow);
        }

        return modifiedList;
    }

    @Override
    public String createMaHD() {
        
        LocalDate dateNow = LocalDate.now();
        String year = (dateNow.getYear()%1000) + "";
        String month = String.format("%02d", dateNow.getMonthValue());
        String day = String.format("%02d", dateNow.getDayOfMonth());
        
        String query = "select count(hd) from HoaDon hd where hd.ngayLap = CURDATE()";
  
        Long count = em.createQuery(query,Long.class).getSingleResult();
                
        String result =  "HD" + year + month + day + String.format("%03d", count + 1);
        
        
        return result;
    }

    @Override
    public boolean checkBanVip(String maBan) {
        String query = "SELECT b FROM Ban b WHERE b.loaiBan.maLB = 'LB003' AND b.maBan = :maBan";

        List<Ban> result = em.createQuery(query, Ban.class)
                             .setParameter("maBan", maBan)
                             .getResultList();

        return !result.isEmpty(); 
    }

    @Override
    public HoaDon getHoaDonTheoBanHoatDong(Ban ban) {
        String query = "select HD from HoaDon HD where trangThai = false and HD.ban.maBan = :maBan and HD.ban.tinhTrang = 1";
        return em.createQuery(query,HoaDon.class)
                .setParameter("maBan", ban.getMaBan()).getSingleResult();
    }
    
    @Override
    public HoaDon getHoaDonTheoMa(String maHD){
        String query = "from HoaDon where maHD = :maHD";
        
        return em.createQuery(query,HoaDon.class)
                .setParameter("maHD", maHD)
                .getSingleResult();
        
    }

    
}
