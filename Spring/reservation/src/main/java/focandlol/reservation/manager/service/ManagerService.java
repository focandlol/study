package focandlol.reservation.manager.service;

import focandlol.reservation.manager.dto.ManagerSignUpDto;

public interface ManagerService {
    ManagerSignUpDto.Response managerSignUp(ManagerSignUpDto.Request request);
}
