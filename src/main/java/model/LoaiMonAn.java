package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class LoaiMonAn {
    @Id
    private String maLoaiMA;
    private String tenLoaiMA;
}
