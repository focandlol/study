package stackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BaekJoon12789 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n  = Integer.parseInt(st.nextToken());
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        int last = n;
        int start = 1;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            queue.add(Integer.parseInt(st.nextToken()));
        }
        stack.push(10000);
        queue.add(10000);
        while(queue.size() > 1 || stack.size() > 1) {
            if (queue.peek() == start) {
                start++;
                queue.poll();
            }else{
                if(stack.peek() == start) {
                    stack.pop();
                    start++;
                }else {
                    if(queue.peek() != 10000) {
                        stack.push(queue.poll());
                    }else{
                        System.out.println("Sad");
                        return;
                    }
                }
            }
        }
        System.out.println("Nice");
    }
}

