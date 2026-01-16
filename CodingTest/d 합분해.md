```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] arr = new int[201][201];
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1;i<=k;i++){
            arr[i][0] = 1;
        }

        for(int i=0; i<=n; i++){
            arr[1][i] = 1;
        }

        for(int i=2;i<=k;i++){
            for(int j=1;j<=n;j++){
                arr[i][j] = (arr[i-1][j] + arr[i][j-1]) % 1000000000;
            }
        }

        System.out.println(arr[k][n]);

    }
}

```
