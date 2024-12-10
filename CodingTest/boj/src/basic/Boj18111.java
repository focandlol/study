package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class Boj18111 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][m];

        int max = 0;
        int min = 257;

        HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                map.put(arr[i][j],map.getOrDefault(arr[i][j],0)+1);
                max = Math.max(max, arr[i][j]);
                min = Math.min(min, arr[i][j]);
            }
        }

        int time = Integer.MAX_VALUE;
        int timeIdx = 0;
        for(int i=max; i>=min; i--){
            int inventory = b;
            int need = 0;
            for(int a : map.keySet()){
                if(a < i){
                    need += map.get(a) * (i-a);
                }else if(a > i){
                    inventory += map.get(a) * (a-i);
                }
            }

            int t = 0;
            if(inventory >= need){
                t += (inventory-b) * 2;
                t += need;
                if(time > t){
                    time = t;
                    timeIdx = i;
                }
            }
        }
        System.out.println(time + " " + timeIdx);

    }
}
