package model;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "chi_tiet_ca_lam_viec")
public class ChiTietCaLamViec {
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_nhan_vien", nullable = false)
    private NhanVien nhanVien;
    @Id
    @ManyToOne
    @JoinColumn(name = "ma_ca", nullable = false)
    private CaLamViec caLamViec;
    @ToString.Exclude
    private LocalDate ngayLamViec;
}
