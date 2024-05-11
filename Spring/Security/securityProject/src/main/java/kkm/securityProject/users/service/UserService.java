package kkm.securityProject.users.service;

import jakarta.transaction.Transactional;
import kkm.securityProject.domain.entity.Account;
import kkm.securityProject.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public void createUser(Account account) {
        userRepository.save(account);
    }

}
