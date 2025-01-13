package basic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj16926 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());

        int[][] arr = new int[n][m];
        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

//        int size = (2 * n) + (2 * m);
//        r = r % size;

            int x = 0;
            int y = 0;
            while(x < n/2 && y < m/2){
                int s =  r % ((2*(n-2*x) + 2*(m-2*y)) - 4);
                for(int i=0; i<s; i++) {
                    int temp = arr[x][y];
                    for (int j = y + 1; j < m - y; j++) {
                        arr[x][j - 1] = arr[x][j];
                    }

                    for (int j = x + 1; j < n - x; j++) {
                        arr[j - 1][m - y - 1] = arr[j][m - y - 1];
                    }

                    for (int j = m - y - 2; j >= y; j--) {
                        arr[n - x - 1][j + 1] = arr[n - x - 1][j];
                    }

                    for (int j = n - x - 2; j >= x; j--) {
                        arr[j + 1][y] = arr[j][y];
                    }

                    arr[x + 1][y] = temp;

                }
                x++;
                y++;
            }


        StringBuilder sb = new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                sb.append(arr[i][j] + " ");
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}
