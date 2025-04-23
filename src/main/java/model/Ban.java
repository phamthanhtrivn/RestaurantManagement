package model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity

public class Ban implements Serializable {

    @Id
    @Column(columnDefinition = "NVARCHAR(5)")
    @EqualsAndHashCode.Include
    private String maBan;
    private int soBan;
    private int soGhe;
    private int tinhTrang;

    @ManyToOne
    @JoinColumn(name = "loaiBanID")
    private LoaiBan loaiBan;

    public Ban(String maBan) {
        this.maBan = maBan;
    }
}
