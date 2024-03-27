package kkm.rest.restservice.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

//@Component
//public class AcceptHeaderResolver extends AcceptHeaderLocaleResolver {
//    List<Locale> LOCALES = Arrays.asList(new Locale("en"),new Locale("ko"),new Locale("fr"));
//
//    @Override
//    public Locale resolveLocale(HttpServletRequest request) {
//        System.out.println("resolovelocale");
//        String headerLang = request.getHeader("Accept-Language");
//        return headerLang == null || headerLang.isEmpty()
//                ? Locale.getDefault()
//                : Locale.lookup(Locale.LanguageRange.parse(headerLang), LOCALES);
//    }
//}
