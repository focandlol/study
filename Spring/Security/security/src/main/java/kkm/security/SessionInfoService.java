package kkm.security;

import org.springframework.security.core.session.SessionInformation;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionInfoService {

    private final SessionRegistry sessionRegistry;

    public SessionInfoService(SessionRegistry sessionRegistry) {
        this.sessionRegistry = sessionRegistry;
    }

    public void sessionInfo(){
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();

        for (Object allPrincipal : allPrincipals) {
            List<SessionInformation> allSessions = sessionRegistry.getAllSessions(allPrincipal, false);
            for (SessionInformation allSession : allSessions) {
                System.out.println("사용자 = " + allPrincipal+"세션 id" + allSession.getSessionId() +
                        " 최종 요청 시간 : " + allSession.getLastRequest());
            }
        }
    }
}
