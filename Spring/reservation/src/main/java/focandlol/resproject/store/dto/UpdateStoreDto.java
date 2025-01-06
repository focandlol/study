package focandlol.resproject.store.dto;

import focandlol.resproject.store.entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 가게 수정 dto
 */
public class UpdateStoreDto {

    /**
     * 가게 수정 요청
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Request {

        /**
         * 가게 이름
         */
        @NotBlank
        private String storeName;

        /**
         * 가게 전화번호
         */
        private String storePhoneNumber;

        /**
         * 가게 설명
         */
        @NotBlank
        private String description;

        /**
         * 가게 위치
         */
        @NotBlank
        private String location;

        /**
         * 세스코 사용 여부
         */
        private boolean cesco;

        /**
         * 총 예약 가능 인원수
         */
        @NotNull
        private Integer totalSeat;
    }

    /**
     * 가게 수정 응답
     */
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    @Builder
    public static class Response {

        /**
         * 가게 이름
         */
        private String storeName;

        /**
         * 가게 전화번호
         */
        private String storePhoneNumber;

        /**
         * 가게 위치
         */
        private String location;

        /**
         * 가게 설명
         */
        private String description;

        /**
         * 세스코 사용 여부
         */
        private boolean cesco;

        /**
         * 총 예약 가능 인원수
         */
        private Integer totalSeat;

        /**
         * StoreEntity 객체를 기반으로 Response 객체를 생성
         *
         * @param store StoreEntity 객체
         * @return StoreDetailDto 객체
         */
        public static Response from(StoreEntity store) {
            return Response.builder()
                    .storeName(store.getStoreName())
                    .storePhoneNumber(store.getStorePhoneNumber())
                    .location(store.getLocation())
                    .description(store.getDescription())
                    .cesco(store.isCesco())
                    .totalSeat(store.getTotalSeat())
                    .build();
        }
    }
}
