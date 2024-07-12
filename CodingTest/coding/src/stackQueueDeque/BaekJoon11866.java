package stackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BaekJoon11866 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int count = 0;

        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayDeque<Integer> out = new ArrayDeque<>();
        for(int i=0; i<n; i++) {
            deque.add(i+1);
        }

       while(deque.size() > 0){
           count++;
           if(count == k){
               out.add(deque.poll());
               count = 0;
           }else{
               deque.add(deque.poll());
           }
       }

       sb.append("<");
        for(int i=0; i<n; i++){
            if(out.size() != 1) {
                sb.append(out.poll()).append(", ");
            }else{
                sb.append(out.poll());
            }
        }
        sb.append(">");
        System.out.println(sb);
    }
}
