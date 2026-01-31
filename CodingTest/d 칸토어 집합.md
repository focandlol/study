```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static StringBuilder sb;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str;
        while(((str = br.readLine()) != null)){
            sb = new StringBuilder();
            int n = Integer.parseInt(str);
            int cnt = (int) Math.pow(3,n);
            find(cnt);
            System.out.println(sb);
        }
    }

    private static void find(int a) {
        if(a == 1){
            sb.append("-");
            return;
        }

        find(a/3);
        for(int i=0; i<a/3; i++){
            sb.append(" ");
        }
        find(a/3);
    }
}

```
