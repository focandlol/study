package focandlol.controller;

import focandlol.dto.UserInfoDto;
import focandlol.response.CustomResponse;
import focandlol.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static focandlol.response.ResponseCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/fintech/v1/user")
public class UserController {
    private final UserService userService;

    @PostMapping("/information")
    public ResponseEntity<CustomResponse> user(@RequestBody UserInfoDto.Request request) {
        return ResponseEntity.ok(new CustomResponse<>(userService.save(request),SUCCESS));
    }

    @GetMapping("/private-info/{userKey}")
    public ResponseEntity<CustomResponse> privateInfo(@PathVariable String userKey) {
        return ResponseEntity.ok(new CustomResponse(userService.findUserInfo(userKey),
                SUCCESS));
    }
}
