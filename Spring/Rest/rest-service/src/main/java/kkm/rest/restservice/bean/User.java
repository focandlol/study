package kkm.rest.restservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;

@Data
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password","ssn"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
public class User {

    @Schema(title = "사용자 id", description = "사용자 id는 자동 생성")
    private Integer id;

    @Schema(title = "사용자 dlfma", description = "사용자 이름 입력")
    @Size(min = 2)
    private String name;

    @Schema(title = "사용자 등록일", description = "등록일 입력, 입력하지 않을 시 현재 날짜 지정")
    private Date joinDate;

    //@JsonIgnore
    @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호 입력")
    private String password;
    //@JsonIgnore
    @Schema(title = "사용자 주민번호", description = "사용자 주민번호 입력")
    private String ssn;
}
