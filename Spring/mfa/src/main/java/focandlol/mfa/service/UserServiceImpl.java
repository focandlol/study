package focandlol.mfa.service;

import focandlol.mfa.datas.entity.UserEntity;
import focandlol.mfa.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public Optional<UserEntity> getUser(String username){
        return getUser(UserEntity.builder().username(username).build());
    }

    @Override
    public Optional<UserEntity> getUser(UserEntity userEntity){
        return userRepository.findByUsername(userEntity.getUsername());
    }
}
