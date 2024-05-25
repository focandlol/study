package generic.test.ex3;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ListEx2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Integer> list = new ArrayList<Integer>();

        System.out.println("n개의 정수를 입력하세여 종료(0)");
        while(true){
            int input = sc.nextInt();
            if(input == 0){
                break;
            }
            list.add(input);
        }

        for(int i=0; i<list.size(); i++){
            System.out.print(list.get(i));
            if(i < list.size()-1){
                System.out.print(", ");
            }
        }
    }
}
