package priority;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class BaekJoon1927 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            int a = Integer.parseInt(br.readLine());

            if(a == 0){
                if(pq.isEmpty()){
                    sb.append(a).append("\n");
                }else{
                    sb.append(pq.poll()).append("\n");
                }
            }else{
                pq.add(a);
            }
        }

        System.out.println(sb);
    }
}
