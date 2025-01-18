package model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chi_tiet_hoa_don")
public class ChiTietHoaDon {
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_hoa_don", nullable = false)
    @EqualsAndHashCode.Include
    private HoaDon hoaDon;
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_mon_an", nullable = false)
    @EqualsAndHashCode.Include
    private MonAn monAn;
    @Column(name = "so_luong", nullable = false)
    private int soLuong;
    @Column(name = "thanh_tien", nullable = false)
    private double thanhTien;
    @Column(name = "gia_sau_giam", nullable = false)
    private double giaSauGiam;
}
