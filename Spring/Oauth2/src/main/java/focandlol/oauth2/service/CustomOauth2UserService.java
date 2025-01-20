package focandlol.oauth2.service;

import focandlol.oauth2.dto.*;
import focandlol.oauth2.entity.UserEntity;
import focandlol.oauth2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomOauth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

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


        Optional<UserEntity> user = userRepository.findByUsername(username);
        System.out.println(oauth2Response.getEmail());

        if(user.isPresent()) {
            UserEntity userEntity = user.get();
            userEntity.setName(oauth2Response.getName());
            userEntity.setEmail(oauth2Response.getEmail());

            userRepository.save(userEntity);

            return new CustomOauth2User(UserDto.builder()
                    .name(oauth2Response.getName())
                    .username(userEntity.getUsername())
                    .email(oauth2Response.getEmail())
                    .role(userEntity.getRole()).build());
        }else{
            userRepository.save(UserEntity.builder()
                    .username(username)
                    .email(oauth2Response.getEmail())
                    .name(oauth2Response.getName())
                    .role("ROLE_USER").build());

            return new CustomOauth2User(UserDto.builder()
                    .name(oauth2Response.getName())
                    .email(oauth2Response.getEmail())
                    .username(username)
                    .role("ROLE_USER").build());
        }


    }
}
