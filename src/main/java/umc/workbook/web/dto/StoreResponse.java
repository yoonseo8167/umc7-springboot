package umc.workbook.web.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import umc.workbook.domain.Store;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StoreResponse {

    private Long id;
    private String name;
    private String address;
    private Float score;
    private String regionName;

    public static StoreResponse fromEntity(Store store) {
        return new StoreResponse(
                store.getId(),
                store.getName(),
                store.getAddress(),
                store.getScore(),
                store.getRegion() != null ? store.getRegion().getName() : null
        );
    }
}
