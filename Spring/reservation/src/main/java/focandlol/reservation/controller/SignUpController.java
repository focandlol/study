package focandlol.reservation.controller;

import focandlol.reservation.dto.CustomerSignUpDto;
import focandlol.reservation.dto.ManagerSignUpDto;
import focandlol.reservation.service.SignUpService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/signUp")
public class SignUpController {

    private final SignUpService signUpService;

    @PostMapping("customer")
    public ResponseEntity<?> signUp(@RequestBody @Valid CustomerSignUpDto.Request request) {
        return ResponseEntity.ok().body(signUpService.customerSignUp(request));
    }

    @PostMapping("manager")
    public ResponseEntity<?> signUp(@RequestBody @Valid ManagerSignUpDto.Request request) {
        return ResponseEntity.ok().body(signUpService.managerSignUp(request));
    }
}
