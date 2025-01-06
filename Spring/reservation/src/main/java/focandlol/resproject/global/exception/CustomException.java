package focandlol.resproject.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * 사용자 정의 예외 클래스
 */
@Getter
public class CustomException extends RuntimeException {
    /**
     * 예외의 에러 코드
     */
    private final ErrorCode errorCode;

    /**
     * 예외 메세지
     */
    private final String message;

    /**
     * 상태 코드
     */
    private final HttpStatus status;

    public CustomException(ErrorCode errorCode) {
        super(errorCode.getDescription());
        this.errorCode = errorCode;
        this.message = errorCode.getDescription();
        this.status = errorCode.getStatus();
    }
}
