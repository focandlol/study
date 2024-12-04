package com.zerobase.fastlms.admin.model;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BannerInput {

    Long id;

    String bannerName;  //배너명
    String bannerLink;   //배너 링크 주소

    int open;   //오픈 방법
    int sortSequence;    //정렬 순서
    boolean show; //공개 여부
    String alterText;

    LocalDateTime registerDate;

    String fileName;
    String urlFileName;

    //삭제를 위한
    String idList;
}
