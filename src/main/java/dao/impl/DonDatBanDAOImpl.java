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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import model.Ban;
import model.ChiTietDatBan;
import model.ChiTietHoaDon;
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
                    jpql.append("AND FUNCTION('FORMAT', d.gioHuy, 'dd/MM/yyyy') = :date ");
                } else {
                    jpql.append("AND FUNCTION('FORMAT', d.ngayTao, 'dd/MM/yyyy') = :date ");
                }
            } else if (type.equalsIgnoreCase("month")) {
                if (params.get("tt").equals("2")) {
                    jpql.append("AND FUNCTION('MONTH', d.gioHuy) = :month AND FUNCTION('YEAR', d.gioHuy) = :year ");
                } else {
                    jpql.append("AND FUNCTION('MONTH', d.ngayTao) = :month AND FUNCTION('YEAR', d.ngayTao) = :year ");
                }
            } else if (type.equalsIgnoreCase("quarter")) {
                if (params.get("tt").equals("2")) {
                    jpql.append("AND (FUNCTION('MONTH', d.gioHuy) - 1) / 3 + 1 = :quarter AND FUNCTION('YEAR', d.gioHuy) = :year ");
                } else {
                    jpql.append("AND (FUNCTION('MONTH', d.ngayTao) - 1) / 3 + 1 = :quarter AND FUNCTION('YEAR', d.ngayTao) = :year ");
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

            // Thêm ORDER BY mới đẹp (ví dụ: mới nhất -> cũ nhất)
            jpql.append("ORDER BY d.ngayTao DESC ");

            TypedQuery<DonDatBan> query = em.createQuery(jpql.toString(), DonDatBan.class);

            // Set params
            if (!params.getOrDefault("tt", "Tất cả").equals("Tất cả")) {
                query.setParameter("tt", Integer.parseInt(params.get("tt")));
            }

            if (type.equalsIgnoreCase("date")) {
                query.setParameter("date", params.get("day"));
            } else if (type.equalsIgnoreCase("month")) {
                query.setParameter("month", Integer.parseInt(params.get("month")));
                query.setParameter("year", Integer.parseInt(params.get("year")));
            } else if (type.equalsIgnoreCase("quarter")) {
                query.setParameter("quarter", Integer.parseInt(params.get("quarter")));
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

}
