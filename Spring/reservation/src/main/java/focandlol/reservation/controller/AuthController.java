package focandlol.reservation.controller;

import focandlol.reservation.exception.CustomException;
import focandlol.reservation.exception.ErrorCode;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
    @GetMapping("/hello")
    public String hello() {
        throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
}
