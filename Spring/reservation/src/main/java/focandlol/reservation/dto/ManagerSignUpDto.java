package focandlol.reservation.dto;

import focandlol.reservation.entity.auth.ManagerEntity;
import focandlol.reservation.type.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

public class ManagerSignUpDto {

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request{

        @Email
        @NotBlank
        private String username;

        @NotBlank
        private String password;

        @NotBlank
        private String phoneNumber;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response{
        @Email
        @NotBlank
        private String username;

        @NotBlank
        private String phoneNumber;

        @NotBlank
        private UserType type;

        public static Response from(ManagerEntity manager){
            return new Response(manager.getUsername(), manager.getPhoneNumber(),manager.getUserType());
        }
    }
}
