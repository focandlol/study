package focandlol.exception;

import focandlol.exception.error.BaseCustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(BaseCustomException.class)
    public ErrorResponse handleCustomException(BaseCustomException e) {
        log.error(e.getMessage());
        return new ErrorResponse(e.getErrorCode(),e.getErrorMessage());
    }
}
