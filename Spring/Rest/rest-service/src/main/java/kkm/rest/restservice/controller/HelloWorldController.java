package kkm.rest.restservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import kkm.rest.restservice.bean.HelloWorldBean;
import org.springframework.cglib.core.Local;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@RestController
public class HelloWorldController {

 private MessageSource messageSource;

    public HelloWorldController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @GetMapping("/hello-world")
    public String helloWorld(){
        return "hello world";
    }

    @GetMapping("/hello-world-bean")
    public HelloWorldBean helloWorldBean(){
        return new HelloWorldBean("hello world");
    }

    @GetMapping("hello-world-bean/path-variable/{name}")
    public HelloWorldBean helloWorldBean(@PathVariable String name){
        return new HelloWorldBean("hello world " + name);
    }

    @GetMapping("/hello-world-internationalized")
    public String helloWorldInternalized(@RequestHeader(name="Accept-language",required = false) Locale locale){
        System.out.println("locale = " + locale);
        return messageSource.getMessage("greeting.message",null,locale);
    }

    @GetMapping(path = "/test-world-internationalized")
    public String testWorldInternationalizedWithoutHeader(HttpServletRequest request) {
        String acceptLanguage = request.getHeader("Accept-Language");
        System.out.println(acceptLanguage);
        //Locale locale = resolver.resolveLocale(request);
        //return messageSource.getMessage("greeting.message", null, locale);
        return null;
    }
}
