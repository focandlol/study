package stackQueueDeque;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class BaekJoon9012 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        ArrayDeque<String> deque = new ArrayDeque<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<t; i++) {
            String[] split = br.readLine().split("");
            for(int j=0; j<split.length; j++) {
                if(split[j].equals("(")){
                    deque.push(split[j]);
                }else if(split[j].equals(")")){
                    if(deque.isEmpty()){
                        deque.push(split[j]);
                        break;
                    }else {
                        deque.pop();
                    }
                }
            }
            if(deque.isEmpty()){
                sb.append("YES").append("\n");
            }else{
                sb.append("NO").append("\n");
            }
            deque.clear();
        }

        System.out.println(sb);
    }
}
