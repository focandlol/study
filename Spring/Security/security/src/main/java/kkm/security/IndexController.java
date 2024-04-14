package kkm.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

//    @Autowired
//    SecurityContextService securityContextService;

//    @GetMapping("/")
//    public String index(@CurrentSecurityContext SecurityContext context){
//        System.out.println("context = " + context.getAuthentication().getName());
//        return "index";
//    }

    @GetMapping("/")
    public String index(){
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        Authentication authentication = context.getAuthentication();
        System.out.println("authentication = " + authentication);

        //securityContextService.getContext();

        return "index";
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
}
