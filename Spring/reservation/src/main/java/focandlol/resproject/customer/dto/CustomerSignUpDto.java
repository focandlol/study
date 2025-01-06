package focandlol.resproject.customer.dto;

import focandlol.resproject.auth.type.UserType;
import focandlol.resproject.customer.entity.CustomerEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 고객 회원가입 dto
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
    public static class Request {

        /**
         * 고객 이메일
         */
        @Email
        @NotBlank
        private String username;

        /**
         * 고객 비밀번호
         */
        @NotBlank
        private String password;

        /**
         * 고객 핸드폰번호
         */
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
    public static class Response {

        /**
         * 고객 이메일
         */
        @Email
        @NotBlank
        private String username;

        /**
         * 고객 핸드폰번호
         */
        @NotBlank
        private String phoneNumber;

        /**
         * 사용자 유형
         */
        @NotNull
        private UserType type;

        /**
         * CustomerEntity 객체를 Response dto로 변환.
         *
         * @param customer 변환할 CustomerEntity 객체
         * @return Response dto
         */
        public static Response from(CustomerEntity customer) {
            return new Response(customer.getUsername(), customer.getPhoneNumber(), customer.getUserType());
        }
    }
}
