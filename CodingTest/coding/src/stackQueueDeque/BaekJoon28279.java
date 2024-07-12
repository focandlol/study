package stackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BaekJoon28279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int n = Integer.parseInt(st.nextToken());
        int b = 0;
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());

            if(a == 1){
                b = Integer.parseInt(st.nextToken());
                deque.addFirst(b);
            }else if(a == 2){
                b = Integer.parseInt(st.nextToken());
                deque.add(b);
            }else if(a==3){
                b = deque.isEmpty() ? -1 : deque.pollFirst();
                sb.append(b).append("\n");
            }else if(a == 4){
                b = deque.isEmpty() ? -1 : deque.pollLast();
                sb.append(b).append("\n");
            }else if(a == 5){
                sb.append(deque.size()).append("\n");
            }else if(a==6){
                b = deque.isEmpty() ? 1 : 0;
                sb.append(b).append("\n");
            }else if(a==7){
                b = deque.isEmpty() ? -1 : deque.peekFirst();
                sb.append(b).append("\n");
            }else if(a==8){
                b = deque.isEmpty() ? -1 : deque.peekLast();
                sb.append(b).append("\n");
            }
        }
        System.out.println(sb);

    }
}
