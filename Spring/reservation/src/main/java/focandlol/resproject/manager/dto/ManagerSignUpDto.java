package focandlol.resproject.manager.dto;

import focandlol.resproject.auth.type.UserType;
import focandlol.resproject.manager.entity.ManagerEntity;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

/**
 * 매니저 회원가입 dto
 */
public class ManagerSignUpDto {

    /**
     * 매니저 회원가입 요청
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Request {

        /**
         * 이메일
         */
        @Email
        @NotBlank
        private String username;

        /**
         * 비밀번호
         */
        @NotBlank
        private String password;

        /**
         * 핸드폰 번호
         */
        @NotBlank
        private String phoneNumber;
    }

    /**
     * 매니저 회원가입 응답
     */
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Response {
        /**
         * 이메일
         */
        @Email
        @NotBlank
        private String username;

        /**
         * 핸드폰 번호
         */
        @NotBlank
        private String phoneNumber;

        /**
         * 사용자 유형
         */
        @NotNull
        private UserType type;

        /**
         * ManagerEntity 객체를 Response dto로 변환.
         *
         * @param manager 변환할 ManagerEntity 객체
         * @return Response dto
         */
        public static Response from(ManagerEntity manager) {
            return new Response(manager.getUsername(), manager.getPhoneNumber(), manager.getUserType());
        }
    }
}
