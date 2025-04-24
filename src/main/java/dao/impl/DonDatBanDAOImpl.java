/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao.impl;

import dao.DonDatBanDAO;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Ban;
import model.ChiTietDatBan;
import model.ChiTietHoaDon;
import java.util.ArrayList;
import java.util.List;
import model.DonDatBan;
import model.HoaDon;

/**
 *
 * @author THANHTRI
 */
public class DonDatBanDAOImpl extends GenericDAOImpl<DonDatBan, String> implements DonDatBanDAO {

    public DonDatBanDAOImpl(Class<DonDatBan> clazz) {
        super(clazz);
    }

    public DonDatBanDAOImpl(EntityManager em, Class<DonDatBan> clazz) {
        super(em, clazz);
    }

    @Override

    public boolean checkTimeBan(String maBan, LocalDateTime gioHen) {
        boolean result = false;
        LocalDateTime gioHenStart = gioHen.minusHours(3);
        LocalDateTime gioHenEnd = gioHen.plusHours(3);
        try {
            DonDatBan donDatBan = (DonDatBan) em.createQuery(
                    "FROM DonDatBan ddb WHERE ddb.trangThai = 0 AND ddb.ban.maBan = :maBan AND ddb.gioHen BETWEEN :gioHenStart AND :gioHenEnd",
                    DonDatBan.class)
                    .setParameter("maBan", maBan)
                    .setParameter("gioHenStart", gioHenStart)
                    .setParameter("gioHenEnd", gioHenEnd)
                    .getSingleResult();
            if (donDatBan != null) {
                result = true;
            }
        } catch (NoResultException e) {

        }
        return result;
    }

    @Override
    public int findSoThuTuHomNay() {
        int stt = 0;
        try {
            List<String> dsSTT = em.createQuery("SELECT SUBSTRING(d.maDDB, 9, 3) FROM DonDatBan d WHERE FUNCTION('DATE', d.ngayTao) = CURRENT_DATE").getResultList();
            if (!dsSTT.isEmpty()) {
                int max = dsSTT.stream().mapToInt(Integer::parseInt).max().orElse(0);
                stt = max + 1;
            }
        } catch (NoResultException e) {

        }
        return stt;
    }

    @Override
    public List<DonDatBan> timKiemCapNhat(LocalDate date, String phone) {
        List<DonDatBan> list = new ArrayList<>();
        try {
            StringBuilder queryStr = new StringBuilder("FROM DonDatBan d WHERE d.trangThai = 0 AND FUNCTION('DATE', d.gioHen) = :date");

            boolean hasPhone = (phone != null && !phone.trim().isEmpty());
            if (hasPhone) {
                queryStr.append(" AND d.soDT = :phone");
            }

            TypedQuery<DonDatBan> query = em.createQuery(queryStr.toString(), DonDatBan.class);
            query.setParameter("date", date);

            if (hasPhone) { // Chỉ set nếu thực sự có phone
                query.setParameter("phone", phone);
            }

            list = query.getResultList();
        } catch (NoResultException e) {
            // Không tìm thấy cũng không sao
        }
        return list;
    }

    @Override
    public boolean huyDonDatBan(String ma, double tienHoan, LocalDateTime gioHuy) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();  // mở transaction

            int updated = em.createQuery("UPDATE DonDatBan ddb SET ddb.trangThai = 2, ddb.hoanCoc = :tienHoan, ddb.gioHuy = :gioHuy WHERE ddb.maDDB = :ma")
                    .setParameter("tienHoan", tienHoan)
                    .setParameter("gioHuy", gioHuy)
                    .setParameter("ma", ma)
                    .executeUpdate();

            tx.commit();  // commit transaction
            return updated > 0;
        } catch (Exception e) {
            if (tx.isActive()) {
                tx.rollback();  // rollback nếu lỗi
            }
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Object[] getThongTinDonDatBan(String maDDB) {
        String jpql = "SELECT ddb.hoTenKH, ddb.soDT, ddb.gioHen, b.soBan, ddb.tienCoc, "
                + "FUNCTION('timestampdiff', MINUTE, CURRENT_TIMESTAMP, ddb.gioHen) "
                + "FROM DonDatBan ddb "
                + "JOIN ddb.ban b "
                + "WHERE ddb.maDDB = :maDDB";

        return (Object[]) em.createQuery(jpql, Object[].class)
                .setParameter("maDDB", maDDB)
                .getSingleResult();
    }

    public List<Integer> loadNam() {
        try {
            String jpql = "SELECT DISTINCT FUNCTION('YEAR', ddb.ngayTao) FROM DonDatBan ddb";
            TypedQuery<Integer> query = em.createQuery(jpql, Integer.class);
            List<Integer> years = query.getResultList();
            return years.isEmpty() ? null : years;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    public List<DonDatBan> thongKeDonDatBan(String type, Map<String, String> params) {
    List<DonDatBan> list = new ArrayList<>();

    try {
        StringBuilder jpql = new StringBuilder("SELECT d FROM DonDatBan d WHERE 1=1 ");

        // Nếu trạng thái khác Tất cả thì mới thêm điều kiện
        if (!params.getOrDefault("tt", "Tất cả").equals("Tất cả")) {
            jpql.append("AND d.trangThai = :tt ");
        }

        // Xử lý theo type
        if (type.equalsIgnoreCase("date")) {
            if (params.get("tt").equals("2")) {
                jpql.append("AND DATE(d.gioHuy) = :date ");
            } else {
                jpql.append("AND DATE(d.ngayTao) = :date ");
            }
        } else if (type.equalsIgnoreCase("month")) {
            if (params.get("tt").equals("2")) {
                jpql.append("AND FUNCTION('MONTH', d.gioHuy) = :month AND FUNCTION('YEAR', d.gioHuy) = :year ");
            } else {
                jpql.append("AND FUNCTION('MONTH', d.ngayTao) = :month AND FUNCTION('YEAR', d.ngayTao) = :year ");
            }
        } else if (type.equalsIgnoreCase("quarter")) {
            // JPQL không hỗ trợ chia số nguyên: xử lý bên Java hoặc chuyển sang native query nếu cần
            if (params.get("tt").equals("2")) {
                jpql.append("AND FUNCTION('MONTH', d.gioHuy) IN :months AND FUNCTION('YEAR', d.gioHuy) = :year ");
            } else {
                jpql.append("AND FUNCTION('MONTH', d.ngayTao) IN :months AND FUNCTION('YEAR', d.ngayTao) = :year ");
            }
        } else if (type.equalsIgnoreCase("year")) {
            if (params.get("tt").equals("2")) {
                jpql.append("AND FUNCTION('YEAR', d.gioHuy) = :year ");
            } else {
                jpql.append("AND FUNCTION('YEAR', d.ngayTao) = :year ");
            }
        }

        // Xử lý loại bàn
        if (!params.getOrDefault("loaiBan", "Tất cả").equals("Tất cả")) {
            jpql.append("AND d.ban.maBan LIKE :loaiBan ");
        }

        jpql.append("ORDER BY d.ngayTao DESC ");

        TypedQuery<DonDatBan> query = em.createQuery(jpql.toString(), DonDatBan.class);

        if (!params.getOrDefault("tt", "Tất cả").equals("Tất cả")) {
            query.setParameter("tt", Integer.parseInt(params.get("tt")));
        }

        if (type.equalsIgnoreCase("date")) {
            LocalDate date = LocalDate.parse(params.get("day"), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            query.setParameter("date", date);
        } else if (type.equalsIgnoreCase("month")) {
            query.setParameter("month", Integer.parseInt(params.get("month")));
            query.setParameter("year", Integer.parseInt(params.get("year")));
        } else if (type.equalsIgnoreCase("quarter")) {
            int quarter = Integer.parseInt(params.get("quarter"));
            List<Integer> months = switch (quarter) {
                case 1 -> List.of(1, 2, 3);
                case 2 -> List.of(4, 5, 6);
                case 3 -> List.of(7, 8, 9);
                case 4 -> List.of(10, 11, 12);
                default -> throw new IllegalArgumentException("Quarter không hợp lệ");
            };
            query.setParameter("months", months);
            query.setParameter("year", Integer.parseInt(params.get("year")));
        } else if (type.equalsIgnoreCase("year")) {
            query.setParameter("year", Integer.parseInt(params.get("year")));
        }

        if (!params.getOrDefault("loaiBan", "Tất cả").equals("Tất cả")) {
            String loaiBan = params.get("loaiBan");
            String likePattern = loaiBan.equals("Tầng 1") ? "T1%" : loaiBan.equals("Tầng 2") ? "T2%" : "VP%";
            query.setParameter("loaiBan", likePattern);
        }

        list = query.getResultList();
        return list.isEmpty() ? null : list;
    } catch (Exception e) {
        e.printStackTrace();
    }

    return null;
}


    public boolean capNhatTTDDBDaNhanVaTaoHoaDon(String maDDB, String maHD) {
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();

            // 1. Tìm đơn đặt bàn
            DonDatBan ddb = em.find(DonDatBan.class, maDDB);
            if (ddb == null) {
                tx.rollback();
                return false;
            }

            // 2. Cập nhật trạng thái đơn đặt bàn
            ddb.setTrangThai(1); // 
            em.merge(ddb);

            // 3. Tạo hóa đơn mới
            HoaDon hoaDon = new HoaDon();
            hoaDon.setMaHD(maHD);
            hoaDon.setNgayLap(LocalDate.now());
            hoaDon.setTrangThai(false); // chưa thanh toán
            hoaDon.setDonDatBan(ddb);
            hoaDon.setBan(ddb.getBan());

            // Công thức tính tổng tiền như trigger bạn ghi: (tiền cọc - 100.000) * 100 / 30
            double tongTien = (ddb.getTienCoc() - 100000) * 100.0 / 30.0;
            hoaDon.setTongTien(tongTien);
            hoaDon.setTongTienTT(0);
            hoaDon.setGioVao(LocalDateTime.now());

            em.persist(hoaDon);

            // 4. Cập nhật trạng thái bàn
            Ban ban = ddb.getBan();
            if (ban != null) {
                ban.setTinhTrang(1); // 1 = đang sử dụng
                em.merge(ban);
            }

            // 5. Thêm chi tiết hóa đơn từ chi tiết đặt bàn
            List<ChiTietDatBan> chiTietDatBanList = em.createQuery(
                    "SELECT ctdb FROM ChiTietDatBan ctdb WHERE ctdb.donDatBan.maDDB = :maDDB", ChiTietDatBan.class)
                    .setParameter("maDDB", maDDB)
                    .getResultList();

            for (ChiTietDatBan ctdb : chiTietDatBanList) {
                ChiTietHoaDon cthd = new ChiTietHoaDon();
                cthd.setHoaDon(hoaDon);
                cthd.setMonAn(ctdb.getMonAn());
                cthd.setSoLuong(ctdb.getSoLuong());
                cthd.setGiaSauGiam(ctdb.getGiaSauGiam());
                cthd.setThanhTien(ctdb.getThanhTien());
                em.persist(cthd);
            }

            tx.commit();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            if (tx.isActive()) {
                tx.rollback();
            }
            return false;
        } finally {
            em.close();
        }
    }

    @Override
    public void capNhatBanTruocGioKhachDen() {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LocalDate today = LocalDate.now();
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime after30Minutes = now.plusMinutes(30);

            String jpql = "UPDATE Ban b SET b.tinhTrang = 2 "
                    + "WHERE b.tinhTrang != 2 "
                    + "AND EXISTS ("
                    + "    SELECT d FROM DonDatBan d "
                    + "    WHERE d.ban.maBan = b.maBan "
                    + "      AND d.trangThai = 0 "
                    + "      AND d.gioHen BETWEEN :now AND :after30Minutes"
                    + ")";

            em.createQuery(jpql)
                    .setParameter("now", now)
                    .setParameter("after30Minutes", after30Minutes)
                    .executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }
    }

    @Override
    public void capNhatBanSauGioKhachDen() {
        EntityTransaction tr = em.getTransaction();
        try {
            tr.begin();
            LocalDate today = LocalDate.now();
            LocalDateTime startTime = LocalDateTime.now().minusMinutes(60); // Trễ 60 phút
            LocalDateTime endTime = LocalDateTime.now().minusMinutes(30);   // Trễ 30 phút

            // Update trạng thái bàn
            String jpqlBan = "UPDATE Ban b SET b.tinhTrang = 0 "
                    + "WHERE b.tinhTrang = 2 "
                    + "AND EXISTS ("
                    + "    SELECT d FROM DonDatBan d "
                    + "    WHERE d.ban.maBan = b.maBan "
                    + "      AND d.trangThai = 0 "
                    + "      AND d.gioHen BETWEEN :startTime AND :endTime"
                    + ")";

            em.createQuery(jpqlBan)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .executeUpdate();

            // Update trạng thái đơn đặt bàn
            String jpqlDonDatBan = "UPDATE DonDatBan d SET d.trangThai = 2, d.hoanCoc = 0, d.gioHuy = CURRENT_TIMESTAMP "
                    + "WHERE d.trangThai = 0 "
                    + "AND d.gioHen BETWEEN :startTime AND :endTime";

            em.createQuery(jpqlDonDatBan)
                    .setParameter("startTime", startTime)
                    .setParameter("endTime", endTime)
                    .executeUpdate();
            tr.commit();
        } catch (Exception e) {
            tr.rollback();
            e.printStackTrace();
        }

    }

    public Object[] timKiemDonDatBanMa(String maDonDatBan, String ngayDB, String ngayKT) {
        String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen\n"
                + "FROM DonDatBan DDB "
                + "WHERE maDDB = :maDDB";
        if (ngayDB.trim().length() > 0 & ngayKT.trim().length() > 0) {
            query += "AND ngayTao BETWEEN :ngayDB AND :ngayKT";
            return em.createQuery(query, Object[].class)
                    .setParameter("maDDB", maDonDatBan)
                    .setParameter("ngayDB", ngayDB)
                    .setParameter("ngayKT", ngayKT).getSingleResult();

        } else {
            return em.createQuery(query, Object[].class)
                    .setParameter("maDDB", maDonDatBan).getSingleResult();
        }
    }

    @Override
    public List<Object[]> timKiemDonDatBanName(String name, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {

        String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen\n"
                + "FROM DonDatBan DDB "
                + "WHERE LOWER(hoTenKH) LIKE LOWER(:hoTenKH) ";

        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
            query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("hoTenKH", "%" + name + "%")
                    .setParameter("trangThai", loaiDon)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();

        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("hoTenKH", "%" + name + "%")
                    .getResultList();
        } else if (loaiDon != -1) {
            query += " AND trangThai = :trangThai " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("hoTenKH", "%" + name + "%")
                    .setParameter("trangThai", loaiDon)
                    .getResultList();
        } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT " + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("hoTenKH", "%" + name + "%")
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();
        }
        return null;
    }

    @Override
    public List<Object[]> timKiemDonDatBanPhone(String phone, int loaiDon, String sortKey, String sortValue, String ngayBD, String ngayKT) {
        String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen\n"
                + " FROM DonDatBan DDB "
                + "WHERE DDB.soDT = :phone ";
        if (loaiDon != -1 && ngayKT.trim().length() > 0) {
            query += " AND trangThai = :trangThai AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("phone", phone)
                    .setParameter("trangThai", loaiDon)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();

        } else if (loaiDon == -1 && (ngayBD.trim().length() == 0 || ngayKT.trim().length() == 0)) {
            query += " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("phone", phone).getResultList();
        } else if (loaiDon != -1) {
            query += " AND trangThai = :trangThai" + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("phone", phone)
                    .setParameter("trangThai", loaiDon).getResultList();
        } else if (ngayBD.trim().length() > 0 && ngayKT.trim().length() > 0) {
            query += " AND ngayTao BETWEEN :ngayDB AND :ngayKT" + " ORDER BY " + sortKey + " " + sortValue;
            return em.createQuery(query, Object[].class)
                    .setParameter("phone", phone)
                    .setParameter("ngayDB", ngayBD)
                    .setParameter("ngayKT", ngayKT)
                    .getResultList();
        }

        return null;
    }

    @Override
    public List<Object[]> donDatBanTrongNgay() {
        String query = "SELECT maDDB,hoTenKH,DDB.nhanVien.hoTenNV,ngayTao AS ngayTao,tienCoc,DDB.soDT,DDB.trangThai,DDB.ban.soBan,gioHen "
                + " FROM DonDatBan DDB "
                + "WHERE FUNCTION('DATE', DDB.gioHen) = CURRENT_DATE  "
                + "ORDER BY ABS(FUNCTION('TIMESTAMPDIFF', HOUR, CURRENT_TIMESTAMP, DDB.gioHen)) ASC";

        return em.createQuery(query).getResultList();
    }

    @Override
    public List<Object[]> getChiTietDonDatBan(String maDonDatBan) {
        String query = "SELECT CTDB.monAn.tenMA,CTDB.monAn.gia,giaSauGiam,soLuong,thanhTien "
                + "FROM ChiTietDatBan CTDB "
                + "WHERE CTHD.donDatBan.maDDB = :maDonDatBan";
        return em.createQuery(query, Object[].class)
                .setParameter("maDonDatBan", maDonDatBan)
                .getResultList();
    }

    @Override
    public Object[] timDDB(String ma) {
        String query = "SELECT hoTenKH,ngayTao,gioHen,soLuongKH,soDT,DDB.ban.soBan,tienCoc,hoanCoc,gioHuy,DDB.trangThai\n"
                + "FROM DonDatBan DDB "
                + "WHERE DDB.maDDB = :ma";

        return em.createQuery(query, Object[].class)
                .setParameter("ma", ma).getSingleResult();
    }

    @Override
    public List<Object[]> timChiTietDonDatBan(String maDonDatBan) {
        String query = "SELECT CTDB.monAn.tenMA, CTDB.monAn.gia, giaSauGiam, soLuong, thanhTien "
                + "FROM ChiTietDatBan CTDB "
                + "WHERE CTDB.donDatBan.maDDB = :maDonDatBan";

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

    @Override
    public DonDatBan getDDBForHD(String maDDB) {
        String query = "select ddb from DonDatBan ddb where maDDB = :maDDB";

        return em.createQuery(query, DonDatBan.class)
                .setParameter("maDDB", maDDB).getSingleResult();

    }

}
