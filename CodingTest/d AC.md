```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<t; i++){
            String p = br.readLine();
            int n = Integer.parseInt(br.readLine());
            String arr = br.readLine();
            arr = arr.substring(1, arr.length()-1);
            String[] split = arr.split(",");
            Deque<String> deque = new ArrayDeque<>();
            if(n > 0) {
                for (int k = 0; k < split.length; k++) {
                    deque.add(split[k]);
                }
            }

            boolean left = true;
            boolean error = false;
            for(int k=0; k<p.length(); k++){
                if(p.charAt(k) == 'R'){
                    left = !left;
                }
                if(p.charAt(k) == 'D'){
                    if(deque.isEmpty()) {
                        error = true;
                        break;
                    }else{
                        if(left){
                            deque.poll();
                        }else{
                            deque.pollLast();
                        }
                    }
                }
            }
            if(error){
                sb.append("error").append('\n');
                continue;
            }
            StringBuilder dap = new StringBuilder();
            dap.append("[");
            while(!deque.isEmpty()){
                if(left) {
                    dap.append(deque.poll());
                }else{
                    dap.append(deque.pollLast());
                }
                if(!deque.isEmpty()){
                    dap.append(",");
                }
            }
            dap.append("]");
            sb.append(dap).append("\n");

        }
        System.out.println(sb.toString());
    }
}
```
