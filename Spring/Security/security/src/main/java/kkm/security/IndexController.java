package kkm.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class IndexController {


    private final SessionInfoService sessionInfoService;

    public IndexController(SessionInfoService sessionInfoService) {
        this.sessionInfoService = sessionInfoService;
    }


//    @Autowired
//    SecurityContextService securityContextService;

//    @GetMapping("/")
//    public String index(@CurrentSecurityContext SecurityContext context){
//        System.out.println("context = " + context.getAuthentication().getName());
//        return "index";
//    }

    @GetMapping("/")
    public String index(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication = " + authentication);
        System.out.println("customUserDetails.getAccountDto() = " + customUserDetails.getAccountDto());
        //securityContextService.getContext();

        return "index";
    }

    @GetMapping("/sessionInfo")
    public String sessionInfo(){
        sessionInfoService.sessionInfo();
        return "sessionInfo";
    }

    @GetMapping("/loginPage")
    public String loginPage(){
        return "loginPage";
    }

    @GetMapping("/home")
    public String home(){
        return "home";
    }

    @GetMapping("/anonymous")
    public String anaonymous(){
        return "anonymous";
    }

    @GetMapping("/authentication")
    public String authentication(Authentication authentication){
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        principal.getAccountDto();
        if(authentication instanceof AnonymousAuthenticationToken){
            return "anonymous";
        }
        return "null";
    }

    @GetMapping("/anonymousContext")
    public String anonymousContext(@CurrentSecurityContext SecurityContext context){
        return context.getAuthentication().getName();
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(){
        return "logout";
    }
    @GetMapping("/invalidSessionUrl")
    public String invalidSessionUrl(){
        return "invalidSessionUrl";
    }
    @GetMapping("/expiredSessionUrl")
    public String expiredSessionUrl(){
        return "expiredSessionUrl";
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/denied")
    public String denied(){
        return "denied";
    }

    @GetMapping("/api/users")
    public String users(){
        return "{\"name\":\"kkm\"}";
    }
}
