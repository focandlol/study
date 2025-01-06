package focandlol.resproject.store.dto;

import focandlol.resproject.store.entity.StoreEntity;
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
    /**
     * 가게 id
     */
    private Long storeId;

    /**
     * 가게 이름
     */
    private String storeName;

    /**
     * 가게 전화번호
     */
    private String storePhoneNumber;

    /**
     * 별점
     */
    private BigDecimal star;

    /**
     * 가게 위치
     */
    private String location;

    /**
     * StoreEntity 객체를 기반으로 StoreDto 객체를 생성
     *
     * @param store StoreEntity 객체
     * @return StoreDto 객체
     */
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
