package stackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BaekJoon2346 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        int[] arr = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int a = Integer.parseInt(st.nextToken());
            arr[i] = a;
        }

        for(int i=2; i<=n; i++){
            deque.add(i);
        }
        sb.append(1).append(" ");
        int poll = arr[1];

        while(deque.size() > 0){
            if(poll > 0){
                for(int i=1; i<poll; i++){
                    deque.add(deque.poll());
                }
                int c = deque.poll();
                sb.append(c).append(" ");
                poll = arr[c];
            }else{
                for(int i=1; i<-poll; i++){
                    deque.addFirst(deque.pollLast());
                }
                int c = deque.pollLast();
                sb.append(c).append(" ");
                poll = arr[c];
            }
        }

        System.out.println(sb);
    }
}

