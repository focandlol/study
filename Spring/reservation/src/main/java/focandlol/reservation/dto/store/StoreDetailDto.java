package focandlol.reservation.dto.store;

import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.ManagerEntity;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StoreDetailDto {

    private Long id;

    private String storeName;

    private String storePhoneNumber;

    private String location;

    private boolean cesco;

    private Integer totalSeat;

    private Long managerId;

    public static StoreDetailDto from(StoreEntity storeEntity) {
        return StoreDetailDto.builder()
                .id(storeEntity.getId())
                .storeName(storeEntity.getStoreName())
                .storePhoneNumber(storeEntity.getStorePhoneNumber())
                .location(storeEntity.getLocation())
                .cesco(storeEntity.isCesco())
                .totalSeat(storeEntity.getTotalSeat())
                .managerId(storeEntity.getManager().getId())
                .build();
    }
}