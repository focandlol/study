package kkm.securityProject.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
public class AccountDto {

    private Long id;
    private String username;
    private String password;
    private int age;
    private String roles;
}
