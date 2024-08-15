package graph;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BaekJoon16928 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] arr = new int[101];
        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
        for(int i = 0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map.put(start,end);
            arr[start] = -1;
        }

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            map.put(start,end);
            arr[start] = -1;
        }

        Deque<int[]> dq = new ArrayDeque<>();
        dq.add(new int[]{1,0});

        int[] dx = {1,2,3,4,5,6};
        while(!dq.isEmpty()){
            int[] poll = dq.poll();
            for(int i=0; i<6; i++){
                int x = poll[0] + dx[i];
                if(x <= 100){
                    while(arr[x] == -1){
                        x = map.get(x);
                    }
                    if(arr[x] == 0){
                        dq.add(new int[]{x,poll[1] + 1});
                        arr[x] = arr[poll[1]] + 1;
                        if(x == 100){
                            System.out.println(poll[1] + 1);
                            return;
                        }
                    }
                }
            }
        }

    }
}
