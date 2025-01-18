package model;

import jakarta.persistence.*;
import lombok.*;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loai_khach_hang")
public class LoaiKhachHang {

    @Id
    @Column(name = "ma_loai_kh", columnDefinition = "CHAR(5)")
    @EqualsAndHashCode.Include
    private String maLoaiKH;
    @Column(name = "ten_loai_kh", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenLoaiKH;
    @Column(name = "giam_gia_tv", nullable = false)
    private int giamGiaTV;
    @Column(name = "giam_gia_sn", nullable = false)
    private int giamGiaSN;
}
