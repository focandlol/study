package stack;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Stack;

public class BaekJoon1874 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int[] arr = new int[num];
        boolean result = true;
        StringBuilder sb = new StringBuilder();
        Stack<Integer> deque = new Stack<>();
        for(int i=0; i<num; i++){
            arr[i] = sc.nextInt();
        }

        int start = 1;
        for(int i=0; i<num; i++){
            if(arr[i] >= start){
                while(start <= arr[i]){
                    deque.push(start++);
                    sb.append("+\n");
                }
                deque.pop();
                sb.append("-\n");
            }else{
                Integer pop = deque.pop();
                if(arr[i] < pop){
                    System.out.println("NO");
                    result = false;
                    break;
                }else{
                    sb.append("-\n");
                }
            }

        }
        if(result){
            System.out.println(sb);
        }
    }
}
