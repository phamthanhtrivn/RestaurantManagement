package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class KhachHang {
    @Id
    private String maKH;
    private String tenKH;
    private String soDT;
    private LocalDate ngayTao;
    private int diemTL;
    private boolean trangThai;

    @ManyToOne
    @JoinColumn(name = "loaiKhachHangID")
    private LoaiKhachHang loaiKhachHang;
}
