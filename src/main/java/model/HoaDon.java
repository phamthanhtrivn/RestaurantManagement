package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
public class HoaDon {
    @Id
    @Column(columnDefinition = "NVARCHAR(11)")
    @EqualsAndHashCode.Include
    private String maHD;
    private LocalDate ngayLap;
    private double tongTien;
    private double tongTienTT;
    private boolean trangThai;
    private LocalDateTime gioVao;
    private LocalDateTime gioRa;
    private double giamGiaTV;
    private double VAT;
    private double phiPhongVIP;
    private double phiDichVu;


    @ManyToOne
    @JoinColumn(name = "nhanVienID")
    private NhanVien nhanVien;
    @ManyToOne
    @JoinColumn(name = "khachHangID")
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "donDatBanID")
    private DonDatBan donDatBan;
    @ManyToOne
    @JoinColumn(name = "banID")
    private Ban ban;


}
