/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.DonDatBanDAO;
import jakarta.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;
import model.DonDatBan;

/**
 *
 * @author THANHTRI
 */
public class DonDatBanDAOImpl extends GenericDAOImpl<DonDatBan, String> implements DonDatBanDAO{
    
    public DonDatBanDAOImpl(Class<DonDatBan> clazz) {
        super(clazz);
    }
    
    public DonDatBanDAOImpl(EntityManager em, Class<DonDatBan> clazz) {
        super(em, clazz);
    }

    @Override
    public Object[] timKiemDonDatBanMa(String maDonDatBan, String ngayDB, String ngayKT) {
      String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen\n" +
               "FROM DonDatBan DDB "
              + "WHERE maDDB = :maDDB";
      if (ngayDB.trim().length() > 0 & ngayKT.trim().length() > 0) {
          query += "AND ngayTao BETWEEN :ngayDB AND :ngayKT";
          return em.createQuery(query,Object[].class)
                  .setParameter("maDDB", maDonDatBan)
                  .setParameter("ngayDB", ngayDB)
                  .setParameter("ngayKT", ngayKT).getSingleResult();
           
      }
      else{
           return em.createQuery(query,Object[].class)
                  .setParameter("maDDB", maDonDatBan).getSingleResult();
      }
    }

    @Override
    public List<Object[]> timKiemDonDatBanName(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        
        String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen\n" +
               "FROM DonDatBan DDB "
              + "WHERE LOWER(hoTenKH) LIKE LOWER(:hoTenKH) ";
                 
         if (loaiDon != -1 && ngayKT.trim().length() > 0) {
             query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
             return em.createQuery(query,Object[].class)
                     .setParameter("hoTenKH", "%" + name + "%")
                     .setParameter("trangThai", loaiDon)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
           
        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query,Object[].class)
                     .setParameter("hoTenKH", "%" + name + "%")
                    .getResultList();
        } else if (loaiDon != -1 ) {
          query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
                       return em.createQuery(query,Object[].class)
                     .setParameter("hoTenKH", "%" + name + "%")
                     .setParameter("trangThai", loaiDon)
                     .getResultList();
        }  else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
                         return em.createQuery(query,Object[].class)
                     .setParameter("hoTenKH", "%" + name + "%")
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> timKiemDonDatBanPhone(String phone, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen\n" +
       " FROM DonDatBan DDB "
      + "WHERE DDB.soDT = :phone ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
             query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue ;
             return em.createQuery(query,Object[].class)  
                     .setParameter("phone", phone)
                     .setParameter("trangThai", loaiDon)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
           
        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue ;
            return em.createQuery(query,Object[].class)
                    .setParameter("phone", phone).getResultList();
        } else if (loaiDon != -1 ) {
          query += " AND trangThai = :trangThai" + " ORDER BY " + sortKey + " " + sortValue ;
                       return em.createQuery(query,Object[].class)
                     .setParameter("phone", phone)
                     .setParameter("trangThai", loaiDon).getResultList();
        }  else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT"+ " ORDER BY " + sortKey + " " + sortValue ;
                         return em.createQuery(query,Object[].class)
                     .setParameter("phone", phone)
                     .setParameter("ngayDB", ngayBD)
                     .setParameter("ngayKT", ngayKT)
                     .getResultList();
        }
       
        
        return null;
    }

    @Override
    public List<Object[]> donDatBanTrongNgay() {
        String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen " +
       " FROM DonDatBan DDB "
        + "WHERE FUNCTION('DATE', DDB.gioHen) = '2024-12-13'  "
        + "ORDER BY ABS(FUNCTION('TIMESTAMPDIFF', HOUR, CURRENT_TIMESTAMP, DDB.gioHen)) ASC";
        
        
        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Object[]> getChiTietDonDatBan(String maDonDatBan) {
        String query = "SELECT CTDB.monAn.tenMA,CTDB.monAn.gia,giaSauGiam,soLuong,thanhTien "
                + "FROM ChiTietDatBan CTDB "
                + "WHERE CTHD.donDatBan.maDDB = :maDonDatBan" ;
        return em.createQuery(query,Object[].class)
                .setParameter("maDonDatBan", maDonDatBan)
                .getResultList();
    }

    @Override
    public Object[] timDDB(String ma) {
        String query = "SELECT hoTenKH,ngayTao,gioHen,soLuongKH,soDT,DDB.ban.soBan,tienCoc,hoanCoc,gioHuy,DDB.trangThai\n"
        + "FROM DonDatBan DDB "
        + "WHERE DDB.maDDB = :ma";
        
        return em.createQuery(query,Object[].class)
                .setParameter("ma", ma).getSingleResult();
    }

    @Override
    public List<Object[]> timChiTietDonDatBan(String maDonDatBan) {
        String query = "SELECT CTDB.monAn.tenMA, CTDB.monAn.gia, giaSauGiam, soLuong, thanhTien " +
                       "FROM ChiTietDatBan CTDB " +
                       "WHERE CTDB.donDatBan.maDDB = :maDonDatBan";

        List<Object[]> resultList = em.createQuery(query, Object[].class)
                                      .setParameter("maDonDatBan", maDonDatBan)
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
  
    
    
    
    
}
