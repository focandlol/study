package com.zerobase.fastlms.admin.entity;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Banner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String bannerName;  //배너명
    String bannerLink;   //배너 링크 주소
    String alterText;

    int open;   //오픈 방법
    int sortSequence;    //정렬 순서
    boolean isShow; //공개 여부

    LocalDateTime registerDate;

    String fileName;
    String urlFileName;
}
