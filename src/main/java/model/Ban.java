package model;

import jakarta.persistence.*;
import lombok.*;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class Ban {
    @Id
    @Column(name = "ma_ban", columnDefinition = "CHAR(5)")
    @EqualsAndHashCode.Include
    private String maBan;
    @Column(name = "so_ban", nullable = false)
    private int soBan;
    @Column(name = "so_ghe", nullable = false)
    private int soGhe;
    @Column(name = "tinh_trang", columnDefinition = "BIT",nullable = false)
    private boolean tinhTrang;

    @ToString.Exclude
    @ManyToOne
    @JoinColumn(name = "ma_loai_ban", nullable = false)
    private LoaiBan loaiBan;
}
