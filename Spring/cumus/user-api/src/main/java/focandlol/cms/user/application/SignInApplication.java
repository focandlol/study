package focandlol.cms.user.application;

import focandlol.cms.user.domain.SignInForm;
import focandlol.cms.user.domain.model.Customer;
import focandlol.cms.user.exception.CustomException;
import focandlol.cms.user.exception.ErrorCode;
import focandlol.cms.user.service.CustomerService;
import focandlol.config.JwtAuthenticationProvider;
import focandlol.domain.common.UserType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static focandlol.cms.user.exception.ErrorCode.*;

@Service
@RequiredArgsConstructor
public class SignInApplication {

    private final CustomerService customerService;
    private final JwtAuthenticationProvider provider;

    public String customerLogin(SignInForm form){
        //1. 로그인 가능 여부
        Customer c = customerService.findValidCustomer(form.getEmail(), form.getPassword())
                .orElseThrow(() -> new CustomException(LOGIN_CHECK_FAIL));

        //토큰 발행

        //토큰 response
        return provider.createToken(c.getEmail(),c.getId(), UserType.CUSTOMER);

    }
}
