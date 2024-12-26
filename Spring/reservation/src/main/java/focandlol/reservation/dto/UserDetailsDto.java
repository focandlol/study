package focandlol.reservation.dto;

import focandlol.reservation.entity.auth.BasicContent;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDetailsDto {

    private String username;
    private String password;
    private List<String> roles;

    public static UserDetailsDto from(BasicContent basicContent) {
        return UserDetailsDto.builder()
                .username(basicContent.getUsername())
                .password(basicContent.getPassword())
                .roles(Collections.singletonList(basicContent.getUserType().getRole()))
                .build();
    }
}
