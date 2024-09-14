package test;

import java.util.Scanner;

/**
 * 고경민
 */
public class aa {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("[과세금액 계산 프로그램]");
        System.out.print("연소득을 입력해 주세요.:");
        long income = sc.nextLong();

        sc.close();

        long[] money = {0,12000000,46000000,88000000,150000000,300000000,500000000,1000000000,income};
        int[] tax = {0,6,15,24,35,38,40,42,45};
        int[] nu = {0,1080000,5220000,14900000,19400000,25400000,35400000,65400000};

        System.out.printf("[세율에 의한 세금]:\t\t\t%5d\n",tax(income,money,tax));
        System.out.printf("[누진공제 계산에 의한 세금]:\t%5d\n",nu(income,money,tax,nu));
    }

    private static long nu(long income, long[] money, int[] tax, int[] nu) {
        long sum = 0;
        for(int i=1; i<money.length; i++){
            if(income <= money[i]){
                sum = (income * tax[i] / 100) - nu[i-1];
                break;
            }
        }
        return sum;
    }

    private static long tax(long income,long[] money,int[] tax) {
        long sum = 0;

        for(int i=1; i<money.length; i++) {
            if(income >= money[i]) {
                System.out.printf("%10d * %2d%% = \t%10d\n",money[i]-money[i-1],tax[i],(money[i]-money[i-1])*tax[i]/100);
                sum += (money[i]-money[i-1])*tax[i]/100;
                if(income == money[i])break;
            }
            else{
                System.out.printf("%10d * %2d%% = \t%10d\n",income-money[i-1],tax[i],(income-money[i-1])*tax[i]/100);
                sum += (income-money[i-1])*tax[i]/100;
                break;
            }
        }
        System.out.println();
        return sum;
    }
}