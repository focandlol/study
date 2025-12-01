```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());
        long[] arr = new long[(int) (n+2)];
        for(int i=1; i<=n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        Stack<Long> stack = new Stack<>();
        stack.push(0L);
        long max = 0;
        for(int i=1; i<=n+1; i++){

            while(!stack.isEmpty()){
                long top = stack.peek();
                if(arr[(int) top] <= arr[i]){
                    break;
                }else{
                    stack.pop();
                    max = Math.max(max, (i-stack.peek()-1) * arr[(int) top]);
                }
            }
            stack.push((long) i);
        }
        System.out.println(max);
    }
}

```
