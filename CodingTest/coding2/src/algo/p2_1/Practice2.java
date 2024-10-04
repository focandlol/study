package algo.p2_1;

import java.util.ArrayList;

public class Practice2 {
    public static void main(String[] args) {
        solutin(15);
    }

    private static void solutin(int n) {
        int a = 2;
        int b = 1;

        ArrayList<Integer> list = new ArrayList<>();

        while(Math.pow(a,2) - Math.pow(b,2) > 0){
            if(Math.pow(a,2) - Math.pow(b,2) == n){
                list.add(a);
                a++;
                b++;
            }
            else if(Math.pow(a,2) - Math.pow(b,2) > n){
                b++;
            }else{
                a++;
            }
        }

        for (Integer i : list) {
            System.out.print(i + " ");
        }
    }
}
