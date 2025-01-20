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
@Table(name = "loai_mon_an")
public class LoaiMonAn {
    @Id
    @Column(name = "ma_loai_mon", columnDefinition = "CHAR(5)")
    @EqualsAndHashCode.Include
    private String maLoaiMon;
    @Column(name = "ten_loai_mon", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenLoaiMon;
}
