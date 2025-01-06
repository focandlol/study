package focandlol.reservation.manager.dto;

import focandlol.reservation.manager.entity.ManagerEntity;
import focandlol.reservation.auth.type.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 가게 주인 회원가입
 */
public class ManagerSignUpDto {

    /**
     * 가게 주인 회원가입 요청
     */
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

    /**
     * 가게 주인 회원가입 응답
     */
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
