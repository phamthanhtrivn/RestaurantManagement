package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class DonDatBan {
    @Id
    private String maDDB;
    private String hoTenKH;
    private String soDT;
    private int soLuongKH;
    private double tienCoc;
    private LocalDateTime gioHuy;
    private double hoanCoc;
    private LocalDateTime gioHen;
    private String ghiChu;
    private LocalDate ngayTao;

    @ManyToOne
    @JoinColumn(name = "nhanVienID")
    private NhanVien nhanVien;
    @ManyToOne
    @JoinColumn(name = "banID")
    private Ban ban;
}
