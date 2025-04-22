package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
@IdClass(ChiTietDatBan.ChiTietDatBanId.class)
public class ChiTietDatBan {
    @Id
    @ManyToOne
    @JoinColumn(name = "donDatBanID")
    @EqualsAndHashCode.Include
    private DonDatBan donDatBan;
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
    public static class ChiTietDatBanId {
        private DonDatBan donDatBan;
        private MonAn monAn;
    }
}
