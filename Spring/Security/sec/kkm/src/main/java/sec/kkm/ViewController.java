package sec.kkm;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    //@GetMapping("/")

    @GetMapping("/form")
    public String form() {
        return "form";
    }

    @GetMapping("/cookie")
    public String cookie(){
        return "cookie";
    }

    @GetMapping("/method")
    public String method(){
        return "method";
    }
}
