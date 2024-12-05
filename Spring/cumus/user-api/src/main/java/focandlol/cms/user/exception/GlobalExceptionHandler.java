package focandlol.cms.user.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ExceptionResponse> customRequestException(CustomException e) {
        log.error("api exception: {}",e.getErrorCode());
        return ResponseEntity.badRequest().body(new ExceptionResponse(e.getErrorCode().getDetail(),e.getErrorCode()));
    }

    @Getter
    @AllArgsConstructor
    public static class ExceptionResponse{
        private String message;
        private ErrorCode errorCode;
    }
}
