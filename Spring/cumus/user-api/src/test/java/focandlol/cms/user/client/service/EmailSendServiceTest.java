package focandlol.cms.user.client.service;

import feign.Response;
import focandlol.cms.user.client.config.FeignConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cache.annotation.EnableCaching;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmailSendServiceTest {

    @Autowired
    private EmailSendService emailSendService;

    @Test
    public void EmailTest(){
        String response = emailSendService.sendEmail();
        System.out.println(response);

    }

}