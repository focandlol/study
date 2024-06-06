package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class BaekJoon2164 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int num = sc.nextInt();
        int result = 0;
        Deque<Integer> deque = new ArrayDeque<>();

        for(int i = 1; i<=num; i++){
            deque.add(i);
        }
        while(deque.size() > 0){
            if(deque.size() == 1){
                result = deque.getLast();
                break;
            }
            deque.poll();
            if(deque.size() == 0){
                break;
            }
            Integer peek = deque.peek();
            deque.poll();
            deque.add(peek);
            result = deque.getLast();
        }
        System.out.println(result);
    }
}
