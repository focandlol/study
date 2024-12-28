package focandlol.reservation.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.reservation.dto.CustomUserDetails;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final ObjectMapper objectMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public LoginFilter(ObjectMapper objectMapper, AuthenticationManager authenticationManager,JwtUtil jwtUtil) {
        this.objectMapper = objectMapper;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        String username;
        String password;
        try {
            // Request body를 읽기 위해 InputStream 사용
            String requestBody = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));

            // JSON 파싱 (Jackson 사용)
            Map<String, String> jsonMap = objectMapper.readValue(requestBody, Map.class);

            // JSON에서 username과 password 추출
            username = jsonMap.get("username");
            password = jsonMap.get("password");

            System.out.println("username: " + username + " password: " + password);
        } catch (IOException e) {
            throw new AuthenticationServiceException("Failed to parse request body", e);
        }

        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password, null);

        return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {

        CustomUserDetails userDetails = (CustomUserDetails) authResult.getPrincipal();

        String username = userDetails.getUsername();


        Collection<? extends GrantedAuthority> authorities = authResult.getAuthorities();
        List<String> roles = authorities.stream()
                .map(grantedAuthority -> grantedAuthority.getAuthority()) // 권한 이름만 추출
                .collect(Collectors.toList());


        String token = jwtUtil.createJwt(userDetails.getUserDetailsDto().getId(),username, roles, 60*60*1000L);

        response.addHeader("Authorization", "Bearer " + token);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(401);
    }
}

