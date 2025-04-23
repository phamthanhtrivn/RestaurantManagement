package model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import java.util.Objects;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString

@Entity
@IdClass(ChiTietDatBan.ChiTietDatBanId.class)
public class ChiTietDatBan implements Serializable {

    @Id
    @ManyToOne(cascade = CascadeType.ALL)
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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @EqualsAndHashCode
    public static class ChiTietDatBanId implements Serializable {
        private DonDatBan donDatBan;
        private MonAn monAn;

    }
}
