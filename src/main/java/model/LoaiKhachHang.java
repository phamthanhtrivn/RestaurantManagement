package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import java.io.Serializable;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
public class LoaiKhachHang implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "NVARCHAR(5)")
    private String maLoaiKH;
    @Column(columnDefinition = "NVARCHAR(30)")
    private String tenLoaiKH;
    private int giamGiaTV;
}
