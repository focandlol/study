package greedy;

import java.util.Scanner;

public class BaekJoon1541Sol2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String next = sc.next();
        String[] split = next.split("-");
        int[] sumArr = new int[split.length];

        for(int i=0; i<split.length; i++){
            int sum = 0;
            String[] plus = split[i].split("\\+");
            for(int j=0; j<plus.length; j++){
                sum += Integer.parseInt(plus[j]);
            }
            sumArr[i] = sum;
        }

        for(int i=1; i<sumArr.length; i++){
            sumArr[0] -= sumArr[i];
        }
        System.out.println(sumArr[0]);
    }
}

