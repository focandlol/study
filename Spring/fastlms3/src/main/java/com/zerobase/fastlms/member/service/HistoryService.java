package com.zerobase.fastlms.member.service;

import com.zerobase.fastlms.admin.dto.HistoryDto;
import com.zerobase.fastlms.member.entity.History;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public interface HistoryService {
    public LocalDateTime save(String userId, String userAgent, String ip);
    public List<HistoryDto> getHistory(String userId);

}
