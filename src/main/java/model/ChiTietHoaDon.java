package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
@IdClass(ChiTietHoaDon.ChiTietHoaDonId.class)
public class ChiTietHoaDon implements Serializable {
    @Id
    @ManyToOne
    @JoinColumn(name = "hoaDonID")
    @EqualsAndHashCode.Include
    private HoaDon hoaDon;
    @Id
    @ManyToOne
    @JoinColumn(name = "monAnID")
    @EqualsAndHashCode.Include
    private MonAn monAn;
    private int soLuong;
    private double thanhTien;
    private double giaSauGiam;


    @EqualsAndHashCode
    @NoArgsConstructor
    @AllArgsConstructor
    public static class ChiTietHoaDonId implements Serializable {

        private HoaDon hoaDon;
        private MonAn monAn;
    }
}
