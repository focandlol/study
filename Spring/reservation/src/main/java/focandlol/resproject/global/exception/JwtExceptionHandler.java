package focandlol.resproject.global.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
@Order(1)
public class JwtExceptionHandler {

    @ExceptionHandler(SignatureException.class)
    public ResponseEntity<ErrorResponse> handleSignatureException(SignatureException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.SIGNATURE_IS_NOT_VALID));
    }

    @ExceptionHandler(MalformedJwtException.class)
    public ResponseEntity<ErrorResponse> handleMalformedJwtException(MalformedJwtException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.WRONG_TOKEN));
    }

    @ExceptionHandler(ExpiredJwtException.class)
    public ResponseEntity<ErrorResponse> handleExpiredJwtException(ExpiredJwtException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.EXPIRED_TOKEN));
    }

    @ExceptionHandler(UnsupportedJwtException.class)
    public ResponseEntity<ErrorResponse> handleUnsupportedJwtException(UnsupportedJwtException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.EXPIRED_TOKEN));
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleExpiredIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.status(UNAUTHORIZED).body(new ErrorResponse(ErrorCode.EXPIRED_TOKEN));
    }
}
