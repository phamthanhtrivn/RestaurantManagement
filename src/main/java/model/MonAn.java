package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class MonAn {

    @Id
    private String maMA;
    private String tenMA;
    private String hinhAnh;
    private double gia;
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "loaiMonAnID")
    private LoaiMonAn loaiMonAn;
    @ManyToOne
    @JoinColumn(name = "khuyenMaiID")
    private KhuyenMai khuyenMai;
}
