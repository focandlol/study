package focandlol.reservation.auth.dto;

import focandlol.reservation.global.entity.BasicContent;
import lombok.*;

import java.util.Collections;
import java.util.List;

/**
 * customUserDetails 에 들어갈 정보
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDto {
    private Long id;
    private String username;
    private String password;
    private List<String> roles;

    public static UserDetailsDto from(BasicContent basicContent) {
        return UserDetailsDto.builder()
                .id(basicContent.getId())
                .username(basicContent.getUsername())
                .password(basicContent.getPassword())
                .roles(Collections.singletonList(basicContent.getUserType().getRole()))
                .build();
    }
}
