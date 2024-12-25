package focandlol.reservation.controller;

import focandlol.reservation.dto.CustomerSignUpDto;
import focandlol.reservation.service.SignUpService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("customer")
    public ResponseEntity<?> signUp(@RequestBody CustomerSignUpDto.Request request) {
        return ResponseEntity.ok().body(signUpService.customerSignUp(request));
    }
}
