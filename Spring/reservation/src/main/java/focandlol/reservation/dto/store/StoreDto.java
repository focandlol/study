package focandlol.reservation.dto.store;

import focandlol.reservation.entity.StoreEntity;
import lombok.*;

import java.math.BigDecimal;

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
