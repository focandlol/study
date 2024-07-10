package stackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class BaekJoon28278 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        Deque<Integer> deque = new ArrayDeque<>();
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            if(a == 1){
                int b = Integer.parseInt(st.nextToken());
                deque.push(b);
            }else if(a == 2){
                if(deque.size() == 0){
                    sb.append(-1).append("\n");
                }else{
                    sb.append(deque.pop()).append("\n");
                }
            }else if(a == 3){
                sb.append(deque.size()).append("\n");
            }else if(a == 4){
                if(deque.size() == 0){
                    sb.append(1).append("\n");
                }else{
                    sb.append(0).append("\n");
                }
            }else{
                if(deque.size() != 0){
                    sb.append(deque.peek()).append("\n");
                }else{
                    sb.append(-1).append("\n");
                }
            }
        }
        System.out.println(sb);
    }
}
