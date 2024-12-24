package focandlol.jwt.controller;

import focandlol.jwt.dto.JoinDto;
import focandlol.jwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import org.hibernate.mapping.Join;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class JoinController {

    private final JoinService joinService;

    @PostMapping("/join")
    public String joinProcess(@RequestBody JoinDto joinDto){
        joinService.joinProcess(joinDto);
        return "ok";
    }
}
