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
        @NotNull
        private Long managerId;

        @NotBlank
        private String storeName;

        private String storePhoneNumber;

        @NotBlank
        private String location;

        private boolean cesco;

        public StoreEntity toEntity(ManagerEntity manager){
            return StoreEntity.builder()
                    .storeName(this.storeName)
                    .storePhoneNumber(this.storePhoneNumber)
                    .location(this.location)
                    .cesco(this.cesco)
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

        private String location;

        private boolean cesco;

        private Long managerId;

        public static Response from(StoreEntity store){
            return Response.builder()
                    .storeId(store.getId())
                    .storeName(store.getStoreName())
                    .storePhoneNumber(store.getStorePhoneNumber())
                    .location(store.getLocation())
                    .cesco(store.isCesco())
                    .managerId(store.getManager().getId())
                    .build();
        }
    }

}
