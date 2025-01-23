package focandlol.doc.service;

import focandlol.doc.model.User;
import focandlol.doc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

  private final UserRepository userRepository;

  public User create(User user){
    return userRepository.save(user);
  }

  public User read(Long id){
    return userRepository.findById(id).orElse(null);
  }

  public User update(User user){
    User findUser = userRepository.findById(user.getId()).orElse(null);

    findUser.setAccount(user.getAccount());
    findUser.setEmail(user.getEmail());
    findUser.setPhoneNumber(user.getPhoneNumber());

    return findUser;
  }

  public boolean delete(Long id){
    return userRepository.findById(id)
        .map(user -> {
          userRepository.deleteById(id);
          return true;
        }).orElse(false);
  }

}
