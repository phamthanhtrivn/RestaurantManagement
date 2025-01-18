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
@Table(name = "khach_hang")
public class KhachHang {
    @Id
    @Column(name = "ma_khach_hang", columnDefinition = "CHAR(8)")
    @EqualsAndHashCode.Include
    private String maKH;
    @Column(name = "ten_khach_hang", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenKH;
    @Column(name = "so_dien_thoai", columnDefinition = "VARCHAR(10)", nullable = false)
    private String soDT;
    @Column(name = "ngay_tao", columnDefinition = "DATE", nullable = false)
    private LocalDate ngayTao;
    @Column(name = "ngay_sinh", columnDefinition = "DATE", nullable = false)
    private LocalDate ngaySinh;
    @Column(name = "diem_tich_luy", nullable = false)
    private int diemTL;
    @Column(name = "trang_thai", columnDefinition = "BIT", nullable = false)
    private boolean trangThai;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ma_loai_kh", nullable = false)
    private LoaiKhachHang loaiKH;
}
