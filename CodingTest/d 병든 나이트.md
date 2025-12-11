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
        int m = Integer.parseInt(st.nextToken());
        int dap = 0;
        if(n < 2 || m < 2){
            dap = 1;
        }else if(n >= 3 && m >= 7){
            dap = m-2;
        }else if(n >= 3){
            dap = m;
            if(dap > 4){
                dap = 4;
            }
        }else if(n == 2){
            if(m % 2 == 0){
                dap = m/2;
            }else{
                dap = m/2 + 1;
            }
            if(dap > 4){
                dap = 4;
            }
        }

        System.out.println(dap);
    }
}


```
