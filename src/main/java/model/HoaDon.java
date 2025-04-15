package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class HoaDon {
    @Id
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


    @ManyToOne
    @JoinColumn(name = "nhanVienID")
    private NhanVien nhanVien;
    @ManyToOne
    @JoinColumn(name = "khachHangID")
    private KhachHang khachHang;
    @ManyToOne
    @JoinColumn(name = "donDatBanID")
    private DonDatBan donDatBan;

}
