package org.example.websample.config;

import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;


@Slf4j
public class LogFilter implements Filter {


    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response, FilterChain chain) throws IOException, ServletException {

        log.info("Hello filter : " + Thread.currentThread());
        chain.doFilter(request, response);
        log.info("Bye filter : " + Thread.currentThread());
    }
}
