package deque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BaekJoon1021 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        LinkedList<Integer> deque = new LinkedList();
        for(int i=1; i<=n; i++){
            deque.add(i);
        }

        Queue<Integer> queue = new LinkedList<>();
        st = new StringTokenizer(br.readLine());
        int count = 0;
        while(st.hasMoreTokens()) {
           int loc = deque.indexOf(Integer.parseInt(st.nextToken()));
           int l = Math.abs(0 - loc);
           int r = Math.abs(deque.size() - 1 - loc);

           if (l > r) {
               for (int j = 0; j < r+1; j++) {
                   deque.addFirst(deque.pollLast());
                   count++;
               }
           } else {
               for (int j = 0; j < l; j++) {
                   deque.add(deque.poll());
                   count++;
               }
           }
           deque.poll();
       }
        System.out.println(count);

    }
}
