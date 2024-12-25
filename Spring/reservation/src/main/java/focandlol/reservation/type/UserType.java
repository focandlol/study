package focandlol.reservation.type;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    CUSTOMER("ROLE_CUSTOMER"),
    MANAGER("ROLE_MANAGER");

    private final String role;
}
