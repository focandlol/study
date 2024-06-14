package greedy;

import java.util.Scanner;

public class BaekJoon1541 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String next = sc.next();
        String[] split = next.split("-");
        int total = 0;

        for(int i=0; i<split.length; i++){
            int sum = 0;
            String[] plus = split[i].split("\\+");
            for(int j=0; j<plus.length; j++){
                sum += Integer.parseInt(plus[j]);
            }
            if(i==0){
                total += sum;
            }else{
                total -= sum;
            }
        }
        System.out.println(total);
    }
}
