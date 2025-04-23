package model;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
public class KhachHang implements Serializable{
    @Id
    @EqualsAndHashCode.Include
    private String maKH;
    @Column(columnDefinition = "VARCHAR(40)")
    private String tenKH;
    @Column(columnDefinition = "VARCHAR(10)")
    private String soDT;
    private LocalDate ngayTao;
    private int diemTL;
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "loaiKhachHangID")
    private LoaiKhachHang loaiKhachHang;
}
