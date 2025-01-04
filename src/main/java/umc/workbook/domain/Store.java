package umc.workbook.domain;

import jakarta.persistence.*;
import lombok.*;
import umc.workbook.domain.common.BaseEntity;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Store extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 50)
    private String address;

    private Float score;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @Override
    public String toString() {
        return "Store {"+
                "id=" + id +
                ", name='" + name + '\''+
                ", address='" + address +'\''+
                ", score=" + score +
                ", region=" + (region != null ? region.getName() : "N/A") +
                '}';
    }
}