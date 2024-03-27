package kkm.rest.restservice.controller;

import jakarta.annotation.PostConstruct;
import jakarta.validation.Valid;
import kkm.rest.restservice.RestServiceApplication;
import kkm.rest.restservice.bean.User;
import kkm.rest.restservice.dao.UserDaoService;
import kkm.rest.restservice.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponents;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserDaoService service;

    @GetMapping("/users")
    public List<User> retrieveAllUsers(){
        return service.findAll();
    }

    @GetMapping("/users/{id}")
    public User retrieveUser(@PathVariable int id){
        User user = service.findOne(id);
        if(user == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        return user;
    }

    @PostMapping("/users")
    public ResponseEntity<User> createUser(@Valid @RequestBody User user){
        User save = service.save(user);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(save.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable int id){
        User deleteUser = service.deleteById(id);

        if(deleteUser == null){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        return ResponseEntity.noContent().build();
    }
}
