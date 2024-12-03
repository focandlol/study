package com.zerobase.fastlms.member.service.impl;

import com.zerobase.fastlms.admin.dto.HistoryDto;
import com.zerobase.fastlms.member.entity.History;
import com.zerobase.fastlms.member.repository.HistoryRepository;
import com.zerobase.fastlms.member.service.HistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class HistoryServiceImpl implements HistoryService {

    private final HistoryRepository historyRepository;

    @Override
    public LocalDateTime save(String userId, String userAgent, String ip) {
        History save = historyRepository.save(
                History.builder()
                        .userId(userId)
                        .loginDate(LocalDateTime.now())
                        .userAgent(userAgent)
                        .ip(ip)
                        .build()
        );
        return save.getLoginDate();
    }

    @Override
    public List<HistoryDto> getHistory(String userId) {
        return historyRepository.findByUserId(userId)
                .stream().map(a -> HistoryDto.of(a))
                .collect(Collectors.toList());
    }
}
