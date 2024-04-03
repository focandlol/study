package kkm.rest.restservice.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
//import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//@Getter
//@Setter
@Data
@NoArgsConstructor
//@JsonIgnoreProperties(value = {"password","ssn"})
//@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Entity
@Table(name = "users")
public class User {

    @Id
   // @Schema(title = "사용자 id", description = "사용자 id는 자동 생성")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

   // @Schema(title = "사용자 dlfma", description = "사용자 이름 입력")
    @Size(min = 2)
    private String name;

   // @Schema(title = "사용자 등록일", description = "등록일 입력, 입력하지 않을 시 현재 날짜 지정")
    private LocalDateTime joinDate;

    //@JsonIgnore
   // @Schema(title = "사용자 비밀번호", description = "사용자 비밀번호 입력")
    private String password;
    //@JsonIgnore
   // @Schema(title = "사용자 주민번호", description = "사용자 주민번호 입력")
    private String ssn;


    @OneToMany(mappedBy = "user")
    private List<Post> posts = new ArrayList<>();

    public User(Integer id, String name, LocalDateTime joinDate, String password, String ssn) {
        this.id = id;
        this.name = name;
        this.joinDate = joinDate;
        this.password = password;
        this.ssn = ssn;
    }
}
