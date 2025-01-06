package focandlol.resproject.auth.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.global.exception.CustomException;
import focandlol.resproject.global.exception.ErrorCode;
import focandlol.resproject.global.exception.ErrorResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
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

import static focandlol.resproject.global.exception.ErrorCode.FAILED_LOGIN;
import static focandlol.resproject.global.exception.ErrorCode.FAILED_TO_PARSE_REQUEST_BODY;

/**
 * /login/customer 경로로 들어오는 요청을 처리하는 로그인 필터입니다.
 * Customer와 Manager의 로그인 로직이 달라질 가능성이 있어 별도로 분리.
 * - 처음 로그인 시 인증을 시도
 * - 인증 성공 시 JWT 토큰을 발급하여 응답 헤더에 포함.
 */
public class CustomerLoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public CustomerLoginFilter(String filterProcessUrl, ObjectMapper objectMapper,
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
         * 로그인 요청 시 인증을 시도
         * 요청 본문(body)에서 username(이메일)과 password(패스워드)를 추출한 후,
         * UsernamePasswordAuthenticationToken을 생성하여 인증을 위임
         */
        try {
            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            if (requestBody.isEmpty()) {
                throw new CustomException(ErrorCode.FAILED_TO_PARSE_REQUEST_BODY);
            }

            Map<String, String> jsonMap = objectMapper.readValue(requestBody, Map.class);

            username = jsonMap.get("username");
            password = jsonMap.get("password");
        } catch (IOException e) {
            throw new CustomException(FAILED_TO_PARSE_REQUEST_BODY);
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

        String token = jwtUtil.createJwt(userDetails.getUserDetailsDto().getId(), username, roles);

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

