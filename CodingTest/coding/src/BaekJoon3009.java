import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon3009 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int[] x = new int[1001];
        int[] y = new int[1001];

        for(int i=0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            x[a]++;
            y[b]++;
        }

        int xgap = 0;
        int ygap = 0;

        for(int i=1; i<x.length; i++){
            if(x[i] == 1){
                xgap = i;
            }
            if(y[i] == 1){
                ygap = i;
            }
        }

        sb.append(xgap).append(" ").append(ygap);
        System.out.println(sb);
    }
}
