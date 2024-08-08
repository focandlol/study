package priority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.PriorityQueue;

public class BaekJoon11279 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        PriorityQueue<Integer> queue = new PriorityQueue<>((Comparator.comparingInt(x -> -x)));
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++){

            int a = Integer.parseInt(br.readLine());

            if(a == 0){
                if(queue.isEmpty()){
                    sb.append(a).append("\n");
                }else{
                    sb.append(queue.poll()).append("\n");
                }
            }else{
                queue.add(a);
            }
        }

        System.out.println(sb);
    }
}
