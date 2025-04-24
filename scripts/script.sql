INSERT INTO loaiBan (maLB, tenLB) VALUES ('LB001', 'Tầng 1');
INSERT INTO loaiBan (maLB, tenLB) VALUES ('LB002', 'Tầng 2');
INSERT INTO loaiBan (maLB, tenLB) VALUES ('LB003', 'Phòng VIP');

INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1001', 1, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1002', 2, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1003', 3, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1004', 4, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1005', 5, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1006', 6, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1007', 7, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1008', 8, 0, 'LB001', 4);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1009', 9, 0, 'LB001', 2);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T1010', 10, 2, 'LB001', 2);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2001', 1, 0, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2002', 2, 0, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2003', 3, 0, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2004', 4, 0, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2005', 5, 0, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2006', 6, 0, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2007', 7, 0, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2008', 8, 2, 'LB002', 8);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2009', 9, 0, 'LB002', 6);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T2010', 10, 0, 'LB002', 6);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T3001', 1, 0, 'LB003', 12);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T3002', 2, 0, 'LB003', 12);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T3003', 3, 0, 'LB003', 12);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T3004', 4, 0, 'LB003', 12);
INSERT INTO Ban (maBan, soBan, tinhTrang, loaiBanID, soGhe) VALUES ('T3005', 5, 0, 'LB003', 10);

INSERT INTO LoaiNhanVien (maLoaiNV, viTri) VALUES ('LNV1', 'Quản lí');
INSERT INTO LoaiNhanVien (maLoaiNV, viTri) VALUES ('LNV2', 'Thu ngân');
INSERT INTO LoaiNhanVien (maLoaiNV, viTri) VALUES ('LNV3', 'Lễ tân');

INSERT INTO NhanVien (maNV, hoTenNV, CCCD, soDT, matKhau, trangThai, loaiNhanVienID, ngaySinh, gioiTinh, maXacThuc, email) VALUES ('NVLT001', 'Trần Minh Trí', '059483728591', '0996854934', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 1, 'LNV3', CAST('2001-09-12' AS DATE), 'Nam', NULL, 'minhtri084038@gmail.com');
INSERT INTO NhanVien (maNV, hoTenNV, CCCD, soDT, matKhau, trangThai, loaiNhanVienID, ngaySinh, gioiTinh, maXacThuc, email) VALUES ('NVQL001', 'Phạm Thành Trí', '864321567955', '0387512346', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 1, 'LNV1', CAST('1996-07-31' AS DATE), 'Nam', NULL, 'phamthanhtri0712@gmail.com');
INSERT INTO NhanVien (maNV, hoTenNV, CCCD, soDT, matKhau, trangThai, loaiNhanVienID, ngaySinh, gioiTinh, maXacThuc, email) VALUES ('NVTN001', 'Đặng Thị Vân Ly', '059378654824', '0928594324', 'ef797c8118f02dfb649607dd5d3f8c7623048c9c063d532cc95c5ed7a898a64f', 1, 'LNV2', CAST('2000-08-16' AS DATE), 'Nam', NULL, 'hthanhtuan.2307@gmail.com');


INSERT INTO LoaiKhachHang (maLoaiKH, tenLoaiKH, giamGiaTV) VALUES ('LKH1', 'Khách hàng thành viên', 0 );
INSERT INTO LoaiKhachHang (maLoaiKH, tenLoaiKH, giamGiaTV) VALUES ('LKH2', 'Khách hàng Goal', 10 );
INSERT INTO LoaiKhachHang (maLoaiKH, tenLoaiKH, giamGiaTV) VALUES ('LKH3', 'Khách hàng Platinum', 15 );

INSERT INTO KhachHang (maKH, tenKH, soDT, diemTL, trangThai, loaiKhachHangID, ngayTao)
VALUES ('KH000001', 'Trần Thị Bích', '0912345678', 0, 1, 'LKH1', CAST('2024-12-09' AS DATE));

INSERT INTO KhachHang (maKH, tenKH, soDT, diemTL, trangThai, loaiKhachHangID, ngayTao)
VALUES ('KH000002', 'Lê Hoàng Phúc', '0923456789', 281, 1, 'LKH2', CAST('2024-12-09' AS DATE));

INSERT INTO KhachHang (maKH, tenKH, soDT, diemTL, trangThai, loaiKhachHangID, ngayTao)
VALUES ('KH000003', 'Nguyễn Xuân Mạnh', '0367155132', 0, 1, 'LKH1', CAST('2024-12-13' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM001', 'Voucher Mùa Đông Ấm Áp', 10, CAST('2024-12-30' AS DATE), CAST('2024-09-01' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM002', 'Ưu đãi cuối tháng 8', 10, CAST('2024-12-12' AS DATE), CAST('2024-09-01' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM003', 'Ưu đãi 30 tháng 4', 20, CAST('2024-05-01' AS DATE), CAST('2024-04-25' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM004', 'Ưu đãi giữa tháng 1', 50, CAST('2024-01-20' AS DATE), CAST('2024-01-10' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM005', 'Ưu đãi cuối tháng 1', 40, CAST('2024-02-01' AS DATE), CAST('2024-01-25' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM008', 'Mùa Đông Ấm Áp', 10, CAST('2024-12-30' AS DATE), CAST('2024-12-13' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM010', 'Cứu Cảo Tôm Phúc Lục', 10, CAST('2024-12-31' AS DATE), CAST('2024-12-13' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM011', 'Khuyến mãi tháng 11', 10, CAST('2024-11-14' AS DATE), CAST('2024-11-01' AS DATE));

INSERT INTO KhuyenMai (maKM, tenKM, giamGia, ngayKT, ngayBD)
VALUES ('KM012', 'Khuyến mãi đầu tháng 11', 10, CAST('2024-12-20' AS DATE), CAST('2024-12-12' AS DATE));


INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM001', 'Dimsum');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM002', 'Drink');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM003', 'Soup');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM004', 'Vịt Bắc Kinh');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM005', 'Meat');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM006', 'Sea Food');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM007', 'Tofu');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM008', 'Rice');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM009', 'Noodle');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM010', 'Vegetable');
INSERT INTO LoaiMonAn (maLoaiMA, tenLoaiMA) VALUES ('LM011', 'Dessert');

INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA001', 'Mì khô xá xíu', '/hinhAnh/miKhoXaXiu.jpg', 105000, 1, 'LM009', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA002', 'Mì sủi cảo', '/hinhAnh/miSuiCao.jpg', 115000, 1, 'LM009', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA003', 'Mì xào vịt', '/hinhAnh/miXaoVit.jpg', 115000, 1, 'LM009', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA004', 'Canh bào ngư', '/hinhAnh/canhBaoNgu.jpg', 155000, 1, 'LM003', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA005', 'Canh vi cá thịt cua', '/hinhAnh/canhViCaThitCua.jpg', 205000, 1, 'LM003', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA006', 'Canh xương hầm ', '/hinhAnh/canhXuongHam.jpg', 175000, 1, 'LM003', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA007', 'Canh tiềm Atiso', '/hinhAnh/canhTiemAtiso.jpg', 255000, 1, 'LM003', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA008', 'Canh hoa tiêu Tứ Xuyên', '/hinhAnh/canhCaHoaTieuTuXuyen.jpg', 285000, 1, 'LM003', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA009', 'Bánh bao ca dé', '/hinhAnh/banhBaoCaDe.jpg', 115000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA010', 'Cảo bò bấm Truffle', '/hinhAnh/caoBoNamTruffle.jpg', 155000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA011', 'Cuộn tôm chiên', '/hinhAnh/cuonTomChien.jpg', 135000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA012', 'Chân gà sốt', '/hinhAnh/chanGaSot.jpg', 125000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA013', 'Cảo tôm phúc lục', '/hinhAnh/caoTomPhucLuc.jpg', 125000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA014', 'Ngọc bích bó xôi', '/hinhAnh/ngocBichBoXoi.jpg', 130000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA015', 'Tôm chiên sốt táo', '/hinhAnh/tomChienSotTao.jpg', 135000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA016', 'Há cảo sò điệp', '/hinhAnh/haCaoSoDiep.jpg', 145000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA017', 'Há cảo tôm', '/hinhAnh/haCaoTom.jpg', 130000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA018', 'Xíu mại sò điệp', '/hinhAnh/xiuMaiSoDiep.jpg', 125000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA019', 'Atiso mật ong hạt sen', '/hinhAnh/atisoMatOngSenVang.jpg', 120000, 1, 'LM002', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA020', 'Trà trái cây', '/hinhAnh/traTraiCay.jpg', 110000, 1, 'LM002', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA021', 'Heineken', '/hinhAnh/heineken.jpg', 95000, 1, 'LM002', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA022', 'Trà đào cam sả', '/hinhAnh/traDaoCamSa.jpg', 110000, 1, 'LM002', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA023', 'Hồng trà xí muội', '/hinhAnh/hongTraXiMuoi.jpg', 110000, 1, 'LM002', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA024', 'Hồng trà quýt', '/hinhAnh/hongTraQuyt.jpg', 115000, 1, 'LM002', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA025', 'Vịt quay bắc kinh', '/hinhAnh/vitQuayBacKinh.jpg', 255000, 1, 'LM004', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA026', 'Vịt răng muối tỏi', '/hinhAnh/vitRangMuoiToi.jpg', 275000, 1, 'LM004', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA027', 'Da vịt cuốn', '/hinhAnh/daVitCuon.jpg', 155000, 1, 'LM004', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA028', 'Heo quay da giòn', '/hinhAnh/heoQuayDaGion.jpeg', 205000, 1, 'LM005', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA029', 'Sườn heo hầm', '/hinhAnh/suonHeoHam.jpeg', 185000, 1, 'LM005', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA030', 'Thịt chua ngọt', '/hinhAnh/thitChuaNgot.jpeg', 165000, 1, 'LM005', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA031', 'Bào ngư sốt hồng kông', '/hinhAnh/baoNguSotHongKong.jpeg', 245000, 1, 'LM006', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA032', 'Sò điệp cháy tỏi', '/hinhAnh/soDiepChayToi.jpeg', 245000, 1, 'LM006', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA033', 'Cua sốt hoàng kim', '/hinhAnh/cuaSotHoangKim.jpeg', 245000, 1, 'LM006', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA034', 'Tàu hủ nước đường', '/hinhAnh/tauHuNuocDuong.jpeg', 105000, 1, 'LM007', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA035', 'Cơm lươn sốt tiêu đên', '/hinhAnh/comLuonSotTieuDen.jpeg', 1155000, 1, 'LM008', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA036', 'Cơm gà xì dầu', '/hinhAnh/comGaXiDau.jpeg', 165000, 1, 'LM008', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA037', 'Cơm tôm ngọc bích', '/hinhAnh/comTomNgocBich.jpeg', 165000, 1, 'LM008', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA038', 'Bông cải xanh sốt dầu hào', '/hinhAnh/bongCaiXanhSotDauHao.jpeg', 145000, 1, 'LM010', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA039', 'Cải hongkong xào tỏi', '/hinhAnh/caiHongKongXaoToi.jpeg', 135000, 1, 'LM010', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA040', 'Đậu que xào quế lân', '/hinhAnh/dauQueXaoQueLan.jpeg', 140000, 1, 'LM010', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA041', 'Chè dương chi kim lộ', '/hinhAnh/cheDuongChiKimLo.jpeg', 123000, 1, 'LM011', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA042', 'Chè củ năng hạt sen', '/hinhAnh/cheCuNangHatSen.jpeg', 123000, 1, 'LM011', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA043', 'Chè sen nhãnn', '/hinhAnh/cheSenNhan.jpeg', 123000, 0, 'LM011', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA046', 'Bào Ngư', '/hinhAnh/baoNguSotHongKong.jpeg', 120000, 1, 'LM001', NULL);
INSERT INTO MonAn (maMA, tenMA, hinhAnh, gia, trangThai, loaiMonAnID, khuyenMaiID) VALUES ('MA048', 'Nước', '/hinhAnh/atisoMatOngSenVang.jpg', 120000, 1, 'LM002', NULL);
