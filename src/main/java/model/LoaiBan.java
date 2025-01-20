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
@Table(name = "loai_ban")
public class LoaiBan {

    @Id
    @Column(name = "ma_loai_ban", columnDefinition = "CHAR(5)")
    @EqualsAndHashCode.Include
    private String maLB;
    @Column(name = "ten_loai_ban", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String tenLB;
}
