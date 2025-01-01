package focandlol.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USERNAME_ALREADY_USE("이미 사용중인 이메일 입니다.",400),
    MANAGER_NOT_FOUND("해당 매니저가 없습니다",400),
    ALREADY_EXISTS_STORE("가게가 이미 있습니다.",400),
    STORE_NOT_FOUND("해당 가게가 없습니다",400),
    ANOTHER_MANAGER("가게 주인이 다릅니다",400),
    CUSTOMER_NOT_FOUND("해당 고객이 없습니다",400),
    RESERVATION_IS_OVER("해당 인원수 만큼의 자리가 없습니다",400),
    RESERVATION_NOT_FOUND("해당 예약이 없습니다",400),
    ANOTHER_CUSTOMER("고객이 다릅니다",400),
    RESERVATION_SAME_TYPE("이미 같은 예약 상태 입니다",400),
    RESERVATION_IS_NOT_APPROVE("승인된 예약이 아닙니다.",400),
    ARRIVE_TIME_IS_OVER("예약 시간 10분전에 도착해야합니다", 400),


    ;

    private final String description;
    private final int status;
}
