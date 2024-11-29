package focandlol.weather.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
public class ErrorResponse {
    private int code;
    private String message;
}
