package focandlol.resproject.auth.security;

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
     * 요청 속성에서 발생한 예외가 존재하면 해당 예외를, 없을 경우 기본 인증 실패 예외를 처리
     *
     * @param request       인증 중 발생한 exception 객체를 가지고 있는 요청 객체
     * @param response      현재 응답 객체
     * @param authException AuthenticationException 예외 객체로, 인증 실패에 대한 정보를 포함.
     */
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

        /**
         * 요청 속성에서 "exception" 객체를 가져옴
         */
        Exception exception = (Exception) request.getAttribute("exception");

        /**
         * 요청 속성에 저장된 "exception" 객체 조회
         * HandlerExceptionResolver를 사용하여 예외 처리 -> 구현한 ExceptionHandler에서 security filter 에서 발생한 예외 처리
         */
        if (exception != null) {
            handlerExceptionResolver.resolveException(request, response, null, exception);
        } else {
            handlerExceptionResolver.resolveException(request, response, null, authException);
        }
    }
}
