package focandlol.relastic.controller;

import focandlol.relastic.dto.UserCreateRequestDto;
import focandlol.relastic.dto.UserDocumentDto;
import focandlol.relastic.dto.UserUpdateRequestDto;
import focandlol.relastic.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

  private final UserService userService;

  @PostMapping
  public UserDocumentDto createUser(@RequestBody UserCreateRequestDto dto) {
    return userService.createUser(dto);
  }

  @GetMapping()
  public Page<UserDocumentDto> findUsers(Pageable pageable) {
    return userService.findUsers(pageable);
  }

  @GetMapping("/{id}")
  public UserDocumentDto findUserById(@PathVariable String id) {
    return userService.getUser(id);
  }

  @PutMapping("/{id}")
  public void updateUser(@PathVariable String id, @RequestBody UserUpdateRequestDto dto) {
    userService.updateUser(id, dto);
  }

}
