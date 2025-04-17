package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
public class KhuyenMai {
    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "NVARCHAR(5)")
    private String maKM;
    @Column(columnDefinition = "NVARCHAR(40)")
    private String tenKM;
    private int giamGia;
    private LocalDate ngayBD;
    private LocalDate ngayKT;
}
