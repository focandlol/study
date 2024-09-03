package test;

import java.util.Scanner;

public class test3 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[입장권 계산]");
        System.out.print("나이를 입력해 주세요.(숫자):");
        int age = sc.nextInt();
        System.out.print("입장시간을 입력해 주세요.(숫자입력):");
        int time = sc.nextInt();
        System.out.print("국가유공자 여부를 입력해 주세요.(y/n):");
        String guk = sc.next();
        System.out.print("복지카드 여부를 입력해 주세요.(y/n):");
        String bok = sc.next();
        sc.close();

        int gap = 10000;
        if(age < 3){
            gap = 0;
        }else if(age < 13 || time >= 17){
            gap = 4000;
        }else if(guk.equals("y") || bok.equals("y")){
            gap = 8000;
        }
        System.out.println("입장료: " + gap);

    }
}
