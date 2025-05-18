package focandlol.elastic;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

  private final UserDocumentRepository userDocumentRepository;

  @PostMapping
  public UserDocument createUser(@RequestBody UserCreateRequestDto request){
    return userDocumentRepository.save(request.to());
  }

  @GetMapping
  public Page<UserDocument> findUsers(){
    return userDocumentRepository.findAll(PageRequest.of(0,10));
  }

  @GetMapping("/{id}")
  public UserDocument findByUserId(@PathVariable String id){
    return userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문서"));
  }

  @PutMapping("/{id}")
  public UserDocument updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto request){
    UserDocument exist = userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문서"));

    exist.setAge(request.getAge());
    exist.setName(request.getName());
    exist.setIsActive(request.getIsActive());

    return userDocumentRepository.save(exist);
  }

  @DeleteMapping("/{id}")
  public void deleteUser(@PathVariable String id){
    UserDocument exist = userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("존재하지 않는 문서"));

    userDocumentRepository.delete(exist);
  }

}
