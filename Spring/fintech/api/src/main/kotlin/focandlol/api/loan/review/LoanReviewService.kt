package focandlol.api.loan.review

import focandlol.domain.domain.LoanReview

interface LoanReviewService {

    fun loanReviewMain(userKey: String): LoanReviewDto.LoanReviewResponseDto

    fun getLoanResult(userKey: String): LoanReview?
}