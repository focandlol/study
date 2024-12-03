package focandlol.cms.user.service;

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
                .from("admin@sandboxf2c6ed790d15420fb8d17cee47bdb7dc.mailgun.org")
                .to("arther200017@gmail.com")
                .subject("Foransddlol")
                .text("my text")
                .build();

        return mailgunClient.sendEmail(form).getBody();
    }
}
