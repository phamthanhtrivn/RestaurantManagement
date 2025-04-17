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
public class LoaiBan {
    @Id
    @EqualsAndHashCode.Include
    @Column(columnDefinition = "NVARCHAR(5)")
    private String maLB;
    @Column(columnDefinition = "NVARCHAR(10)")
    private String tenLB;
}
