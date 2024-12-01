package focandlol.consumer.service

import focandlol.domain.domain.LoanReview
import focandlol.domain.repository.LoanReviewRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestService(private val loanReviewRepository: LoanReviewRepository) {

    fun loanRequest(){
        //TODO : CB Component로 요청 보내기 -> 응답값을 db에 저장
    }

    fun loanRequestToCb(){
        //todo
    }

    fun saveLoanReviewData(loanReview: LoanReview) = loanReviewRepository.save(loanReview)

}