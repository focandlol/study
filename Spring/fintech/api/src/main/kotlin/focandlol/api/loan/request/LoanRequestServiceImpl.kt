package focandlol.api.loan.request

import focandlol.api.loan.GenerateKey
import focandlol.api.loan.encrypt.EncryptComponent
import focandlol.domain.repository.UserInfoRepository
import focandlol.kafka.enum.KafkaTopic
import focandlol.kafka.producer.LoanRequestSender
import org.springframework.stereotype.Service

@Service
class LoanRequestServiceImpl(
    private val generateKey: GenerateKey,
    private val userInfoRepository: UserInfoRepository,
    private val encryptComponent: EncryptComponent,
    private val loanRequestSender: LoanRequestSender
):
    LoanRequestService {
    override fun loanRequestMain(loanRequestInputDto: LoanRequestDto.LoanRequestInputDto):
        LoanRequestDto.LoanRequestResponseDto {

        val userKey = generateKey.generateUserKey()

        loanRequestInputDto.userRegistrationNumber =
            encryptComponent.encryptString(loanRequestInputDto.userRegistrationNumber)

        val userInfoDto = loanRequestInputDto.toUserInfoDto(userKey)

        saveUserInfo(userInfoDto)

        loanRequestReview(userInfoDto)

        return LoanRequestDto.LoanRequestResponseDto(userKey)
    }

    override fun saveUserInfo(userInfoDto: UserInfoDto) =
        userInfoRepository.save(userInfoDto.toEntity())


    override fun loanRequestReview(userInfoDto: UserInfoDto) {
        loanRequestSender.sendMessage(KafkaTopic.LOAN_REQUEST,userInfoDto.toLoanRequestKafkaDto())
    }


}