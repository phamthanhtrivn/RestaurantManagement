package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class KhuyenMai {
    @Id
    private String maKM;
    private String tenKM;
    private int giamGia;
    private LocalDate ngayBD;
    private LocalDate ngayKT;
}
