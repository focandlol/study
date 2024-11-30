package focandlol.api.loan.review

import focandlol.api.exception.CustomException
import focandlol.api.exception.ErrorResponse
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice(basePackageClasses = [LoanReviewController::class])
class LoanReviewControllerAdvice {

    @ExceptionHandler(CustomException::class)
    fun handleCustomException(e: CustomException) =
        ErrorResponse(e).toResponseEntity()

}