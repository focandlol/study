package test;

import java.util.Random;
import java.util.Scanner;

/**
 * 고경민
 */
public class test4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StringBuffer bf = new StringBuffer();
        System.out.println("주민등록번호 계산");
//        System.out.print("출생년도를 입력해 주세요.(yyyy):");
//        String year = sc.next();
//        System.out.print("출생월을 입력해 주세요.(mm):");
//        String month = sc.next();
//        System.out.print("출생일을 입력해 주세요.(dd):");
//        String day = sc.next();
        System.out.print("출생일을 입력해 주세요.(yyyy.mm.dd):");
        String date = sc.next();
        System.out.print("성별을 입력해 주세요.(m/f):");
        String mf = sc.next();
        sc.close();
        String[] split = date.split("\\.");

//        bf.append(year.substring(2,4))
//                .append(month)
//                .append(day)
        bf.append(split[0].substring(2,4))
                .append(split[1])
                .append(split[2])
                .append("-")
                .append((mf.equals("m"))?3:4)
                .append(new Random().nextInt(999999) + 1);

        System.out.println(bf);

    }
}
