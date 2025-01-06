package focandlol.resproject.global.entity;

import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

/**
 * 공통 엔티티 속성 정의 클래스
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
@Getter
public class BaseEntity {

    /**
     * 생성일
     */
    @CreatedDate
    private LocalDateTime createdAt;

    /**
     * 수정일
     */
    @LastModifiedDate
    private LocalDateTime lastModifiedAt;
}
