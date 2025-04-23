package model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString


@Entity
public class NhanVien implements Serializable {
    @Id
    @Column(columnDefinition = "NVARCHAR(8)")
    @EqualsAndHashCode.Include
    private String maNV;
    @Column(columnDefinition = "NVARCHAR(40)")
    private String hoTenNV;
    @Column(columnDefinition = "NVARCHAR(20)")
    private String CCCD;
    @Column(columnDefinition = "VARCHAR(10)")
    private String soDT;
    @Column(columnDefinition = "VARCHAR(64)")
    private String matKhau;
    @Column(columnDefinition = "VARCHAR(50)")
    private String email;
    @Column(columnDefinition = "VARCHAR(6)")
    private String maXacThuc;
    private LocalDate ngaySinh;
    private boolean trangThai;

    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    @ManyToOne
    @JoinColumn(name = "loaiNhanVienID")
    private LoaiNhanVien loaiNhanVien;
    
    public NhanVien(String maNV) {
        this.maNV = maNV;
    }
}
