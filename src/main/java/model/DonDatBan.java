package model;

import jakarta.persistence.*;
import java.io.Serializable;
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
public class DonDatBan implements Serializable {
    @Id
    @Column(columnDefinition = "NVARCHAR(11)")
    @EqualsAndHashCode.Include
    private String maDDB;
    @Column(columnDefinition = "NVARCHAR(40)")
    private String hoTenKH;
    @Column(columnDefinition = "NVARCHAR(10)")
    private String soDT;
    private int soLuongKH;
    private double tienCoc;
    private LocalDateTime gioHuy;

    @Column(nullable = true)
    private double hoanCoc;
    private LocalDateTime gioHen;
    private String ghiChu;
    private LocalDate ngayTao;
    private int trangThai;

    @ManyToOne
    @JoinColumn(name = "nhanVienID")
    private NhanVien nhanVien;
    @ManyToOne
    @JoinColumn(name = "banID")
    private Ban ban;
    
    public DonDatBan(String maDDB) {
        this.maDDB = maDDB;
    }
}
