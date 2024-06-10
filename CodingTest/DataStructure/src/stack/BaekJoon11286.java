package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static java.lang.Math.abs;

public class BaekJoon11286 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int num = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>((a,b) -> {
            if(abs(a) == abs(b)){
                return a > b ? 1 : -1;
            }
            return abs(a) - abs(b);
        });
        Queue<Integer> a = new LinkedList<>();
        for(int i=0; i<num; i++){
            int input = Integer.parseInt(br.readLine());

//            if(input == 0) {
//                if (pq.isEmpty()) {
//                    System.out.println("0");
//                }else{
//                    System.out.println(pq.poll());
//                }
//            }else{
//                pq.add(input);
//            }
            pq.add(input);
            a.add(input);
        }
        System.out.println(a);
        System.out.println(pq);
        pq.poll();
        System.out.println(pq);
    }
}
