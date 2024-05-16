package kkm.securityProject.users.service;

import jakarta.transaction.Transactional;
import kkm.securityProject.admin.repository.RoleRepository;
import kkm.securityProject.domain.entity.Account;
import kkm.securityProject.domain.entity.Role;
import kkm.securityProject.users.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    public void createUser(Account account) {
        Role role = roleRepository.findByRoleName("ROLE_USER");
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        account.setUserRoles(roles);

        userRepository.save(account);
    }

}
