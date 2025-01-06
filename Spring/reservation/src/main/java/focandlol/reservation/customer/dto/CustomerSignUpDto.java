package focandlol.reservation.customer.dto;

import focandlol.reservation.customer.entity.CustomerEntity;
import focandlol.reservation.auth.type.UserType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

/**
 * 고객 회원가입
 */
public class CustomerSignUpDto {

    /**
     * 고객 회원가입 요청
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
     * 고객 회원가입 응답
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

        public static Response from(CustomerEntity customer){
            return new Response(customer.getUsername(), customer.getPhoneNumber(),customer.getUserType());
        }
    }
}
