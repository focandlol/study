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
    private final CustomUserDetailsService customerUserDetailsService;
    private final ManagerUserDetailsService managerUserDetailsService;

    private JwtUtil(@Value("${spring.jwt.secret}") String secret, CustomUserDetailsService userDetailsService, ManagerUserDetailsService managerUserDetailsService) {
        secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), Jwts.SIG.HS256.key().build().getAlgorithm());
        this.customerUserDetailsService = userDetailsService;
        this.managerUserDetailsService = managerUserDetailsService;
    }

    public Long getId(Jws<Claims> jws) {

        return jws.getPayload().get("id", Long.class);
    }

    public String getUsername(Jws<Claims> jws) {

        return jws.getPayload().get("username", String.class);
    }

    public List<String> getRoles(Jws<Claims> jws) {

        return jws.getPayload().get("roles", List.class);
    }

    public Boolean isExpired(Jws<Claims> jws) {

        return jws.getPayload().getExpiration().before(new Date());
    }

    public Jws<Claims> verifyToken(String token) {
        return Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(token);
    }

    public Authentication authentication(String token) {
        Jws<Claims> jws;
        jws = verifyToken(token);
        /**
         * 필요한지 확인할 것
         */
//        if (isExpired(jws)) {
//            throw new CustomException(TOKEN_IS_EXPIRED);
//        }

        String username = getUsername(jws);
        List<String> roles = getRoles(jws);
        Long id = getId(jws);

        CustomUserDetails userDetails;

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

        userDetails.setPassword("safePassword");

        //스프링 시큐리티 인증 토큰 생성
        return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
    }

    /**
     * role, expiration email 고치자
     * @param username
     * @param roles
     * @param expiredMs
     * @return
     */
    public String createJwt(Long id, String username, List<String> roles, Long expiredMs) {

        return Jwts.builder()
                .claim("id",id)
                .claim("username", username)
                .claim("roles", roles)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiredMs))
                .signWith(secretKey)
                .compact();
    }
}
