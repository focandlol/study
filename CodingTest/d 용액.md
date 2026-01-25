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

    int[] arr = new int[n];
    int min = Integer.MAX_VALUE;

    int[] dap = new int[2];

    st = new StringTokenizer(br.readLine());
    for(int i = 0; i < n; i++) {
      arr[i] = Integer.parseInt(st.nextToken());
    }

    for(int i=0; i < n-1; i++) {
      int start = i+1;
      int end = n-1;

      while(start <= end){
        int mid = (start + end) / 2;

        int sum = arr[i] + arr[mid];
        if(Math.abs(sum) < min){
          min = Math.abs(sum);
          dap[0] = i;
          dap[1] = mid;
        }

        if(sum < 0){
          start = mid + 1;
        }else if(sum > 0){
          end = mid - 1;
        }else{
          break;
        }
      }
    }

    StringBuilder sb = new StringBuilder();
    sb.append(arr[dap[0]]).append(" ").append(arr[dap[1]]);
    System.out.println(sb);
  }

}
```
