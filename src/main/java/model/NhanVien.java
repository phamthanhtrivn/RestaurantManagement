package model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @Column(name = "ma_nhan_vien", columnDefinition = "CHAR(8)")
    @EqualsAndHashCode.Include
    private String maNV;
    @Column(name = "ho_ten", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String hoTenNV;
    @Column(name = "cccd", columnDefinition = "VARCHAR(12)", nullable = false)
    private String CCCD;
    @Column(name = "so_dien_thoai", columnDefinition = "VARCHAR(10)", nullable = false)
    private String soDT;
    @Column(name = "mat_khau", columnDefinition = "VARCHAR(64)", nullable = false)
    private String matKhau;
    @Column(columnDefinition = "VARCHAR(50)", nullable = false)
    private String email;
    @Column(name = "ngay_sinh", columnDefinition = "DATE", nullable = false)
    private LocalDate ngaySinh;
    @Column(name = "trang_thai", columnDefinition = "BIT", nullable = false)
    private boolean trangThai;
    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;
    @ManyToOne
    @JoinColumn(name = "ma_loai_nv", nullable = false)
    @ToString.Exclude
    private LoaiNhanVien loaiNhanVien;
    @Column(name = "ma_xac_thuc", columnDefinition = "CHAR(6)")
    private String maXacThuc;
}
