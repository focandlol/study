package stackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class BaekJoon18258 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static ArrayDeque<Integer> deque = new ArrayDeque<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            check(a);
        }
        System.out.println(sb);
    }

    private static void check(String a) {
        int b = 0;
        switch(a){
            case "push": deque.add(Integer.parseInt(st.nextToken())); break;
            case "pop": b = deque.isEmpty() ? -1 : deque.poll(); sb.append(b).append("\n"); break;
            case "size": sb.append(deque.size()).append("\n"); break;
            case "empty": b = deque.isEmpty() ? 1 : 0; sb.append(b).append("\n"); break;
            case "front": b = deque.isEmpty() ? -1 : deque.peek(); sb.append(b).append("\n"); break;
            case "back": b = deque.isEmpty() ? -1 : deque.peekLast(); sb.append(b).append("\n"); break;
            default: break;
        }
    }
}

