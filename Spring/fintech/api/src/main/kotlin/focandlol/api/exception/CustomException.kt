package focandlol.api.exception

class CustomException(val customErrorCode: CustomErrorCode) : RuntimeException() {
}