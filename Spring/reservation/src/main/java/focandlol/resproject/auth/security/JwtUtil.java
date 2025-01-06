package focandlol.resproject.auth.security;

import focandlol.resproject.auth.dto.CustomUserDetails;
import focandlol.resproject.global.exception.CustomException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static focandlol.resproject.global.exception.ErrorCode.WRONG_TOKEN;

/**
 * jwt 유틸리티 클래스
 * - jwt 토큰의 생성, 검증, 클레임 추출 등의 기능을 제공
 * - 사용자 인증 정보를 생성하고 토큰의 만료 여부를 확인
 */
@Component
@Slf4j
public class JwtUtil {

    private final SecretKey secretKey;
    private final Long tokenValidTime;
    private final UserDetailsService customerUserDetailsService;
    private final UserDetailsService managerUserDetailsService;

    /**
     * JwtUtil 생성자
     *
     * @param secret                     jwt 토큰 서명에 사용할 비밀키
     * @param tokenTime                  jwt 토큰 유효 기간 (밀리초)
     * @param customerUserDetailsService CustomerUserDetailsService 구현체
     * @param managerUserDetailsService  ManagerUserDetailsService 구현체
     */
    private JwtUtil(@Value("${spring.jwt.secret}") String secret,
                    @Value("${spring.jwt.time}") Long tokenTime,
                    UserDetailsService customerUserDetailsService, UserDetailsService managerUserDetailsService) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        tokenValidTime = tokenTime;
        this.customerUserDetailsService = customerUserDetailsService;
        this.managerUserDetailsService = managerUserDetailsService;
    }

    /**
     * jwt Payload에서 "id" 클레임 값을 Long 타입으로 반환
     */
    public Long getId(Jws<Claims> jws) {
        return jws.getPayload().get("id", Long.class);
    }

    /**
     * jwt Payload에서 "username" 클레임 값을 String 타입으로 반환
     */
    public String getUsername(Jws<Claims> jws) {
        return jws.getPayload().get("username", String.class);
    }

    /**
     * jwt Payload에서 "roles" 클레임 값을 List<String> 타입으로 반환
     */
    public List<String> getRoles(Jws<Claims> jws) {
        return jws.getPayload().get("roles", List.class);
    }

    /**
     * 주어진 jwt 토큰을 검증, 서명 및 클레임 정보 확인
     * 여러 jwt 관련 예외 발생 가능 -> jwtFilter에서 catch -> CustomAuthenticationEntryPoint -> ExceptionHandler 에서 처리
     */
    public Jws<Claims> verifyToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    /**
     * jwt 토큰을 검증하고 사용자 인증 정보를 생성
     * - 토큰의 유효성을 검증하고 사용자 정보를 로드
     * - 토큰의 클레임 정보와 사용자 계정 정보를 비교하여 일치하지 않으면 예외
     *
     * @param token 검증할 jwt 토큰
     * @return 사용자 인증 정보 (UsernamePasswordAuthenticationToken)
     * @throws CustomException jwt 토큰이 유효하지 않거나 사용자 정보가 일치하지 않을 경우
     */
    public Authentication authentication(String token) {
        Jws<Claims> jws = verifyToken(token);

        String username = getUsername(jws);
        List<String> roles = getRoles(jws);
        Long id = getId(jws);

        CustomUserDetails userDetails;

        /**
         * role 에 따라 사용자 정보 로드
         * 해당 jwt token에 맞는 계정이 없으면 사용하지 못하도록 조치
         * service단 에서 현재 로그인한 사용자 계정의 존재 유무를 파악 (ex) findById(id))하는 대신
         * 이곳에서 jwt token에 대한 사용자 계정 존재 유무 확인
         * 따라서 이곳과 jwtFilter에서 예외가 발생하지 않는다면
         * @AuthenticationPrincipal 이것으로 가져오는 사용자 정보는
         * jwt token 인증 성공 및 계정 존재 유무 확인 완료된 정보
         */
        if (roles.contains("ROLE_MANAGER")) {
            userDetails = (CustomUserDetails) managerUserDetailsService.loadUserByUsername(username);
        } else if (roles.contains("ROLE_CUSTOMER")) {
            userDetails = (CustomUserDetails) customerUserDetailsService.loadUserByUsername(username);
        } else {
            throw new CustomException(WRONG_TOKEN);
        }

        if (!id.equals(userDetails.getId())) {
            throw new CustomException(WRONG_TOKEN);
        }
        if (!username.equals(userDetails.getUsername())) {
            throw new CustomException(WRONG_TOKEN);
        }

        /**
         * 토큰 생성 후 리턴
         */
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * jwt 생성 메서드
     *
     * @param id       사용자 ID
     * @param username 사용자 이메일
     * @param roles    사용자 권한 리스트
     */
    public String createJwt(Long id, String username, List<String> roles) {

        return Jwts.builder()
                .claim("id", id)
                .claim("username", username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(secretKey)
                .compact();
    }
}
