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
public class LoaiNhanVien implements Serializable {
    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "NVARCHAR(4)")
    private String maLoaiNV;
    @Column(columnDefinition = "NVARCHAR(20)")
    private String viTri;
}
