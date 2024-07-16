import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BaekJoon4948 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        boolean[] arr = new boolean[2*123456 + 1];
        StringBuilder sb = new StringBuilder();

        for(int i=2; i<=Math.sqrt(2*123456); i++){
            if(!arr[i]){
                for(int j = 2*i; j<=2*123456; j+=i){
                    arr[j] = true;
                }
            }
        }

        while(true){
            int count = 0;
            int a = Integer.parseInt(br.readLine());
            if(a == 0){
                break;
            }
            for(int i = a+1; i<=2*a; i++){
                if(!arr[i]){
                    count++;
                }
            }
            sb.append(count).append("\n");
        }

        System.out.println(sb);



    }
}
