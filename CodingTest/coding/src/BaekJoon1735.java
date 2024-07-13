import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1735 {
    static int[] fa = new int[2];
    static int[] mo = new int[2];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int i=0; i<2; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            fa[i] = a;
            mo[i] = b;
        }

        long i = find(Math.max(mo[0],mo[1]),Math.min(mo[0],mo[1]));
        long mow = mo[0]*mo[1]/i;
        long up = fa[0]*mow/mo[0] + fa[1]*mow/mo[1];

        long i1 = find(Math.max(mow, up), Math.min(mow, up));
        System.out.print(up/i1 + " ");
        System.out.print(mow/i1);
    }

    private static long find(long max, long min) {
        if(max % min == 0){
            return min;
        }else{
            return find(min, max % min);
        }
    }
}
