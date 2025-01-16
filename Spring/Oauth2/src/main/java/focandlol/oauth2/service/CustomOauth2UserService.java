package focandlol.oauth2.service;

import focandlol.oauth2.dto.*;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        OAuth2User oAuth2User = super.loadUser(userRequest);
        System.out.println(oAuth2User);

        //naver에서 온건지 google에서 온건지
        String registrationId = userRequest.getClientRegistration().getRegistrationId();
        Oauth2Response oauth2Response;

        if(registrationId.equals("naver")) {
            oauth2Response = new NaverReponse(oAuth2User.getAttributes());
        }else if(registrationId.equals("google")) {
            oauth2Response = new GoogleResponse(oAuth2User.getAttributes());
        }else{
            return null;
        }

        String username = oauth2Response.getProvider()+ " " + oauth2Response.getProviderId();

        return new CustomOauth2User(UserDto.builder()
                .name(oauth2Response.getName())
                .username(username)
                .role("ROLE_USER").build());
    }
}
