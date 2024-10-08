package stream;

import java.util.Arrays;

public class Practice {
    public static void main(String[] args) {

        // 예제: 1~10 숫자 중 짝수 들의 합
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        int sum = 0;

        for (int num: arr) {
            if (num % 2 == 0) {
                sum += num;
            }
        }
        System.out.println("sum = " + sum);


        // 스트림으로 구현

        sum = Arrays.stream(arr).filter(n -> n % 2 == 0).reduce((x, y) -> x+y).getAsInt();
        System.out.println("sum = " + sum);


    }

}
