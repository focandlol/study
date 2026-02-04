```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        String a = "";
        ArrayDeque<String> deque = new ArrayDeque<>();
        while(!a.equals(".")){
            deque.push("a");
            a = br.readLine();
            if(a.equals(".")){
                break;
            }
            String[] split = a.split("");
            for(int i=0; i<split.length; i++){
                if(split[i].equals("(") || split[i].equals(")") || split[i].equals("[") || split[i].equals("]")) {
                    if (deque.peek().equals("(") && split[i].equals(")")) {
                        deque.pop();
                        continue;
                    }
                    if (deque.peek().equals("[") && split[i].equals("]")) {
                        deque.pop();
                        continue;
                    }
                    deque.push(split[i]);
                }
            }
            if(!deque.peek().equals("a")){
                sb.append("no").append("\n");
            }else{
                sb.append("yes").append("\n");
            }
            deque.clear();
        }
        System.out.println(sb);
    }
}


```
