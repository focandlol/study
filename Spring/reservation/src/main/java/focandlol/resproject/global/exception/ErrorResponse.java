package focandlol.resproject.global.exception;

import lombok.*;
import org.springframework.http.HttpStatus;

/**
 * api에서 에러 정보 전달 클래스
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    /**
     * 에러 코드
     */
    private ErrorCode errorCode;

    /**
     * 에러 메시지
     */
    private String message;

    /**
     * 상태 코드
     */
    private HttpStatus status;

    public ErrorResponse(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getDescription();
        this.status = errorCode.getStatus();
    }
}
