package focandlol.reservation.service;

import focandlol.reservation.dto.CustomUserDetails;
import focandlol.reservation.dto.UserDetailsDto;
import focandlol.reservation.entity.auth.CustomerEntity;
import focandlol.reservation.entity.auth.ManagerEntity;
import focandlol.reservation.repository.CustomerRepository;
import focandlol.reservation.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final CustomerRepository customerRepository;
    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<ManagerEntity> findManager = managerRepository.findByUsername(username);

        if(findManager.isPresent()) {
            ManagerEntity manager = findManager.get();
            return new CustomUserDetails(UserDetailsDto.from(manager));
        }

        Optional<CustomerEntity> findCustomer = customerRepository.findByUsername(username);

        if(findCustomer.isPresent()) {
            CustomerEntity customer = findCustomer.get();
            return new CustomUserDetails(UserDetailsDto.from(customer));
        }

        throw new UsernameNotFoundException("User not found");
    }

}
