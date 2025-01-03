package focandlol.reservation.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.reservation.exception.CustomException;
import focandlol.reservation.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final ObjectMapper mapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            String authorization = request.getHeader("Authorization");

            if (authorization == null || !authorization.startsWith("Bearer ")) {

                System.out.println("token null");
                filterChain.doFilter(request, response);

                return;
            }

            System.out.println("authorization now");
            //Bearer 부분 제거 후 순수 토큰만 획득
            String token = authorization.split(" ")[1];

            Authentication authentication = jwtUtil.authentication(token);

            //세션에 사용자 등록
            SecurityContextHolder.getContext().setAuthentication(authentication);


        }catch (Exception e) {
//            ErrorResponse error = new ErrorResponse(e.getErrorCode());
//
//            response.setStatus(e.getStatus().value());
//            response.setCharacterEncoding("UTF-8");
//            response.setContentType("application/json");
//            response.getWriter().write(mapper.writeValueAsString(error));
            request.setAttribute("exception", e);
        }
        filterChain.doFilter(request, response);
    }
}