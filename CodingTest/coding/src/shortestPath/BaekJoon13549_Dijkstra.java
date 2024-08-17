package shortestPath;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BaekJoon13549_Dijkstra {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] arr = new int[200000];
        Arrays.fill(arr,Integer.MAX_VALUE);

        Deque<Integer> pq = new ArrayDeque<>();
        pq.add(n);
        arr[n] = 0;

        while (!pq.isEmpty()) {
            int poll = pq.poll();

            if(poll + 1 <= 100000){
                if(arr[poll + 1] > arr[poll] + 1) {
                    pq.add(poll + 1);
                    arr[poll + 1] = arr[poll] + 1;
                }
            }
            if(poll - 1 >= 0){
                if(arr[poll - 1] > arr[poll] + 1) {
                    pq.add(poll - 1);
                    arr[poll - 1] = arr[poll] + 1;
                }
            }
            if(poll * 2 <= 100000 && poll * 2 >= 0){
                if(arr[poll * 2] > arr[poll]) {
                    pq.add(poll * 2);
                    arr[poll * 2] = arr[poll];
                }
            }
        }
        System.out.println(arr[k]);
    }
}

