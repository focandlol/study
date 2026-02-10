```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int GCD(int num1, int num2) {
        while(true) {
            if(num2 == 0) break;
            int tmp = num1 % num2;
            num1 = num2;
            num2 = tmp;
        }
        return num1;
    }
    static int LCM(int num1, int num2) {
        return num1 * num2 / GCD(num1, num2);
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken()) -1;
            int y = Integer.parseInt(st.nextToken()) -1;

            int count = x;
            int ty = x;
            int dap = -1;
            int lcm = LCM(m, n);
            while(count <= lcm){
                ty = ty % n;
                if(ty == y){
                    dap = count + 1;
                    break;
                }
                count += m;
                ty+=m;
            }
            sb.append(dap);
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
```
