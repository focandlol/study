package com.zerobase.fastlms.member.repository;

import com.zerobase.fastlms.member.entity.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface HistoryRepository extends JpaRepository<History, Long> {
    List<History> findByUserId(String userId);

    Optional<LocalDateTime> getLastLoginDateByUserId(String userId);
}
