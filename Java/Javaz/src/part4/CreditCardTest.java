package part4;

public class CreditCardTest {
    public static void main(String[] args) {
        CreditCard myCard = new CreditCard();
        myCard.cardNumber = 12345;
        myCard.cardOwner = "kkm";

        System.out.println(myCard.cardNumber);
        System.out.println(myCard.cardOwner);
    }
}
