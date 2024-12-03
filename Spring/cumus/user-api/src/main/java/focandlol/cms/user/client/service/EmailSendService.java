package focandlol.cms.user.client.service;

import feign.Response;
import focandlol.cms.user.client.MailgunClient;
import focandlol.cms.user.client.mailgun.SendMailForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailSendService {

    private final MailgunClient mailgunClient;

    public String sendEmail(){
        SendMailForm form = SendMailForm.builder()
                .from("admin@sandbox987992d760f0444caa56b3ab7ed721f8.mailgun.org")
                .to("focandlol@naver.com")
                .subject("Forandlol")
                .text("my text")
                .build();

        return mailgunClient.sendEmail(form).getBody();
    }
}
