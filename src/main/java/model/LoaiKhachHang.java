package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class LoaiKhachHang {
    @Id
    private String maLoaiKH;
    private String tenLoaiKH;
    private int giamGiaTV;
}
