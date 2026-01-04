```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[][] arr = new int[n][3];

        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][] minDp = new int[n][3];
        int[][] maxDp = new int[n][3];

        for(int[] a : minDp){
            Arrays.fill(a,Integer.MAX_VALUE);
        }

        for(int i=0; i<3; i++){
            minDp[0][i] = arr[0][i];
            maxDp[0][i] = arr[0][i];
        }

        for(int i=1; i<n; i++){
            for(int j=0; j<3; j++){
                if(j == 0){
                    minDp[i][j] = Math.min(minDp[i-1][j] + arr[i][j], Math.min(minDp[i-1][j+1] + arr[i][j],minDp[i][j]));
                    maxDp[i][j] = Math.max(maxDp[i-1][j] + arr[i][j], Math.max(maxDp[i-1][j+1] + arr[i][j],maxDp[i][j]));
                }else if(j == 2){
                    minDp[i][j] = Math.min(minDp[i-1][j] + arr[i][j], Math.min(minDp[i-1][j-1] + arr[i][j],minDp[i][j]));
                    maxDp[i][j] = Math.max(maxDp[i-1][j] + arr[i][j], Math.max(maxDp[i-1][j-1] + arr[i][j],maxDp[i][j]));
                }else{
                    minDp[i][j] = Math.min(minDp[i-1][j] + arr[i][j], Math.min(minDp[i-1][j+1] + arr[i][j],minDp[i][j]));
                    maxDp[i][j] = Math.max(maxDp[i-1][j] + arr[i][j], Math.max(maxDp[i-1][j+1] + arr[i][j],maxDp[i][j]));

                    minDp[i][j] = Math.min(minDp[i][j],minDp[i-1][j-1] + arr[i][j]);
                    maxDp[i][j] = Math.max(maxDp[i][j],maxDp[i-1][j-1] + arr[i][j]);
                }
            }
        }
        
        int max = 0;
        int min = Integer.MAX_VALUE;
        for(int i=0; i<3; i++){
            max = Math.max(max, maxDp[n-1][i]);
            min = Math.min(min, minDp[n-1][i]);
        }

        System.out.println(max + " " + min);
    }
}
```
