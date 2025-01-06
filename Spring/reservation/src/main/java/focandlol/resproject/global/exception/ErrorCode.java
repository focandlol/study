package focandlol.resproject.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

/**
 * 에러 코드 클래스
 */
@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 사용자 에러
     */
    USERNAME_ALREADY_USE("이미 사용중인 이메일 입니다.", BAD_REQUEST),
    CUSTOMER_NOT_FOUND("해당 고객이 없습니다", BAD_REQUEST),
    MANAGER_NOT_FOUND("해당 매니저가 없습니다", BAD_REQUEST),
    ANOTHER_CUSTOMER("고객이 다릅니다", BAD_REQUEST),
    ANOTHER_MANAGER("매니저가 다릅니다", BAD_REQUEST),
    FAILED_LOGIN("로그인 실패", BAD_REQUEST),

    /**
     * 예약 에러
     */
    RESERVATION_IS_OVER("해당 인원수 만큼의 자리가 없습니다", BAD_REQUEST),
    RESERVATION_NOT_FOUND("해당 예약이 없습니다", BAD_REQUEST),
    RESERVATION_SAME_TYPE("이미 같은 예약 상태 입니다", BAD_REQUEST),
    RESERVATION_IS_NOT_APPROVE("승인된 예약이 아닙니다.", BAD_REQUEST),
    ARRIVE_TIME_IS_OVER("예약 시간 10분전에 도착해야합니다", BAD_REQUEST),
    ARRIVE_TOO_EARLY("예약 시간 30분 전부터 입장 가능합니다", BAD_REQUEST),
    INVALID_RESERVATION_STATUS("해당 예약 상태로 변경할 수 없습니다.", BAD_REQUEST),

    /**
     * 가게 에러
     */
    ALREADY_EXISTS_STORE("가게가 이미 있습니다.", BAD_REQUEST),
    STORE_NOT_FOUND("해당 가게가 없습니다", BAD_REQUEST),
    ANOTHER_STORE("예약한 가게와 다릅니다.", BAD_REQUEST),

    /**
     * 리뷰 에러
     */
    REVIEW_NOT_FOUND("해당 리뷰를 찾을 수 없습니다.", BAD_REQUEST),
    NOT_VISITED("방문한 고객만 리뷰를 작성할 수 있습니다.", BAD_REQUEST),

    /**
     * 인증 및 권한 에러
     */
    WRONG_TOKEN("유효하지 않은 토큰입니다", UNAUTHORIZED),
    EXPIRED_TOKEN("만료된 토큰입니다.", UNAUTHORIZED),
    SIGNATURE_IS_NOT_VALID("잘못된 시그니처입니다", UNAUTHORIZED),
    ACCESS_DENIED("잘못된 권한입니다.", UNAUTHORIZED),
    UNSUPPORTED_TOKEN("지원하지 않는 토큰 형식입니다.", UNAUTHORIZED),
    EMPTY_TOKEN("토큰이 비어있습니다.", UNAUTHORIZED),


    /**
     * 처리 관련 에러
     */
    FAILED_TO_PARSE_REQUEST_BODY("요청 본문 처리 중 오류가 발생했습니다.", BAD_REQUEST),
    TYPE_MISMATCH("타입이 다릅니다", BAD_REQUEST),
    METHOD_ARGUMENT_NOT_VALID("잘못된 데이터입니다", BAD_REQUEST),

    /**
     * 시스템 에러
     */
    INTERNAL_SERVER_ERROR("서버 오류입니다.", HttpStatus.INTERNAL_SERVER_ERROR);

    /**
     * 에러 메시지
     */
    private final String description;
    /**
     * 상태 코드
     */
    private final HttpStatus status;
}
