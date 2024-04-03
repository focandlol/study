package resttest.resttest;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpa")
public class JpaUserController {

    private final UserRepository userRepository;

    @PostConstruct
    public void a(){
        User user = new User();
        user.setName("a");
        userRepository.save(user);
    }

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);



        System.out.println("user = " + user.get());
         EntityModel<User> entityModel = EntityModel.of(user.get());
         entityModel.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users"));
         return ResponseEntity.ok(entityModel);



    }
}
