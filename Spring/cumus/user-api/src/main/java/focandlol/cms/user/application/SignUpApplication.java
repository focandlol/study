package focandlol.cms.user.application;

import focandlol.cms.user.client.MailgunClient;
import focandlol.cms.user.client.mailgun.SendMailForm;
import focandlol.cms.user.domain.SignUpForm;
import focandlol.cms.user.domain.model.Customer;
import focandlol.cms.user.exception.CustomException;
import focandlol.cms.user.exception.ErrorCode;
import focandlol.cms.user.service.SignUpCustomerService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SignUpApplication {

    private final MailgunClient mailgunClient;
    private final SignUpCustomerService signUpCustomerService;

    public void customerVerify(String email, String code){
        signUpCustomerService.verifyEmail(email,code);
    }

    public String customerSignUp(SignUpForm form){
        if(signUpCustomerService.isEmailExist(form.getEmail())){
            throw new CustomException(ErrorCode.ALREADY_REGISTER_USER);
        }else{
            Customer c = signUpCustomerService.signUp(form);
            LocalDateTime now = LocalDateTime.now();

            String code = getRandomCode();
            SendMailForm sendMailForm = SendMailForm.builder()
                    .from("admin@sandboxf2c6ed790d15420fb8d17cee47bdb7dc.mailgun.org")
                    .to(form.getEmail())
                    .subject("verification email")
                    .text(getVerificationEmailBody(form.getEmail(), form.getName(), code))
                    .build();

            mailgunClient.sendEmail(sendMailForm);
            signUpCustomerService.changeCustomerValidateEmail(c.getId(),code);
            return "회원 가입에 성공하였습니다.";
        }

    }

    private String getRandomCode(){
        return RandomStringUtils.random(10,true,true);
    }

    private String getVerificationEmailBody(String email, String name, String code){
        StringBuilder sb = new StringBuilder();
        return sb.append("hello ").append(name).append("! please click link for verificatin\n\n")
                .append("http://localhost:8080/signup/verify/customer?email=")
                .append(email)
                .append("&code=")
                .append(code).toString();
    }


}
