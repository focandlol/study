```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] arr;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        arr = new int[n][n];
        for(int i=0; i<n; i++){
            String s = br.readLine();
            for(int j = 0; j<n; j++){
                arr[j][i] = s.charAt(j) - '0';
            }
        }
        find(0,0,n);
        System.out.println(sb);
    }

    private static void find(int x, int y, int n) {


        if(!check(x,y,n)){
            if(n == 1){
                return;
            }
            sb.append("(");
            int a = n/2;
            find(x,y,a);
            find(x+a,y,a);
            find(x,y+a,a);
            find(x+a,y+a,a);
            sb.append(")");
        }

    }

    private static boolean check(int x, int y, int n) {
        for(int i = y; i<y+n; i++) {
            for(int j = x; j<x+n; j++) {
                if(arr[x][y] != arr[j][i]){
                    return false;
                }
            }
        }
        if(arr[x][y] == 1){
            sb.append(1);
        }else{
            sb.append(0);
        }
        return true;
    }
}

```
