package focandlol.reservation.dto.store;

import focandlol.reservation.entity.StoreEntity;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StoreDto {
    private Long storeId;

    private String storeName;

    private String storePhoneNumber;

    private String location;

    public static StoreDto from(StoreEntity store) {
        return StoreDto.builder()
                .storeId(store.getId())
                .storeName(store.getStoreName())
                .storePhoneNumber(store.getStorePhoneNumber())
                .location(store.getLocation())
                .build();
    }
}
