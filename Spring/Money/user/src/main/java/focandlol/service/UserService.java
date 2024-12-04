package focandlol.service;

import focandlol.domain.UserInfo;
import focandlol.repository.UserInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserInfoRepository userInfoRepository;

    public String save(UserInfo user){
        userInfoRepository.save(user);
        return "save complete";
    }
}
