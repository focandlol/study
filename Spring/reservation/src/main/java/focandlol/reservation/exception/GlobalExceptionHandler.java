package focandlol.reservation.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.*;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler{

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<ErrorResponse> handleCustomException(CustomException e){
        log.error(e.getMessage());
        log.info("CustomException handler 호출");
        return ResponseEntity.status(e.getStatus()).body(new ErrorResponse(e.getErrorCode()));
    }

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.SIGNATURE_IS_NOT_VALID));
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.INCORRECT_TOKEN));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        System.out.println("ExpiredJwtException");
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.EXPIRED_TOKEN));
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(AccessDeniedException e) {
        e.printStackTrace();
        System.out.println("AccessDeniedException");
        System.out.println(e.getStackTrace());
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("Authentication: {}", authentication);
        log.info("Is authenticated: {}", authentication.isAuthenticated());
        log.info("Authorities: {}", authentication.getAuthorities());
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.ACCESS_DENIED));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception e){
        log.error(e.getMessage());
        log.error("Exception class: {}", e.getClass().getName());
        log.info("Exception handler 호출");
        return ResponseEntity.status(INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(ErrorCode.INTERNAL_SERVER_ERROR));
    }
}
