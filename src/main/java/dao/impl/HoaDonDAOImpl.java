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
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
            StringBuilder jpql = new StringBuilder("SELECT hd FROM HoaDon hd WHERE 1=1 ");

            String loaiBan = params.getOrDefault("loaiBan", "Tất cả");

            switch (type.toLowerCase()) {
                case "year" ->
                    jpql.append("AND FUNCTION('YEAR', hd.ngayLap) = :year ");
                case "quarter" ->
                    jpql.append("AND FUNCTION('MONTH', hd.ngayLap) IN :months ")
                            .append("AND FUNCTION('YEAR', hd.ngayLap) = :year ");
                case "month" ->
                    jpql.append("AND FUNCTION('MONTH', hd.ngayLap) = :month ")
                            .append("AND FUNCTION('YEAR', hd.ngayLap) = :year ");
                case "date" ->
                    jpql.append("AND FUNCTION('DATE', hd.ngayLap) = :day ");
                default ->
                    throw new IllegalArgumentException("Invalid type: " + type);
            }

            if (!loaiBan.equals("Tất cả")) {
                jpql.append("AND hd.ban.maBan LIKE :maBan ");
            }

            TypedQuery<HoaDon> query = em.createQuery(jpql.toString(), HoaDon.class);

            switch (type.toLowerCase()) {
                case "year" ->
                    query.setParameter("year", Integer.parseInt(params.get("year")));
                case "month" -> {
                    query.setParameter("month", Integer.parseInt(params.get("month")));
                    query.setParameter("year", Integer.parseInt(params.get("year")));
                }
                case "quarter" -> {
                    int q = Integer.parseInt(params.get("quarter"));
                    List<Integer> months = switch (q) {
                        case 1 ->
                            List.of(1, 2, 3);
                        case 2 ->
                            List.of(4, 5, 6);
                        case 3 ->
                            List.of(7, 8, 9);
                        case 4 ->
                            List.of(10, 11, 12);
                        default ->
                            throw new IllegalArgumentException("Invalid quarter");
                    };
                    query.setParameter("months", months);
                    query.setParameter("year", Integer.parseInt(params.get("year")));
                }
                case "date" -> {
                    LocalDate date = LocalDate.parse(params.get("day"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));
                    query.setParameter("day", date);
                }
            }

            if (!loaiBan.equals("Tất cả")) {
                String likePattern = loaiBan.equals("Tầng 1") ? "T1%"
                        : loaiBan.equals("Tầng 2") ? "T2%" : "VP%";
                query.setParameter("maBan", likePattern);
            }

            list = query.getResultList();
            return list.isEmpty() ? null : list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Object[]> thongKeMon(String type, Map<String, String> params) {
        List<Object[]> list = new ArrayList<>();
        try {
            StringBuilder jpql = new StringBuilder(
                    "SELECT ma.maMA, ma.tenMA, ma.gia, SUM(ct.soLuong), SUM(ct.thanhTien) "
                    + "FROM HoaDon hd "
                    + "JOIN hd.chiTietHoaDons ct "
                    + "JOIN ct.monAn ma "
                    + "JOIN ma.loaiMonAn lma "
                    + "WHERE 1=1 "
            );

            switch (type) {
                case "year" ->
                    jpql.append("AND YEAR(hd.ngayLap) = :year ");
                case "quarter" ->
                    jpql.append("AND MONTH(hd.ngayLap) IN :months AND YEAR(hd.ngayLap) = :year ");
                case "month" ->
                    jpql.append("AND MONTH(hd.ngayLap) = :month AND YEAR(hd.ngayLap) = :year ");
                case "date" ->
                    jpql.append("AND hd.ngayLap = :day ");
                default ->
                    throw new IllegalArgumentException("Invalid type: " + type);
            }

            jpql.append("AND lma.maLoaiMA = :maLoaiMon ");
            jpql.append("GROUP BY ma.maMA, ma.tenMA, ma.gia");

            Query query = em.createQuery(jpql.toString());

            switch (type) {
                case "year" ->
                    query.setParameter("year", Integer.parseInt(params.get("year")));
                case "quarter" -> {
                    int q = Integer.parseInt(params.get("quarter"));
                    List<Integer> months = switch (q) {
                        case 1 ->
                            List.of(1, 2, 3);
                        case 2 ->
                            List.of(4, 5, 6);
                        case 3 ->
                            List.of(7, 8, 9);
                        case 4 ->
                            List.of(10, 11, 12);
                        default ->
                            throw new IllegalArgumentException("Invalid quarter");
                    };
                    query.setParameter("months", months);
                    query.setParameter("year", Integer.parseInt(params.get("year")));
                }
                case "month" -> {
                    query.setParameter("month", Integer.parseInt(params.get("month")));
                    query.setParameter("year", Integer.parseInt(params.get("year")));
                }
                case "date" -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                    LocalDate date = LocalDate.parse(params.get("day"), formatter);
                    query.setParameter("day", date);
                }
            }

            String maLoaiMon = params.get("maLoaiMon");
            if (maLoaiMon != null && !maLoaiMon.isBlank()) {
                query.setParameter("maLoaiMon", maLoaiMon);
            } else {
                throw new IllegalArgumentException("Thiếu mã loại món");
            }

            list = query.getResultList();
            return list.isEmpty() ? null : list;

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Object[]> hoaDonTrongNgay() {
        String query = "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE ngayLap = CURRENT_DATE ";
        return em.createQuery(query, Object[].class).getResultList();
    }

    @Override
    public Object[] timKiemHoaDonTheoMa(String maHoaDon, String ngayDB, String ngayKT) {
        String query = "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE HD.maHD = :maHoaDon ";
        if (ngayDB.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT";
            return em.createQuery(query, Object[].class)
                    .setParameter("maHoaDon", maHoaDon)
                    .setParameter("ngayDB", ngayDB)
                    .setParameter("ngayKT", ngayKT)
                    .getSingleResult();
        }
        return em.createQuery(query, Object[].class).setParameter("maHoaDon", maHoaDon)
                .getSingleResult();
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoTenKH(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        String query = "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE LOWER(HD.khachHang.tenKH) LIKE LOWER(:name) ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
            query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("trangThai", loaiDon)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();

        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("name", "%" + name + "%")
                    .getResultList();
        } else if (loaiDon != -1) {
            query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("trangThai", loaiDon)
                    .getResultList();
        } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("name", "%" + name + "%")
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();
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

    public List<Object[]> timKiemHoaDonTheoSTD(String soDienThoai, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        String query = "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE HD.khachHang.soDT = :soDienThoai ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
            query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soDienThoai", soDienThoai)
                    .setParameter("trangThai", loaiDon)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();

        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soDienThoai", soDienThoai)
                    .getResultList();
        } else if (loaiDon != -1) {
            query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soDienThoai", soDienThoai)
                    .setParameter("trangThai", loaiDon)
                    .getResultList();
        } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soDienThoai", soDienThoai)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> timKiemHoaDonTheoSoBan(String soBan, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        String query = "SELECT maHD,ngayLap,HD.khachHang.tenKH,HD.khachHang.soDT,tongTien,tongTienTT,HD.ban.soBan,HD.trangThai "
                + "FROM HoaDon HD "
                + "WHERE HD.ban.soBan = :soBan ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
            query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soBan", soBan)
                    .setParameter("trangThai", loaiDon)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();

        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soBan", soBan)
                    .getResultList();
        } else if (loaiDon != -1) {
            query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soBan", soBan)
                    .setParameter("trangThai", loaiDon)
                    .getResultList();
        } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("soBan", soBan)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();
        }
        return null;
    }

    @Override
    public Object[] timKiemHD(String maHD) {
        String query = "SELECT B.soBan, HD.ngayLap, NV.hoTenNV, KH.tenKH, "
                + "HD.trangThai, HD.tongTien, "
                + "HD.tongTien * 0.08 AS thueVAT, "
                + "HD.tongTien * 0.05 AS phiPhucVu, "
                + "HD.giamGiaTV, DDB.tienCoc, B.loaiBan, HD.tongTienTT, "
                + "HD.gioVao, HD.gioRa "
                + "FROM HoaDon HD "
                + "LEFT JOIN HD.ban B "
                + "LEFT JOIN HD.nhanVien NV "
                + "LEFT JOIN HD.khachHang KH "
                + "LEFT JOIN HD.donDatBan DDB "
                + "WHERE HD.maHD = :maHD";
        return em.createQuery(query, Object[].class)
                .setParameter("maHD", maHD)
                .getSingleResult();
    }

    @Override
    public List<Object[]> timKiemCTHD(String maHD) {
        String query = "SELECT CTHD.monAn.tenMA,CTHD.monAn.gia,giaSauGiam,soLuong,thanhTien "
                + "from ChiTietHoaDon CTHD "
                + "WHERE CTHD.hoaDon.maHD = :maHD";

        List<Object[]> resultList = em.createQuery(query, Object[].class)
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
        return em.createQuery(query, HoaDon.class)
                .setParameter("maBan", ban.getMaBan()).getSingleResult();
    }

    @Override
    public HoaDon getHoaDonTheoMa(String maHD) {
        String query = "from HoaDon where maHD = :maHD";

        return em.createQuery(query, HoaDon.class)
                .setParameter("maHD", maHD)
                .getSingleResult();

    }

}
