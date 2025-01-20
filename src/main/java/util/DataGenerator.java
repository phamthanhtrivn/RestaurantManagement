package util;

import model.*;
import net.datafaker.Faker;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class DataGenerator {
    private static Faker faker = new Faker();

    public static LoaiBan generateLoaiBan(){
        LoaiBan loaiBan = new LoaiBan();
        loaiBan.setMaLB("LB" + faker.number().digits(3));
        loaiBan.setTenLB(faker.lorem().word());
        return loaiBan;
    }

    public static Ban generateBan(){
        Ban ban = new Ban();
        ban.setMaBan("B" + faker.number().digits(4));
        ban.setSoBan(faker.number().numberBetween(1, 20));
        ban.setSoGhe(faker.number().numberBetween(1, 20));
        ban.setTinhTrang(faker.bool().bool());
        LoaiBan lb = new LoaiBan("LB088", "rerum");
        ban.setLoaiBan(lb);
        return ban;
    }

    public static LoaiNhanVien generateLoaiNhanVien() {
        LoaiNhanVien loaiNhanVien = new LoaiNhanVien();
        loaiNhanVien.setMaLoaiNV("LNV" + faker.number().digits(2));
        loaiNhanVien.setViTri(faker.lorem().word());
        return loaiNhanVien;
    }

    public static NhanVien generateNhanVien() {
        NhanVien nhanVien = new NhanVien();
        nhanVien.setMaNV("NV" + faker.number().digits(4));
        nhanVien.setHoTenNV(faker.name().fullName());
        nhanVien.setLoaiNhanVien(new LoaiNhanVien("LNV04", "new position"));
        nhanVien.setCCCD(faker.number().digits(12));
        nhanVien.setEmail(faker.internet().emailAddress());
        int gioiTinhIndex = faker.number().numberBetween(0, 3);
        switch (gioiTinhIndex) {
            case 0:
                nhanVien.setGioiTinh(GioiTinh.Nam);
                break;
            case 1:
                nhanVien.setGioiTinh(GioiTinh.Nu);
                break;
            case 2:
                nhanVien.setGioiTinh(GioiTinh.Khac);
                break;
        }
        nhanVien.setMatKhau(faker.internet().password());
        nhanVien.setNgaySinh(LocalDate.now());
        nhanVien.setMaXacThuc(null);
        nhanVien.setSoDT(faker.number().digits(10));
        nhanVien.setTrangThai(faker.bool().bool());
        return nhanVien;
    }

    public static CaLamViec generateCaLamViec() {
        CaLamViec caLamViec = new CaLamViec();
        caLamViec.setMaCa(faker.number().numberBetween(1, 100));
        caLamViec.setTenCa(faker.lorem().word());
        caLamViec.setThoiGianBD(LocalDateTime.now());
        caLamViec.setThoiGianKT(LocalDateTime.now());
        return caLamViec;
    }
}
