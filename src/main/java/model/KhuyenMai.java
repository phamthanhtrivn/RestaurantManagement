package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "khuyen_mai")
public class KhuyenMai {
    @Id
    @Column(name = "ma_khuyen_mai", columnDefinition = "CHAR(5)")
    @EqualsAndHashCode.Include
    private String maKM;
    @Column(name = "ten_khuyen_mai", columnDefinition = "NVARCHAR(100)", nullable = false)
    private String tenKM;
    @Column(name = "ngay_bat_dau", columnDefinition = "DATE", nullable = false)
    private LocalDate ngayBD;
    @Column(name = "ngay_ket_thuc", columnDefinition = "DATE", nullable = false)
    private LocalDate ngayKT;
    @Column(name = "giam_gia", nullable = false)
    private int giamGia;
}
