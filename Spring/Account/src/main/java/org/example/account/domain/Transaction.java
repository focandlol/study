package org.example.account.domain;

import javax.persistence.*;
import lombok.*;
import org.example.account.type.TransactionResultType;
import org.example.account.type.TransactionType;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

    @Enumerated(EnumType.STRING)
    private TransactionType transactionType;

    @Enumerated(EnumType.STRING)
    private TransactionResultType transactionResult;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private Long amount;

    private Long balanceSnapshot;

    private String transactionId;

    private LocalDateTime transactedAt;
}
