package EulerPhi;

import java.util.Scanner;

public class BaekJoon11689 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long a = n;

        for (long i = 2; i <= Math.sqrt(a); i = i + 1) {
            if(a % i == 0){
                n = n - (n/i);
            }
            while(a % i == 0){
                a = a/i;
            }
        }

        if (a!=1) {
            n = n-(n/a);
        }
        System.out.println(n);
    }

    private static boolean sosu(long n) {
        boolean sosu = true;
        for (long i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                sosu = false;
            }
        }
        return sosu;
    }
}