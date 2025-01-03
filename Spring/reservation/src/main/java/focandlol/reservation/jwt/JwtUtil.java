package focandlol.reservation.jwt;

import focandlol.reservation.exception.CustomException;
import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.service.CustomUserDetailsService;
import focandlol.reservation.service.ManagerUserDetailsService;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static focandlol.reservation.exception.ErrorCode.*;

@Component
@Slf4j
public class JwtUtil {

    private final SecretKey secretKey;
    private final Long tokenValidTime;
    private final CustomUserDetailsService customerUserDetailsService;
    private final ManagerUserDetailsService managerUserDetailsService;

    private JwtUtil(@Value("${spring.jwt.secret}") String secret,
                    @Value("${spring.jwt.time}") Long tokenTime,
                    CustomUserDetailsService userDetailsService, ManagerUserDetailsService managerUserDetailsService) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        tokenValidTime = tokenTime;
        this.customerUserDetailsService = userDetailsService;
        this.managerUserDetailsService = managerUserDetailsService;
    }

    /**
     * JWT의 Payload에서 "id" 클레임 값을 Long 타입으로 반환
     */
    public Long getId(Jws<Claims> jws) {
        return jws.getPayload().get("id", Long.class);
    }

    /**
     * JWT의 Payload에서 "username" 클레임 값을 String 타입으로 반환
     */
    public String getUsername(Jws<Claims> jws) {
        return jws.getPayload().get("username", String.class);
    }

    /**
     * JWT의 Payload에서 "roles" 클레임 값을 List<String> 타입으로 반환
     */
    public List<String> getRoles(Jws<Claims> jws) {
        return jws.getPayload().get("roles", List.class);
    }

    /**
     * JWT의 만료(expiration) 시간을 확인하여 만료 여부를 Boolean 값으로 반환
     */
    public Boolean isExpired(Jws<Claims> jws) {
        return jws.getPayload().getExpiration().before(new Date());
    }

    /**
     * 주어진 JWT 토큰을 검증, 서명 및 클레임 정보 확인
     * 여러 jwt 관련 예외 발생 가능 -> jwtFilter에서 catch -> CustomAuthenticationEntryPoint -> GlobalExceptionHandler 에서 처리
     */
    public Jws<Claims> verifyToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    /**
     * jwt 검증 및 사용자 인증 정보 생성
     */
    public Authentication authentication(String token) {
        Jws<Claims> jws = verifyToken(token);

        String username = getUsername(jws);
        List<String> roles = getRoles(jws);
        Long id = getId(jws);

        CustomUserDetails userDetails;

        /**
         * role 에 따라 사용자 정보 로드
         * 만약 jwt token 을 통째로 잃어버렸을 때를 대비
         * 계정을 삭제하면 해당 jwt token도 사용하지 못하도록 조치
         * service단 에서 현재 로그인한 사용자 계정의 존재 유무를 파악 (ex) findById(id))하는 대신
         * 이곳에서 jwt token에 대한 사용자 계정 존재 유무 확인
         * 따라서 이곳과 jwtFilter에서 예외가 발생하지 않는다면
         * @AuthenticationPrincipal 이것으로 가져오는 사용자 정보는
         * jwt token 인증 성공 및 계정 존재 유무 확인 완료된 정보
         */
        if(roles.contains("ROLE_MANAGER")){
            userDetails = (CustomUserDetails) managerUserDetailsService.loadUserByUsername(username);
        }else if(roles.contains("ROLE_CUSTOMER")){
            userDetails = (CustomUserDetails) customerUserDetailsService.loadUserByUsername(username);
        }else{
            throw new CustomException(WRONG_TOKEN);
        }

        if(!id.equals(userDetails.getId())) {
            throw new CustomException(WRONG_TOKEN);
        }
        if(!username.equals(userDetails.getUsername())) {
            throw new CustomException(WRONG_TOKEN);
        }

        /**
         * 토큰 생성 후 리턴
         */
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * jwt 생성 메서드
     * @param id 사용자 ID
     * @param username 사용자 이메일
     * @param roles 사용자 권한 리스트
     */
    public String createJwt(Long id, String username, List<String> roles) {

        return Jwts.builder()
                .claim("id",id)
                .claim("username", username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + tokenValidTime))
                .signWith(secretKey)
                .compact();
    }
}
