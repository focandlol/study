```

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        for(int i=0; i<n; i++){
            Stack<String> stack = new Stack<>();
            String[] split = br.readLine().split("");
            boolean flag = true;
            for(int j=0; j<split.length; j++){
                if (split[j].equals("(")) {
                    stack.push(split[j]);
                } else if(split[j].equals(")")){
                    if (stack.isEmpty()) {
                        sb.append("NO").append("\n");
                        flag = false;
                        break;
                    }else{
                        stack.pop();
                    }
                }
            }
            if (flag) {
                sb.append(stack.isEmpty() ? "YES" : "NO").append("\n");
            }
        }
        System.out.println(sb);
    }
}

```
