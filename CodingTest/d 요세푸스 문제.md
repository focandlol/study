```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        LinkedList<Integer> linkedList = new LinkedList<>();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        for(int i=1; i<=n; i++){
            linkedList.add(i);
        }

        StringBuilder sb = new StringBuilder();
        sb.append("<");
        int count = 1;
        while(!linkedList.isEmpty()){
            if(count % k == 0){
                sb.append(linkedList.remove());
                if(!linkedList.isEmpty()){
                    sb.append(", ");
                }
            }else{
                linkedList.add(linkedList.remove());
            }
            count++;
        }
        sb.append(">");

        System.out.println(sb);
    }
}
```
