package combination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon1010TopDown {
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int t = Integer.parseInt(st.nextToken());
        StringBuilder sb = new StringBuilder();
        arr = new int[30][30];
        for (int i = 0; i < 30; i++) {
            arr[i][i] = 1;
            arr[i][0] = 1;
        }

        for(int i=0; i<t; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());

            sb.append(aa(b,a)).append("\n");
        }

        System.out.println(sb);
    }

    private static int aa(int b, int a) {
        if(arr[b][a] != 0){
            return arr[b][a];
        }
        return arr[b][a] = aa(b-1,a) + aa(b-1,a-1);
    }
}
