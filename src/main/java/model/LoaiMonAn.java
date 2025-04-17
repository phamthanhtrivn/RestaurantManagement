package model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
public class LoaiMonAn {
    @Id
    @Column(columnDefinition = "NVARCHAR(5)")
    @EqualsAndHashCode.Include
    private String maLoaiMA;
    @Column(columnDefinition = "NVARCHAR(40)")
    private String tenLoaiMA;
}
