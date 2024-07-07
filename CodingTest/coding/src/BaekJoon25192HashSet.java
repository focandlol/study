import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BaekJoon25192HashSet {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashSet<String> set = new HashSet<>();
        int n = Integer.parseInt(br.readLine());
        int count = 0;

        for (int i = 0; i < n; i++) {
            String a = br.readLine();
            if(a.equals("ENTER")){
                count += set.size();
                set.clear();
            }else{
                set.add(a);
            }
        }
        count += set.size();
        System.out.println(count);
    }
}
