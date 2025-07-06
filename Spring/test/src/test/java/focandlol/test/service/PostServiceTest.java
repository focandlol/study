package focandlol.test.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

import focandlol.test.domain.PostEntity;
import focandlol.test.dto.PostRequestDto;
import focandlol.test.repository.PostRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PostServiceTest {

  @InjectMocks
  private PostService postService;

  @Mock
  private PostRepository postRepository;

  @Test
  @DisplayName("post create1")
  void create_테스트1() {
    //given
    PostRequestDto dto = new PostRequestDto();
    dto.setTitle("제목1");
    dto.setContent("내용1");

    PostEntity saved = new PostEntity();
    saved.setId(1L);
    saved.setTitle("제목");
    saved.setContent("내용");

    given(postRepository.save(any(PostEntity.class))).willReturn(saved);

    //when
    Long resultId = postService.create(dto);

    //then
    assertTrue(resultId instanceof Long);
  }

  @Test
  @DisplayName("post create2")
  void create_테스트2() {
    //given
    PostRequestDto dto = new PostRequestDto();
    dto.setTitle("제목2");
    dto.setContent("내용2");

    PostEntity saved = new PostEntity();
    saved.setId(1L);
    saved.setTitle("제목");
    saved.setContent("내용");

    given(postRepository.save(any(PostEntity.class))).willReturn(saved);

    //when
    postService.create(dto);

    //then
    verify(postRepository).save(any(PostEntity.class));
  }

  @Test
  @DisplayName("post create3 empty title")
  void create_테스트3(){
    //given
    PostRequestDto dto = new PostRequestDto();
    dto.setTitle("");
    dto.setContent("내용");

    //when

    //then
    assertThrows(IllegalArgumentException.class, () -> postService.create(dto));
  }

}