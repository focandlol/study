package focandlol.reservation.store.dto;

import focandlol.reservation.store.entity.StoreEntity;
import lombok.*;

import java.math.BigDecimal;

/**
 * 가게 목록 dto
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {
    private Long storeId;

    private String storeName;

    private String storePhoneNumber;

    private BigDecimal star;

    private String location;

    public static StoreDto from(StoreEntity store) {
        return StoreDto.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .storePhoneNumber(store.getStorePhoneNumber())
                .star(store.getStar())
                .location(store.getLocation())
                .build();
    }
}
