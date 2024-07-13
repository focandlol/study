import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon13241 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        long a = Integer.parseInt(st.nextToken());
        long b = Integer.parseInt(st.nextToken());

        long i = find(Math.max(a, b), Math.min(a, b));
        System.out.println(a*b/i);
    }

    private static long find(long max, long min) {
        if(max % min == 0){
            return min;
        }else{
            return find(min,max%min);
        }

    }
}
