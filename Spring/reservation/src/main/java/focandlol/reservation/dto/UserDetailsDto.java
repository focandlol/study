package focandlol.reservation.dto;

import focandlol.reservation.entity.BaseEntity;
import focandlol.reservation.type.UserType;
import jakarta.validation.constraints.NotBlank;
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

    public static UserDetailsDto from(BaseEntity baseEntity) {
        return UserDetailsDto.builder()
                .username(baseEntity.getUsername())
                .password(baseEntity.getPassword())
                .roles(Collections.singletonList(baseEntity.getUserType().getRole()))
                .build();
    }
}
