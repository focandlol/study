package focandlol.weather.error;

import focandlol.weather.WeatherApplication;
import focandlol.weather.dto.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final Logger logger = LoggerFactory.getLogger(WeatherApplication.class);

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(DiaryException.class)
    public ErrorResponse handleAllException(DiaryException e){
        logger.error("error 발생 " + e.getMessage());
        return new ErrorResponse(e.getErrorCode(),e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ErrorResponse handleAccountException(Exception e) {
        logger.error("error 발생 " + e.getMessage());
        return new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR,e.getMessage());
    }
}
