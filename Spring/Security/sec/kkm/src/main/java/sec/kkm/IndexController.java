package sec.kkm;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.CurrentSecurityContext;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;

@RestController
@RequiredArgsConstructor
public class IndexController {

    /**
     * 익명 사용자인지 아닌지 구분
     */
    AuthenticationTrustResolverImpl trustResolver = new AuthenticationTrustResolverImpl();

    private final DateService dataService;

    @GetMapping("/trust")
    public String trust(){
        Authentication authentication = SecurityContextHolder.getContextHolderStrategy().getContext().getAuthentication();
        return trustResolver.isAnonymous(authentication) ? "anonymous" : "authenticated";
    }

    @GetMapping("/springUser")
    public User springUser(@AuthenticationPrincipal User user){
        return user;
    }

    @GetMapping("/springUser2")
    public String springUser2(@AuthenticationPrincipal(expression = "username") String user){
        return user;
    }

    @GetMapping("/callable")
    public Callable<Authentication> callable(){
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        System.out.println("context = " + context);
        System.out.println("Parent thread = " + Thread.currentThread().getName());

        return () -> {
            SecurityContext context1 = SecurityContextHolder.getContextHolderStrategy().getContext();
            System.out.println("context = " + context1);
            System.out.println("child thread = " + Thread.currentThread().getName());
            return context1.getAuthentication();
        };
    }




    //    @GetMapping("/")
//    public String index(String customParam){
//        if(customParam == null){
//            return "index";
//        }else{
//            return "customPage";
//        }
//    }

    @GetMapping("/sd")
    public String ss(@AuthenticationPrincipal CustomUserDetails customUserDetails){
        SecurityContext context = SecurityContextHolder.getContextHolderStrategy().getContext();
        Authentication authentication = context.getAuthentication();
        CustomUserDetails principal = (CustomUserDetails) authentication.getPrincipal();
        principal.getAccountDto();
        System.out.println("authentication = " + authentication);
        System.out.println("customUserDetails.getAccountDto() = " + customUserDetails.getAccountDto());
        //securityContextService.getContext();

        return "index";
    }

    @GetMapping("/loginPage")
    public String login(){
        return "loginPage";
    }

    @GetMapping("/anonymous")
    public String anonymous(){
        return "anonymous";
    }

    @GetMapping("/authentication")
    public String authentication(Authentication authentication){

        if (authentication instanceof AnonymousAuthenticationToken) {
            return "anonymous";
        } else {
            return "null";
        }
    }
    @GetMapping("/anonymousContext")
    public String anonymousContext(@CurrentSecurityContext SecurityContext context){
        return context.getAuthentication().getName();
    }

    @GetMapping("/logoutSuccess")
    public String logoutSuccess(@CurrentSecurityContext SecurityContext context){
        return "logoutSuccess";
    }

    @GetMapping("/invalidSessionUrl")
    public String invalidSessionUrl(){
        return "invalidSessionUrl";
    }

    @GetMapping("/expired")
    public String expired(){
        return "expired";
    }


    @GetMapping("/denied")
    public String denied(){
        return "denied";
    }

    @GetMapping("/login")
    public String customLogin(){
        return "loginPage 를 구현해야 한다";
    }

    @PostMapping("/csrf")
    public String csrf(){
        return "csrf 적용";
    }


    @PostMapping("/csrfs")
    public String csrfs(){
        return "csrfasd 적용";
    }

    @GetMapping("/csrfToken")
    public String csrfToken(HttpServletRequest request){
        CsrfToken csrfToken1 = (CsrfToken) request.getAttribute(CsrfToken.class.getName());
        CsrfToken csrfToken2 = (CsrfToken) request.getAttribute("_csrf");

        String token = csrfToken1.getToken();
        return token;
    }

    @PostMapping("/formCsrf")
    public CsrfToken formCsrf(CsrfToken csrfToken){
        return csrfToken;
    }

    @PostMapping("/cookieCsrf")
    public CsrfToken cookieCsrf(CsrfToken csrfToken){
        return csrfToken;
    }

    @GetMapping("/user")
    public String user() {
        return dataService.getUser();
    }

    @GetMapping("/db")
    public String db(){
        return "db";
    }

    @GetMapping("/admin")
    public String admin(){
        return "admin";
    }
    @GetMapping("/user/{name}")
    public String userName(@PathVariable String name){
        return name;
    }

    @GetMapping("/admin/db")
    public String adminDb(){
        return "admin";
    }

    @GetMapping("/custom")
    public String custom(){
        return "custom";
    }

    @GetMapping("/api/photos")
    public String photos(){
        return "photos";
    }

    @GetMapping("/oauth/login")
    public String oauthLogin(){
        return "oauthLogin";
    }

    @GetMapping("/ssecure")
    public String secure(){
        return "secure";
    }
    @GetMapping("/owner2")
    public Account owner2(String name){
        return dataService.getOwner(name);
    }

    @GetMapping("/login2")
    public String login2(HttpServletRequest request,MemberDto memberDto) throws ServletException {
        request.login(memberDto.getUsername(),memberDto.getPassword());
        System.out.println("login is successful");
        return "login";
    }

    @GetMapping("/users2")
    public List<MemberDto> users2(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean authenticate = request.authenticate(response);
        if(authenticate){
            return List.of(new MemberDto("user","1111"));
        }
        return Collections.emptyList();


    }




}
