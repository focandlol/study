package focandlol.openaitest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MainController {

    private final ApiService apiService;

    @GetMapping("/api")
    public String api() {
        return apiService.generateRecipeInKorean("감자,돼지고기,파,양파, 매운, 30분 이내, 한식, 매우 특이한 음식",1.0);
    }
}
