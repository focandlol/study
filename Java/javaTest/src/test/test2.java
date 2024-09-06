package test;

import java.util.Scanner;

/**
 * 고경민
 */
public class test2 {
    public static void main(String[] args) {
        System.out.println("캐시백 계산");
        System.out.print("결제 금액을 입력해 주세요.(금액):");
        int gap = new Scanner(System.in).nextInt();
        int cash = (gap*0.1 >= 300) ? 300 : (int)(gap * 0.1)/100 * 100;
        System.out.printf("결제 금액은 %d원이고, 캐시백은 %d원 입니다.",gap,cash);
    }
}


