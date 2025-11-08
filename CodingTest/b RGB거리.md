```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[3][n];
        int[][] dap = new int[3][n];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++) {
                arr[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        dap[0][0] = arr[0][0];
        dap[1][0] = arr[1][0];
        dap[2][0] = arr[2][0];

        for(int i=1; i<n; i++) {
            dap[0][i] = arr[0][i] + Math.min(dap[1][i-1], dap[2][i-1]);
            dap[1][i] = arr[1][i] + Math.min(dap[2][i-1], dap[0][i-1]);
            dap[2][i] = arr[2][i] + Math.min(dap[0][i-1], dap[1][i-1]);
        }

        System.out.println(Math.min(Math.min(dap[0][n-1],dap[1][n-1]),dap[2][n-1]));
    }
}


```
