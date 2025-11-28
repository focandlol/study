```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long n = Long.parseLong(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        long[] arr = new long[(int) n];
        long min = 1;
        long max = 0;
        for(int i=0; i<n; i++){
            arr[i] = Long.parseLong(br.readLine());
            if(max < arr[i]){
                max = arr[i];
            }
        }

        max++;
        long mid = 0;
        while(min < max){
            int count = 0;
            mid = (max + min) / 2;

            for(int i=0; i<n; i++){
                count += arr[i] / mid;
            }

            if(count < k){
                max = mid;
            }else{
                min = mid + 1;
            }
        }

        System.out.println(min-1);



    }
}

```
