package focandlol.reservation.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static focandlol.reservation.exception.ErrorCode.FAILED_LOGIN;

/**
 * 추후 customer, manager 로그인 시 로직이 달라질 확률이 높으므로 Loginfilter 분리
 * /login/manager 경로로 들어오는 요청 처리하는 필터
 * 처음 로그인 할 때 인증 시도, 인증 성공 시 jwt token 발급
 */
public class ManagerLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public ManagerLoginFilter(String filterProcessUrl, ObjectMapper objectMapper,
                               AuthenticationManager authenticationManager, JwtUtil jwtUtil) {
        this.setFilterProcessesUrl(filterProcessUrl);
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    /**
     * 처음 로그인 할 때 인증 시도
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username;
        String password;

        /**
         * /login/manager 경로로  요청 시
         * body에 로그인 정보 넣어서 요청
         * username : 사용자 이메일
         * password : 사용자 패스워드
         * 해당 body에 있는 데이터 추출
         */
        try {
            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            Map<String, String> jsonMap = objectMapper.readValue(requestBody, Map.class);

            username = jsonMap.get("username");
            password = jsonMap.get("password");

        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to parse request body", e);
        }

        /**
         * 토큰 만들고 인증 시도
         */
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    /**
     * 로그인 인증 성공 시 호출
     * 인증된 customUserDetails를 토대로 jwt token 생성 후 header에 넣어서 리턴
     */
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();

        String username = userDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        List<String> roles = authorities.stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .collect(Collectors.toList());

        String token = jwtUtil.createJwt(userDetails.getUserDetailsDto().getId(),username, roles);

        response.addHeader("Authorization", "Bearer " + token);
    }

    /**
     * 로그인 인증 실패 시 호출
     */
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        ErrorResponse error = new ErrorResponse(FAILED_LOGIN);
        response.setStatus(FAILED_LOGIN.getStatus().value());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.getWriter().write(objectMapper.writeValueAsString(error));
    }
}

