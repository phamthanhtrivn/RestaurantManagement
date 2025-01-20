package model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mon_an")
public class MonAn {
    @Id
    @Column(name = "ma_mon_an", columnDefinition = "CHAR(5)")
    @EqualsAndHashCode.Include
    private String maMA;
    @Column(name = "ten_mon_an", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenMA;
    @Column(name = "hinh_anh", columnDefinition = "VARCHAR(100)")
    private String hinhAnh;
    @Column(nullable = false)
    private double gia;
    @Column(name = "trang_thai", columnDefinition = "BIT", nullable = false)
    private boolean trangThai;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ma_khuyen_mai")
    private KhuyenMai khuyenMai;
    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ma_loai_mon", nullable = false)
    private LoaiMonAn loaiMonAn;
}
