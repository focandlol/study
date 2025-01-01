package focandlol.reservation.jwt;


import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.exception.CustomException;
import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.dto.UserDetailsDto;
import focandlol.reservation.entity.BaseEntity;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

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

            filterChain.doFilter(request, response);
        }catch (CustomException e) {
            ErrorResponse error = new ErrorResponse(e.getErrorCode());

            response.setStatus(e.getStatus().value());
            response.setCharacterEncoding("UTF-8");
            response.setContentType("application/json");
            response.getWriter().write(mapper.writeValueAsString(error));
        }
    }
}
