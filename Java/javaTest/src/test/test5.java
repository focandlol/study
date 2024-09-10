package test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Scanner;

/**
 * 고경민
 */
public class test5 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[달력 출력 프로그램]");
        System.out.print("달력의 년도를 입력해 주세요.(yyyy):");
        int year = sc.nextInt();
        System.out.print("달력의 월을 입력해 주세요.(mm):");
        int month = sc.nextInt();
        sc.close();
        print(year,month);
    }

    private static void print(int year, int month) {
        String[][] arr = new String[3][];

        for(int i=-1; i<=1; i++) {
            arr[i+1] = create(year,month+i);
        }

        for(int i=-1; i<=1; i++){
            int y = year;
            int m = month+i;
            if(m > 12){
                y += 1;
                m = 1;
            }else if(m < 1){
                y -= 1;
                m = 12;
            }
            System.out.printf("[%d년 %02d월]\t\t\t\t\t",y,m);
        }
        System.out.println();

        for (int i = 0; i < 3; i++) {
            System.out.print("일\t월\t화\t수\t목\t금\t토\t\t");
        }

        System.out.println();

        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(arr[j][i] + "\t");
            }
            System.out.println();
        }
    }

    private static String[] create(int year, int month) {
        if(month > 12){
            year += 1;
            month = 1;
        }else if(month < 1){
            year -= 1;
            month = 12;
        }

        LocalDate firstDay = LocalDate.of(year,month,1);
        int dayCount = firstDay.lengthOfMonth();
        DayOfWeek dayOfWeek = firstDay.getDayOfWeek();

        int value = dayOfWeek.getValue();
        value = (value == 7) ? 0 : value;

        StringBuffer sf = new StringBuffer();
        for (int i = 0; i < value; i++) {
            sf.append("\t");
        }

        int index = 0;
        String[] arr = new String[6];
        for(int i=1; i<=dayCount; i++) {
            sf.append(String.format("%02d\t", i));
            if((value + i) % 7 == 0 || i == dayCount){
                if(i == dayCount) {
                    int a = LocalDate.of(year,month,i).getDayOfWeek().getValue();
                    a = (a == 7) ? 0 : a;
                    for(int k=0; k<6-a; k++){
                        sf.append("1\t");
                    }
                }
                arr[index] = sf.toString();
                sf = new StringBuffer();
                index++;
            }

        }
        while (index < 6) {
            sf = new StringBuffer();
            for(int i = 0; i<7; i++){
                sf.append("1\t");
            }
            arr[index] = sf.toString();
            index++;
        }
        return arr;
    }
}

