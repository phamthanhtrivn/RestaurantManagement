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
    @JoinColumn(name = "donDatBanID", referencedColumnName = "maDDB", insertable = false, updatable = false)
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
    public static class ChiTietDatBanId implements Serializable {

        private DonDatBan donDatBan;
        private MonAn monAn;

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            ChiTietDatBanId that = (ChiTietDatBanId) o;
            return Objects.equals(donDatBan.getMaDDB(), that.donDatBan.getMaDDB())
                    && Objects.equals(monAn.getMaMA(), that.monAn.getMaMA());
        }

        @Override
        public int hashCode() {
            return Objects.hash(donDatBan.getMaDDB(), monAn.getMaMA());
        }
    }
}
