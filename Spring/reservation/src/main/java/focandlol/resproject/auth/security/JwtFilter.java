package focandlol.resproject.auth.security;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * JWT 토큰을 검증하여 사용자 인증 정보를 설정하는 필터
 * - 요청 헤더에서 JWT 토큰을 추출하고 유효성을 확인
 * - 유효한 토큰이면 SecurityContext에 인증 정보를 설정
 * - 유효하지 않은 토큰이거나 예외가 발생하면 CustomAuthenticationEntryPoint에서 처리
 */
@RequiredArgsConstructor
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    /**
     * JWT 토큰을 검증하여 사용자 인증 정보를 설정하는 메서드
     * - Authorization 헤더에서 JWT 토큰을 추출하고 유효성을 확인
     * - 유효한 토큰이면 SecurityContext에 인증 정보를 저장
     * - 예외가 발생하면 요청 속성에 예외를 저장하고 이후 필터로 제어를 넘김
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            /**
             * 요청 헤더에서 Authorization 조회
             * 클라이언트가 HTTP 요청 헤더에 Authorization 값을 포함하지 않았거나,
             * Authorization 값이 "Bearer "로 시작하지 않으면 필터를 건너뜀.
             * 이 경우 다음 필터로 제어를 넘기고 메서드를 종료
             */
            String authorization = request.getHeader("Authorization");
            if (authorization == null || !authorization.startsWith("Bearer ")) {
                filterChain.doFilter(request, response);
                return;
            }

            /**
             * Authorization 헤더의 값에서 "Bearer " 부분을 제거하고 실제 토큰 값만 추출
             * 헤더 값 예시: "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9..."
             * `split(" ")[1]`을 사용하여 "Bearer " 이후의 부분을 가져옴.
             */
            String token = authorization.split(" ")[1];

            /**
             * jwt 토큰 유효성 확인 및 인증 정보 생성
             */
            Authentication authentication = jwtUtil.authentication(token);

            /**
             * 인증이 성공하면 SecurityContextHolder를 통해 인증 정보를 저장
             * 이로 인해 해당 요청에서 인증된 사용자 정보를 참조 가능
             */
            SecurityContextHolder.getContext().setAuthentication(authentication);

        } catch (Exception e) {
            /**
             * 예외가 발생하면 요청 속성에 예외 객체를 저장
             * "exception"이라는 이름으로 예외를 저장하여 이후에 접근
             * 이후 CustomAuthenticationEntryPoint에서 예외 처리
             */
            request.setAttribute("exception", e);
        }
        filterChain.doFilter(request, response);
    }
}