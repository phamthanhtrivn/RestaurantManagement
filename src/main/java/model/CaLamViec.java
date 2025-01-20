package model;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

import java.time.LocalDateTime;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "ca_lam_viec")
public class CaLamViec {

    @Id
    @Column(name = "ma_ca")
    @EqualsAndHashCode.Include
    private int maCa;
    @Column(name = "ten_ca", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenCa;
    @Column(name = "thoi_gian_bd", nullable = false)
    private LocalDateTime thoiGianBD;
    @Column(name = "thoi_gian_kt", nullable = false)
    private LocalDateTime thoiGianKT;
}
