package focandlol.css.service

import focandlol.css.dto.LoanRequestDto
import focandlol.css.dto.LoanResultDto
import org.springframework.stereotype.Service

@Service
class LoanReviewService {
    fun loanReview(loanRequestDto: LoanRequestDto.RequestInputDto): LoanResultDto.ResponseDto{
        if(loanRequestDto.userIncomeAmount < 0) throw RuntimeException("Invalid userIncomeAmount Param")
        if(loanRequestDto.userIncomeAmount < 10000000)
            return LoanResultDto.ResponseDto(loanRequestDto.userKey,0.0,1000000)
        if(loanRequestDto.userIncomeAmount < 20000000)
            return LoanResultDto.ResponseDto(loanRequestDto.userKey,10.0,2000000)
        if(loanRequestDto.userIncomeAmount < 30000000)
            return LoanResultDto.ResponseDto(loanRequestDto.userKey,9.0,3000000)
        if(loanRequestDto.userIncomeAmount < 40000000)
            return LoanResultDto.ResponseDto(loanRequestDto.userKey,8.0,4000000)
        if(loanRequestDto.userIncomeAmount < 50000000)
            return LoanResultDto.ResponseDto(loanRequestDto.userKey,7.0,5000000)
        if(loanRequestDto.userIncomeAmount >= 50000000)
            return LoanResultDto.ResponseDto(loanRequestDto.userKey,6.0,6000000)
        throw RuntimeException("Invalid userIncomeAmount Param")
    }
}