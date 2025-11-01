```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static String[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        arr = new String[n][m];

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String[] split = st.nextToken().split("");
            for(int j=0; j<m; j++) {
                arr[i][j] = split[j];
            }
        }

        int x = 0;
        int y = 0;
        int min = 100;
        while(y+8<=n) {
            int count = 0;
            String dot = arr[y][x];
            for (int i = y; i < y + 8; i++) {
                for (int j = x; j < x + 8; j++) {
                    if(!arr[i][j].equals(dot)){
                        count++;
                    }
                    if(dot.equals("W")){
                        dot = "B";
                    }else{
                        dot = "W";
                    }
                }
                if(dot.equals("W")){
                    dot = "B";
                }else{
                    dot = "W";
                }
            }
            
            if(Math.min(count,64-count) < min){
                min = Math.min(count,64-count);
            }
            x++;
            if(x + 8 > m){
                y++;
                x = 0;
            }
        }

        System.out.println(min);

    }

}

```
