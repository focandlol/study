package focandlol.relastic.service;

import focandlol.relastic.document.UserDocument;
import focandlol.relastic.dto.UserCreateRequestDto;
import focandlol.relastic.dto.UserDocumentDto;
import focandlol.relastic.dto.UserUpdateRequestDto;
import focandlol.relastic.repository.UserDocumentRepository;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

  private final UserDocumentRepository userDocumentRepository;

  public UserDocumentDto createUser(UserCreateRequestDto dto) {
    return UserDocumentDto.from(userDocumentRepository.save(dto.toUserDocument()));
  }

  public Page<UserDocumentDto> findUsers(Pageable pageable) {
    Page<UserDocument> all = userDocumentRepository.findAll(pageable);
    List<UserDocumentDto> collect = all.stream().map(p -> UserDocumentDto.from(p))
        .collect(Collectors.toList());

    return new PageImpl<UserDocumentDto>(collect, pageable, all.getTotalElements());
  }

  public UserDocumentDto getUser(String id) {
    return UserDocumentDto.from(userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found")));
  }

  public void updateUser(String id, UserUpdateRequestDto dto) {
    UserDocument document = userDocumentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("User not found"));

    document.setName(dto.getName());
    document.setAge(dto.getAge());
    document.setIsActive(dto.getIsActive());
    userDocumentRepository.save(document);
  }

}
