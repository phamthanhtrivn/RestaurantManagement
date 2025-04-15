package model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString


@Entity
public class NhanVien {
    @Id
    private String maNV;
    private String hoTenNV;
    private String CCCD;
    private String soDT;
    private String matKhau;
    private String email;
    private String maXacThuc;
    private LocalDate ngaySinh;
    private boolean trangThai;

    @Enumerated(EnumType.STRING)
    private GioiTinh gioiTinh;

    @ManyToOne
    @JoinColumn(name = "loaiNhanVienID")
    private LoaiNhanVien loaiNhanVien;
}
