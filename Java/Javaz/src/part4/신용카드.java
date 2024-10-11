package part4;

public class 신용카드{
    public long 카드번호;
    public String 카드소유주;

    public long 카드사용누적금액;
    public void 카드사용(long 카드사용금액){
        카드사용누적금액 += 카드사용금액;
    }
}
