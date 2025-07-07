package focandlol.test.controller;

import focandlol.test.dto.PostRequestDto;
import focandlol.test.service.PostService;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

  private final PostService postService;

  @PostMapping("/post")
  public ResponseEntity<?> postMethod(@RequestBody PostRequestDto dto){
    Long resultId = postService.create(dto);
    Map<String, Object> map = Map.of("id", resultId);

    return ResponseEntity.ok().contentType(MediaType.APPLICATION_JSON).body(map);
  }

}
