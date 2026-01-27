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

        long[] arr = new long[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int left = 0;
        int right = n - 1;

        int lDap = 0;
        int rDap = 0;

        long min = Long.MAX_VALUE;

        while(left < right) {
            if(min > Math.abs(arr[left] + arr[right])){
                min = Math.abs(arr[left] + arr[right]);
                lDap = left;
                rDap = right;
            }
            if(arr[left] + arr[right] > 0){
                right--;
            }else if(arr[left] + arr[right] < 0){
                left++;
            }else{
                System.out.println(arr[left] + " " + arr[right]);
                return;
            }
        }
        System.out.println(arr[lDap] + " " + arr[rDap]);
    }
}

```
