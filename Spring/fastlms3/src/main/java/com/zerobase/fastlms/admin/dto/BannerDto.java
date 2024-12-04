package com.zerobase.fastlms.admin.dto;

import com.zerobase.fastlms.admin.entity.Banner;
import lombok.*;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BannerDto {
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

    //추가컬럼
    long totalCount;
    long seq;

    public static BannerDto of(Banner banner) {
        return BannerDto.builder()
                .id(banner.getId())
                .bannerName(banner.getBannerName())
                .bannerLink(banner.getBannerLink())
                .open(banner.getOpen())
                .sortSequence(banner.getSortSequence())
                .show(banner.isShow())
                .registerDate(banner.getRegisterDate())
                .fileName(banner.getFileName())
                .urlFileName(banner.getUrlFileName())
                .build();
    }

    public String getRegDtText() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd HH:mm:ss");
        return registerDate != null ? registerDate.format(formatter) : "";
    }
}
