package focandlol.reservation.customer.service;

import focandlol.reservation.customer.dto.CustomerSignUpDto;
import focandlol.reservation.manager.dto.ManagerSignUpDto;

public interface CustomerService {
    CustomerSignUpDto.Response customerSignUp(CustomerSignUpDto.Request request);

}
