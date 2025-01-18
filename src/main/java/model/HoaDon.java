package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "hoa_don")
public class HoaDon {

    @Id
    @Column(name = "ma_hd", columnDefinition = "VARCHAR(11)")
    @EqualsAndHashCode.Include
    private String maHD;
    @Column(name = "ngay_lap", columnDefinition = "DATE", nullable = false)
    private LocalDate ngayLap;
    @Column(name = "tong_tien", nullable = false)
    private double tongTien;
    @Column(name = "tong_tien_tt", nullable = false)
    private double tongTienTT;
    @Column(name = "trang_thai", nullable = false)
    private boolean trangThai;
    @Column(name = "gio_vao", columnDefinition = "DATETIME", nullable = false)
    private LocalDateTime gioVao;
    @Column(name = "gio_ra", columnDefinition = "DATETIME")
    private LocalDateTime gioRa;
    @Column(name = "giam_gia_sn")
    private double giamGiaSN;
    @Column(name = "giam_gia_tv")
    private double giamGiaTV;
    @Column(name = "vat")
    private double VAT;
    @Column(name = "phi_phong_vip")
    private double phiPhongVIP;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ma_nv", nullable = false)
    private NhanVien nhanVien;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ma_kh")
    private KhachHang khachHang;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ma_ban", nullable = false)
    private Ban ban;
}
