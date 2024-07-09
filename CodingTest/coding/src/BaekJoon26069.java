import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BaekJoon26069 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        HashSet<String> set = new HashSet<String>();

        for(int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken();
            String b = st.nextToken();

            if(a.equals("ChongChong") || b.equals("ChongChong")) {
                set.add(a);
                set.add(b);
            }
            if(set.contains(a) || set.contains(b)) {
                set.add(a);
                set.add(b);
            }
        }
        System.out.println(set.size());
    }
}
