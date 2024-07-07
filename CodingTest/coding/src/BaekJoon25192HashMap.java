import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class BaekJoon25192HashMap {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String,Integer> map = new HashMap<>();
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        for(int i=0; i<n; i++){
            String a = br.readLine();
            if(a.equals("ENTER")){
                map.clear();
            }else{
                if(!map.containsKey(a)){
                    map.put(a,1);
                    count++;
                }
            }
        }

        System.out.println(count);
    }
}
