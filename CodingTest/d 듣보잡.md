```
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> map = new HashMap<>();

        for(int i=0; i<n; i++) {
            String s = br.readLine();
            map.put(s,1);
        }

        for(int i=0; i<m; i++){
            String s = br.readLine();
            map.put(s,map.getOrDefault(s,0)+1);
        }

        List<String> dap = map.entrySet().stream()
                .sorted((a,b) -> a.getKey().compareTo(b.getKey()))
                .filter(a -> a.getValue() >= 2)
                .map(a -> a.getKey())
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        int count = 0;
        for(String s : dap){
            sb.append(s).append("\n");
            count++;
        }

        sb.insert(0,count + "\n");
        System.out.println(sb);
    }
}

```
