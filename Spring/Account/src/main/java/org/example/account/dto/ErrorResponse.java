package org.example.account.dto;

import jdk.jfr.StackTrace;
import lombok.*;
import org.example.account.type.ErrorCode;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ErrorResponse {
    private ErrorCode errorCode;
    private String errorMessage;
}
