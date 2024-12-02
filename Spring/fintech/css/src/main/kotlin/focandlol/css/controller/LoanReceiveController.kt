package focandlol.css.controller

import focandlol.css.dto.LoanRequestDto
import focandlol.css.dto.LoanResultDto
import focandlol.css.service.LoanReviewService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/css/api/v1")
class LoanReceiveController(
    private val loanReviewService: LoanReviewService
) {
    @PostMapping("/request")
    fun loanReceive(
        @RequestBody requestInputDto: LoanRequestDto.RequestInputDto
    ): LoanResultDto.ResponseDto =
        loanReviewService.loanReview(requestInputDto)
}