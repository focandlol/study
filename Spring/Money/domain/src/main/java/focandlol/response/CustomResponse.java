package focandlol.response;

import lombok.Getter;

@Getter
public class CustomResponse<T> {
    private final T data;
    private final String code;
    private final String message;

    public CustomResponse(T data, ResponseCode responseCode) {
        this.data = data;
        this.code = responseCode.getCode();
        this.message = responseCode.getMessage();
    }
}
