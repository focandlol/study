package generic.test.ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListEx3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();
        int total = 0;

        System.out.println("n개의 정수를 입력하세여 종료(0)");
        while(true){
            int input = sc.nextInt();
            if(input == 0){
                break;
            }
            list.add(input);
            total += input;
        }

        double average = (double) total / list.size();

        System.out.println("입력한 정수의 합계 : "+ total);
        System.out.println("입력한 정수의 평균 : " + average);

    }
}
