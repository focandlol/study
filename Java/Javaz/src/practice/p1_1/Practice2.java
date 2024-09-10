package practice.p1_1;

import java.util.Scanner;

public class Practice2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter an alphabet: ");
        char a = sc.next().charAt(0);

        if(a >= 'A' && a <= 'Z'){
            System.out.println((char)(a+32));
        }else if(a >= 'a' && a <= 'z'){
            System.out.println((char)(a-32));
        }else{
            System.out.println("알파벳 입력");
        }
    }
}
