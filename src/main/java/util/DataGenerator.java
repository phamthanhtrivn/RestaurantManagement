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
        ban.setTinhTrang(faker.number().numberBetween(0, 2));
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


    public static KhuyenMai generateKhuyenMai() {
        KhuyenMai khuyenMai = new KhuyenMai();
        khuyenMai.setMaKM( faker.number().digits(5));
        khuyenMai.setTenKM(faker.lorem().word());
        khuyenMai.setGiamGia(faker.number().numberBetween(1, 100));
        khuyenMai.setNgayBD(LocalDate.now());
        khuyenMai.setNgayKT(LocalDate.now());
        return khuyenMai;
    }

    public static MonAn generateMonAn(){
        MonAn monAn = new MonAn();
        monAn.setMaMA(faker.number().digits(5));
        monAn.setTenMA(faker.lorem().word());
        monAn.setGia(faker.number().numberBetween(50000, 1000000));
        monAn.setHinhAnh(faker.internet().url());
        monAn.setTrangThai(true);
        monAn.setLoaiMonAn(new LoaiMonAn("LMA07", "numquam"));
        return monAn;
    }
    public static LoaiMonAn generateLoaiMonAn(){
        LoaiMonAn loaiMonAn = new LoaiMonAn();
        loaiMonAn.setMaLoaiMA("LMA" + faker.number().digits(2));
        loaiMonAn.setTenLoaiMA(faker.lorem().word());
        return loaiMonAn;
    }

    public static KhachHang generateKhachHang() {
        KhachHang khachHang = new KhachHang();
        khachHang.setMaKH("KH" + faker.number().digits(6));
        khachHang.setTenKH(faker.name().fullName());
        khachHang.setSoDT(faker.number().digits(10));
        khachHang.setNgayTao(LocalDate.now());
        khachHang.setNgaySinh(LocalDate.now().minusYears(faker.number().numberBetween(18, 60)));
        khachHang.setDiemTL(faker.number().numberBetween(0, 1000));
        khachHang.setTrangThai(faker.bool().bool());

        LoaiKhachHang loaiKH = new LoaiKhachHang();
        loaiKH.setMaLoaiKH("LKH" + faker.number().digits(2));
        loaiKH.setTenLoaiKH(faker.options().option("VIP", "Regular", "New"));
        khachHang.setLoaiKH(loaiKH);

        return khachHang;
    }
    public static LoaiKhachHang generateLoaiKhachHang() {
        LoaiKhachHang loaiKhachHang = new LoaiKhachHang();
        loaiKhachHang.setMaLoaiKH("LKH" + faker.number().digits(2));
        loaiKhachHang.setTenLoaiKH(faker.lorem().word());
        loaiKhachHang.setGiamGiaTV(faker.number().numberBetween(5, 50));
        loaiKhachHang.setGiamGiaSN(faker.number().numberBetween(5, 50));
        return loaiKhachHang;
    }


}
