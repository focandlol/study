package focandlol.api.loan.request

import focandlol.api.loan.GenerateKey
import focandlol.api.loan.encrypt.EncryptComponent
import focandlol.domain.repository.UserInfoRepository
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,
    private val encryptComponent: EncryptComponent
):
    LoanRequestService {
    override fun loanRequestMain(loanRequestInputDto: LoanRequestDto.LoanRequestInputDto):
        LoanRequestDto.LoanRequestResponseDto {

        val userKey = generateKey.generateUserKey()
        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)

        saveUserInfo(
            loanRequestInputDto.toUserInfoDto(userKey)
        )

        loanRequestReview("")

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())


    override fun loanRequestReview(userKey: String) {

    }


}