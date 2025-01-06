package focandlol.reservation.global.entity;

import focandlol.reservation.auth.type.UserType;

/**
 * customer, manager 공통 메서드
 */
public interface BasicContent {

    Long getId();
    String getUsername();
    String getPassword();
    UserType getUserType();
}
