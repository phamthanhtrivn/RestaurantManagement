package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@ToString
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "loai_nhan_vien")
public class LoaiNhanVien {
    @Id
    @Column(name = "ma_loai_nv", columnDefinition = "CHAR(5)")
    @EqualsAndHashCode.Include
    private String maLoaiNV;
    @Column(name = "vi_tri", columnDefinition = "NVARCHAR(50)", nullable = false)
    private String viTri;
}
