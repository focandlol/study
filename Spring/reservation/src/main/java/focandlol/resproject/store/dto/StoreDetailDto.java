package focandlol.resproject.store.dto;

import focandlol.resproject.store.entity.StoreEntity;
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

    /**
     * 가게 id
     */
    private Long id;

    /**
     * 가게 이름
     */
    private String storeName;

    /**
     * 가게 전화번호
     */
    private String storePhoneNumber;

    /**
     * 가게 설명
     */
    private String description;

    /**
     * 별점
     */
    private BigDecimal star;

    /**
     * 가게 위치
     */
    private String location;

    /**
     * 세스코 사용 여부
     */
    private boolean cesco;

    /**
     * 총 예약 가능 인원수
     */
    private Integer totalSeat;

    /**
     * 매니저 id
     */
    private Long managerId;

    /**
     * StoreEntity 객체를 기반으로 StoreDetailDto 객체를 생성
     *
     * @param storeEntity StoreEntity 객체
     * @return StoreDetailDto 객체
     */
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
