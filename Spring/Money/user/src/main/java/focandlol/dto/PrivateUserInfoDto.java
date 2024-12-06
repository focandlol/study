package focandlol.dto;

import focandlol.domain.UserInfo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PrivateUserInfoDto {
    private String userKey;
    private String userRegistrationNumber;

    public static PrivateUserInfoDto from(UserInfo userInfo) {
        return new PrivateUserInfoDto(userInfo.getUserKey(), userInfo.getUserRegistrationNumber());
    }
}
