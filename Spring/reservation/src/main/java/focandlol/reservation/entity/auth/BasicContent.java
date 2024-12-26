package focandlol.reservation.entity.auth;

import focandlol.reservation.type.UserType;

public interface BasicContent {

    Long getId();
    String getUsername();
    String getPassword();
    UserType getUserType();
}
