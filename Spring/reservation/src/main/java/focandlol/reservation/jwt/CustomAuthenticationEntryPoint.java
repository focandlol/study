package focandlol.reservation.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.exception.CustomException;
import focandlol.exception.ErrorCode;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;


import java.io.IOException;

import static focandlol.exception.ErrorCode.*;

//@RequiredArgsConstructor
//public class CustomAuthenticationEntryPoint implements AuthenticationEntryPoint {
//    @Override
//    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
//        Throwable cause = authException.getCause();
//        System.out.println("Cause: " + (cause != null ? cause.getClass().getName() : "null"));
//        ErrorResponse errorResponse;
//
//        if (cause instanceof CustomException) {
//            CustomException customException = (CustomException) cause;
//            errorResponse = new ErrorResponse(customException.getErrorCode());
//            response.setStatus(customException.getStatus());
//        } else {
//            System.out.println("else");
//            errorResponse = new ErrorResponse(
//                    INTERNAL_SERVER_ERROR
//            );
//            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
//        }
//
//        response.setContentType("application/json");
//        response.getWriter().write(mapper.writeValueAsString(errorResponse));
//    }
//}
