package focandlol.reservation.store.dto;

import focandlol.reservation.store.entity.StoreEntity;
import lombok.*;

import java.math.BigDecimal;

/**
 * 가게 세부 정보 dto
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDetailDto {

    private Long id;

    private String storeName;

    private String storePhoneNumber;

    private String description;

    private BigDecimal star;

    private String location;

    private boolean cesco;

    private Integer totalSeat;

    private Long managerId;

    public static StoreDetailDto from(StoreEntity storeEntity) {
        return StoreDetailDto.builder()
                .id(storeEntity.getId())
                .storeName(storeEntity.getStoreName())
                .storePhoneNumber(storeEntity.getStorePhoneNumber())
                .description(storeEntity.getDescription())
                .star(storeEntity.getStar())
                .location(storeEntity.getLocation())
                .cesco(storeEntity.isCesco())
                .totalSeat(storeEntity.getTotalSeat())
                .managerId(storeEntity.getManager().getId())
                .build();
    }
}
