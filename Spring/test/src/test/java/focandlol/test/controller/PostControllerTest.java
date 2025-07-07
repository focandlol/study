package focandlol.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import focandlol.test.dto.PostRequestDto;
import focandlol.test.service.PostService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureHttpGraphQlTester;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

@WebMvcTest(controllers = PostController.class)
class PostControllerTest {

  @Autowired
  MockMvc mockMvc;

  @Autowired
  ObjectMapper objectMapper;

  @MockBean
  PostService postService;

  @Test
  @DisplayName("receive 200 ok")
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  void post_method_테스트1() throws Exception {
    //given
    PostRequestDto dto = new PostRequestDto();
    dto.setTitle("title");
    dto.setContent("content");

    //when, then
    mockMvc.perform(post("/post")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));
  }

  @Test
  @DisplayName("receive 403")
  void post_method_테스트1_1() throws Exception {
    //given
    PostRequestDto dto = new PostRequestDto();
    dto.setTitle("title");
    dto.setContent("content");

    //when, then
    mockMvc.perform(post("/post")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().is4xxClientError());
  }

  @Test
  @DisplayName("call dependencies")
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  void post_method_테스트2() throws Exception {
    //given
    PostRequestDto dto = new PostRequestDto();
    dto.setTitle("title");
    dto.setContent("content");

    given(postService.create(any(PostRequestDto.class))).willReturn(1L);

    //when,then
    mockMvc.perform(post("/post")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.id").value(1));

    verify(postService).create(any(PostRequestDto.class));
  }

  @Test
  @DisplayName("400")
  @WithMockUser(username = "admin", roles = {"ADMIN"})
  void post_method_테스트3() throws Exception {
    //given
    PostRequestDto dto = new PostRequestDto();
    dto.setTitle("");
    dto.setContent("content");

    given(postService.create(any(PostRequestDto.class))).willThrow(new IllegalArgumentException());

    //when, then
    mockMvc.perform(post("/post")
            .with(csrf())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(dto)))
        .andExpect(status().isBadRequest())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON));

  }
}