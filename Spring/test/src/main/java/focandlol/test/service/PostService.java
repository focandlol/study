package focandlol.test.service;

import focandlol.test.domain.PostEntity;
import focandlol.test.dto.PostRequestDto;
import focandlol.test.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostService {

  private final PostRepository postRepository;

  public Long create(PostRequestDto dto){

    if(dto.getTitle() == null || dto.getTitle().isEmpty()){
      throw new IllegalArgumentException("Title cannot be null or empty");
    }

    PostEntity postEntity = new PostEntity();
    postEntity.setTitle(dto.getTitle());
    postEntity.setContent(dto.getContent());

    PostEntity save = postRepository.save(postEntity);

    return save.getId();
  }

}
