package Floyd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon11404 {
    static long[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());
        arr = new long[n+1][n+1];
        for(int i=1; i<=n; i++){
            for(int j=1; j<=n; j++){
                if(i == j){
                    arr[i][j] = 0;
                }else{
                    arr[i][j] = Integer.MAX_VALUE;
                }
            }
        }


        for(int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            if(arr[start][end] > weight) {
                arr[start][end] = weight;
            }
        }

        //모든 노드 의 최단거리 탐색
        for(int k=1; k<=n; k++) {
            for(int start=1; start<=n; start++) {
                for(int end=1; end<=n; end++) {
                    arr[start][end] = Math.min(arr[start][end], arr[start][k] + arr[k][end]);
                }
            }
        }


        //갈 수 없는 노선이면 0출력
        for(int i=1; i<=n; i++) {
            for(int j=1; j<=n; j++) {
                if(arr[i][j] == Integer.MAX_VALUE) {
                    System.out.print("0 ");
                }else{
                    System.out.print(arr[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
