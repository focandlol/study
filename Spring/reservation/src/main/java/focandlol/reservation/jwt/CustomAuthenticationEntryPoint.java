package focandlol.reservation.jwt;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/**
 * spring security 인증 실패 처리 시 호출
 */
@Component
public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {

    /**
     * Spring의 HandlerExceptionResolver를 사용해 예외를 처리
     */
    public final HandlerExceptionResolver handlerExceptionResolver;

    public CustomAuthenticationEntryPoint(@Qualifier("handlerExceptionResolver") HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    /**
     * 인증 실패 시 호출되는 메서드입니다.
     * @param request jwtFilter에서 발생한 exception 객체를 가지고 있는 요청 객체
     * @param response 현재 응답 객체
     * @param authException AuthenticationException 예외 객체로, 인증 실패에 대한 정보를 포함.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        /**
         * 요청 속성에 저장된 "exception" 객체 조회
         * HandlerExceptionResolver를 사용하여 예외 처리 -> 구현한 GlobalExceptionHandler에서 security filter 에서 발생한 예외 처리
         */
        handlerExceptionResolver.resolveException(request, response, null, (Exception) request.getAttribute("exception"));
    }
}
