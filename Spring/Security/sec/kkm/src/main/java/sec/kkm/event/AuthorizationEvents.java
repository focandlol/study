package sec.kkm.event;

import org.springframework.context.event.EventListener;
import org.springframework.security.authorization.event.AuthorizationDeniedEvent;
import org.springframework.security.authorization.event.AuthorizationEvent;
import org.springframework.security.authorization.event.AuthorizationGrantedEvent;
import org.springframework.stereotype.Component;

@Component
public class AuthorizationEvents {

    @EventListener
    public void onAuthorization(AuthorizationEvent event){
        System.out.println("event0 = " + event.getAuthentication().get().getAuthorities());
    }
    @EventListener
    public void onAuthorization(AuthorizationDeniedEvent failure){
        System.out.println("event1 = " + failure.getAuthentication().get().getAuthorities());
    }

    @EventListener
    public void onAuthorization(AuthorizationGrantedEvent success){
        System.out.println("event2 = " + success.getAuthentication().get().getAuthorities());
    }
}
