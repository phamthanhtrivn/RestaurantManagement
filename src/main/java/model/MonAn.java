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
public class MonAn implements Serializable {

    @Id
    @Column(columnDefinition = "NVARCHAR(5)")
    @EqualsAndHashCode.Include
    private String maMA;
    @Column(columnDefinition = "NVARCHAR(40)")
    private String tenMA;
    @Column(columnDefinition = "VARCHAR(100)")
    private String hinhAnh;
    private double gia;
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "loaiMonAnID")
    private LoaiMonAn loaiMonAn;
    @ManyToOne
    @JoinColumn(name = "khuyenMaiID")
    private KhuyenMai khuyenMai;
    
    public MonAn(String maMA) {
        this.maMA = maMA;
    }
}
