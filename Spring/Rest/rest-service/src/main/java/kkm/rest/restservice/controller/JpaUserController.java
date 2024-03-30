package kkm.rest.restservice.controller;

import jakarta.validation.Valid;
import kkm.rest.restservice.bean.Post;
import kkm.rest.restservice.bean.User;
import kkm.rest.restservice.exception.UserNotFoundException;
import kkm.rest.restservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequiredArgsConstructor
@RequestMapping("/jpa")
public class JpaUserController {

    private final UserRepository userRepository;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){

        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public ResponseEntity retrieveUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException("id = "+ id);
        }

        System.out.println("user = " + user.get());
        EntityModel<User> entityModel = EntityModel.of(user.get());
        entityModel.add(linkTo(methodOn(this.getClass()).retrieveAllUsers()).withRel("all-users"));
        return ResponseEntity.ok(entityModel);


    }

    @DeleteMapping("/users/{id}")
    public void deleteUserById(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User savedUser = userRepository.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedUser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    public List<Post> retrieveAllPostsByUser(@PathVariable int id){
        Optional<User> user = userRepository.findById(id);

        return user.get().getPosts();
    }
}
