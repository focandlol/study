package focandlol.doc.controller;

import focandlol.doc.model.User;
import focandlol.doc.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserApiController {

  private final UserService userService;

  @PostMapping("")
  public ResponseEntity<User> createUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.create(user));
  }

  @GetMapping("{id}")
  public ResponseEntity<User> getUser(@PathVariable long id) {
    return ResponseEntity.ok(userService.read(id));
  }

  @PutMapping("")
  public ResponseEntity<User> updateUser(@RequestBody User user) {
    return ResponseEntity.ok(userService.update(user));
  }

  @DeleteMapping("{id}")
  public ResponseEntity<Boolean> deleteUser(@PathVariable long id) {
    return ResponseEntity.ok(userService.delete(id));
  }



}
