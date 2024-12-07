package focandlol.exception;

import focandlol.exception.errorcode.ErrorCode;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;
}
