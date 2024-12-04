package com.zerobase.fastlms.admin.service;

import com.zerobase.fastlms.admin.dto.BannerDto;
import com.zerobase.fastlms.admin.model.BannerInput;
import com.zerobase.fastlms.admin.model.BannerParam;

import java.util.List;

public interface BannerService {
    List<BannerDto> list(BannerParam parameter);
    boolean add(BannerInput parameter);
    BannerDto getById(long id);
    boolean set(BannerInput parameter);
    boolean edit(BannerInput parameter);
    boolean del(String idList);
//    List<BannerDto> showList(BannerParam parameter);
}
