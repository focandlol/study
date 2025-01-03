package focandlol.reservation.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USERNAME_ALREADY_USE("이미 사용중인 이메일 입니다.", BAD_REQUEST),
    MANAGER_NOT_FOUND("해당 매니저가 없습니다",BAD_REQUEST),
    ALREADY_EXISTS_STORE("가게가 이미 있습니다.",BAD_REQUEST),
    STORE_NOT_FOUND("해당 가게가 없습니다",BAD_REQUEST),
    ANOTHER_MANAGER("가게 주인이 다릅니다",BAD_REQUEST),
    CUSTOMER_NOT_FOUND("해당 고객이 없습니다",BAD_REQUEST),
    RESERVATION_IS_OVER("해당 인원수 만큼의 자리가 없습니다",BAD_REQUEST),
    RESERVATION_NOT_FOUND("해당 예약이 없습니다",BAD_REQUEST),
    ANOTHER_CUSTOMER("고객이 다릅니다",BAD_REQUEST),
    RESERVATION_SAME_TYPE("이미 같은 예약 상태 입니다",BAD_REQUEST),
    RESERVATION_IS_NOT_APPROVE("승인된 예약이 아닙니다.",BAD_REQUEST),
    ARRIVE_TIME_IS_OVER("예약 시간 10분전에 도착해야합니다", BAD_REQUEST),
    WRONG_TOKEN("잘못된 토큰입니다",BAD_REQUEST),
    INTERNAL_SERVER_ERROR("서버 오류입니다.",HttpStatus.INTERNAL_SERVER_ERROR),

    REVIEW_NOT_FOUND("해당 리뷰를 찾을 수 없습니다.",BAD_REQUEST),
    TYPE_MISMATCH("타입이 다릅니다",BAD_REQUEST),

    SIGNATURE_IS_NOT_VALID("잘못된 시그니처입니다",BAD_REQUEST),
    INCORRECT_TOKEN("토큰이 아닙니다.",UNAUTHORIZED),
    EXPIRED_TOKEN("만료된 토큰입니다.",UNAUTHORIZED),
    ACCESS_DENIED("잘못된 권한입니다.",UNAUTHORIZED),

    FAILED_LOGIN("로그인 실패",BAD_REQUEST),

    ;

    private final String description;
    private final HttpStatus status;
}
