package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon2263 {
    static int[] in;
    static int[] post;
    static int[] pre;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        in = new int[n];
        post = new int[n];
        pre = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            in[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            post[i] = Integer.parseInt(st.nextToken());
        }

        preOrder(0,n-1,0,n-1);

        System.out.println(sb);

    }

    private static void preOrder(int is, int ie, int ps, int pe) {

        if (is <= ie && ps <= pe){
            sb.append(post[pe]).append(" ");
            int pos = 0;
            for(int i=is; i<=ie; i++){
                if(in[i] == post[pe]){
                    pos = i;
                    break;
                }
            }

            preOrder(is,pos-1,ps,pos-is+ps-1);
            preOrder(pos+1,ie,ps+pos-is,pe-1);
        }

    }
}
