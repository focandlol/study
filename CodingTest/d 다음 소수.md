```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            long a = Long.parseLong(br.readLine());

            find(a);
        }
        System.out.println(sb);
    }

    private static void find(long a) {
        if(a == 0 || a==1 || a==2){
            sb.append(2).append("\n");
            return;
        }
        while(!sosu(a)){
            a++;
        }
        sb.append(a).append("\n");
    }

    private static boolean sosu(long a) {
        for(int i=2; i<=Math.sqrt(a); i++){
            if(a%i==0){
                return false;
            }
        }
        return true;

    }
}
```
