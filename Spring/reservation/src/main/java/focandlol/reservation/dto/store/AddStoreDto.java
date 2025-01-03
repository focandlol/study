package focandlol.reservation.dto.store;

import focandlol.reservation.entity.StoreEntity;
import focandlol.reservation.entity.auth.ManagerEntity;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class AddStoreDto {

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{
        @NotBlank
        private String storeName;

        private String storePhoneNumber;

        @NotBlank
        private String description;

        @NotBlank
        private String location;

        private boolean cesco;

        @NotNull
        private Integer totalSeat;

        public StoreEntity toEntity(ManagerEntity manager){
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

    @Getter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        private Long storeId;

        private String storeName;

        private String storePhoneNumber;

        private String description;

        private String location;

        private boolean cesco;

        private Integer totalSeat;

        private Long managerId;

        public static Response from(StoreEntity store){
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
