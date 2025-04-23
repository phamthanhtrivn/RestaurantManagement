/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.HoaDonDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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

    public List<Integer> loadNam() {
        try {
            String jpql = "SELECT DISTINCT FUNCTION('YEAR', hd.ngayLap) FROM HoaDon hd";
            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
            List<Integer> years = query.getResultList();
            return years.isEmpty() ? null : years;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<HoaDon> thongKeHoaDon(String type, Map<String, String> params) {
        List<HoaDon> list = new ArrayList<>();
        try {
            String jpql = "SELECT hd FROM HoaDon hd WHERE ";

            switch (type) {
                case "year":
                    jpql += "FUNCTION('YEAR', hd.ngayLap) = :year ";
                    break;
                case "quarter":
                    jpql += "FUNCTION('QUARTER', hd.ngayLap) = :quarter AND FUNCTION('YEAR', hd.ngayLap) = :year ";
                    break;
                case "month":
                    jpql += "FUNCTION('MONTH', hd.ngayLap) = :month AND FUNCTION('YEAR', hd.ngayLap) = :year ";
                    break;
                case "date":
                    jpql += "FUNCTION('DATE', hd.ngayLap) = FUNCTION('DATE', :day) ";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }

            if (!params.get("loaiBan").equals("Tất cả")) {
                jpql += "AND hd.ban.maBan LIKE :maBan ";
            }

            TypedQuery<HoaDon> query = em.createQuery(jpql, HoaDon.class);

            // Set parameters
            if (type.equals("year")) {
                query.setParameter("year", Integer.parseInt(params.get("year")));
            } else if (type.equals("quarter")) {
                query.setParameter("quarter", Integer.parseInt(params.get("quarter")));
                query.setParameter("year", Integer.parseInt(params.get("year")));
            } else if (type.equals("month")) {
                query.setParameter("month", Integer.parseInt(params.get("month")));
                query.setParameter("year", Integer.parseInt(params.get("year")));
            } else if (type.equals("date")) {
                query.setParameter("day", java.sql.Date.valueOf(params.get("day"))); // "yyyy-MM-dd"
            }

            if (!params.get("loaiBan").equals("Tất cả")) {
                String loaiBan = params.get("loaiBan");
                if (loaiBan.equals("Tầng 1")) {
                    query.setParameter("maBan", "T1%");
                } else if (loaiBan.equals("Tầng 2")) {
                    query.setParameter("maBan", "T2%");
                } else {
                    query.setParameter("maBan", "VP%");
                }
            }

            list = query.getResultList();
            return list.isEmpty() ? null : list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<Object[]> thongKeMon(String type, Map<String, String> params) {
        List<Object[]> list = new ArrayList<>();
        try {
            String jpql = "SELECT ma.maMA, ma.tenMA, ma.gia, SUM(ct.soLuong), SUM(ct.thanhTien) "
                    + "FROM HoaDon hd "
                    + "JOIN hd.chiTietHoaDons ct "
                    + "JOIN ct.monAn ma "
                    + "JOIN ma.loaiMonAn lma "
                    + "WHERE ";

            switch (type) {
                case "year":
                    jpql += "FUNCTION('YEAR', hd.ngayLap) = :year ";
                    break;
                case "quarter":
                    jpql += "FUNCTION('QUARTER', hd.ngayLap) = :quarter AND FUNCTION('YEAR', hd.ngayLap) = :year ";
                    break;
                case "month":
                    jpql += "FUNCTION('MONTH', hd.ngayLap) = :month AND FUNCTION('YEAR', hd.ngayLap) = :year ";
                    break;
                case "date":
                    jpql += "FUNCTION('DATE', hd.ngayLap) = FUNCTION('DATE', :day) ";
                    break;
                default:
                    throw new IllegalArgumentException("Invalid type: " + type);
            }

            jpql += "AND lma.maLoaiMA = :maLoaiMon "
                    + "GROUP BY ma.maMA, ma.tenMA, ma.gia";

            Query query = em.createQuery(jpql);

            // set parameters
            if (type.equals("year")) {
                query.setParameter("year", Integer.parseInt(params.get("year")));
            } else if (type.equals("quarter")) {
                query.setParameter("quarter", Integer.parseInt(params.get("quarter")));
                query.setParameter("year", Integer.parseInt(params.get("year")));
            } else if (type.equals("month")) {
                query.setParameter("month", Integer.parseInt(params.get("month")));
                query.setParameter("year", Integer.parseInt(params.get("year")));
            } else if (type.equals("date")) {
                query.setParameter("day", java.sql.Date.valueOf(params.get("day"))); // định dạng yyyy-MM-dd
            }
            query.setParameter("maLoaiMon", params.get("maLoaiMon"));

            list = query.getResultList();
            return list.isEmpty() ? null : list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String createMaHD() {
        LocalDate dateNow = LocalDate.now();
        String year = (dateNow.getYear() % 1000) + "";
        String month = String.format("%02d", dateNow.getMonthValue());
        String day = String.format("%02d", dateNow.getDayOfMonth());

        String query = "select count(hd) from HoaDon hd where hd.ngayLap = CURDATE()";

        Long count = em.createQuery(query, Long.class).getSingleResult();

        String result = "HD" + year + month + day + String.format("%03d", count + 1);

        return result;
    }

}
