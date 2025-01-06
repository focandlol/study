package focandlol.resproject.store.dto;

import focandlol.resproject.manager.entity.ManagerEntity;
import focandlol.resproject.store.entity.StoreEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * 가게 추가 dto
 */
public class AddStoreDto {

    /**
     * 가게 추가 요청
     */
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
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


        /**
         * 가게 추가 request 데이터를 기반으로 StoreEntity 객체를 생성
         *
         * @param manager 가게 주인(매니저) (ManagerEntity 객체)
         * @return StoreEntity 객체
         */
        public StoreEntity toEntity(ManagerEntity manager) {
            return StoreEntity.builder()
                    .storeName(this.storeName)
                    .storePhoneNumber(this.storePhoneNumber)
                    .description(this.description)
                    .location(this.location)
                    .cesco(this.cesco)
                    .totalSeat(this.totalSeat)
                    .manager(manager)
                    .build();
        }
    }

    /**
     * 가게 추가 응답
     */
    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
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
         * 가게 설명
         */
        private String description;

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
         * StoreEntity 객체를 기반으로 Response 객체를 생성
         *
         * @param store StoreEntity 객체
         * @return Response 객체
         */
        public static Response from(StoreEntity store) {
            return Response.builder()
                    .storeId(store.getId())
                    .storeName(store.getStoreName())
                    .storePhoneNumber(store.getStorePhoneNumber())
                    .location(store.getLocation())
                    .cesco(store.isCesco())
                    .description(store.getDescription())
                    .totalSeat(store.getTotalSeat())
                    .managerId(store.getManager().getId())
                    .build();
        }
    }

}
