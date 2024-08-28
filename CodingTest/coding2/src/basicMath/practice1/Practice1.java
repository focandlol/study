package basicMath.practice1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Practice1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        ArrayList<Integer>[] list = new ArrayList[n];
        for(int i=0; i<n; i++){
            list[i] = new ArrayList<>();

            for(int j=0; j<i+1; j++){
                if(j == 0 || j == i){
                    list[i].add(1);
                }else{
                    list[i].add(list[i-1].get(j-1) + list[i-1].get(j));
                }
            }
        }

        System.out.println(Arrays.toString(list));

    }

}
