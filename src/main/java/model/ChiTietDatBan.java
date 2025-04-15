package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

@Entity
public class ChiTietDatBan {
    @Id
    @ManyToOne
    @JoinColumn(name = "donDatBanID")
    private DonDatBan donDatBan;
    @Id
    @ManyToOne
    @JoinColumn(name = "monAnID")
    private MonAn monAn;

    private int soLuong;
    private double thanhTien;
    private double giaSauGiam;
}
