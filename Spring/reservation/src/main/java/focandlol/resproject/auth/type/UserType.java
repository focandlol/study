package focandlol.resproject.auth.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 사용자 구분
 */
@Getter
@AllArgsConstructor
public enum UserType {
    /**
     * 일반 고객
     */
    CUSTOMER("ROLE_CUSTOMER"),

    /**
     * 매니저
     */
    MANAGER("ROLE_MANAGER");

    private final String role;
}
