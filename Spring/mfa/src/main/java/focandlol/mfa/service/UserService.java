package focandlol.mfa.service;

import focandlol.mfa.datas.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    Optional<UserEntity> getUser(String username);

    Optional<UserEntity> getUser(UserEntity userEntity);
}
