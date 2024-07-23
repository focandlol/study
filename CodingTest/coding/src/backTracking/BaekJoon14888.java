package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BaekJoon14888 {
    static int[] arr;
    static int[] gi;
    static int n;
    static int dap = 0;
    static int min = Integer.MAX_VALUE;
    static int max = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        gi = new int[4];
        for(int i=0; i < 4; i++) {
            gi[i] = Integer.parseInt(st.nextToken());
        }

        dap = arr[0];
        find(dap,0,0);
        System.out.println(max);
        System.out.println(min);

    }

    private static void find(int dap,int y, int depth) {
        if(depth != 0){
            if(y == 0){
                dap += arr[depth];
            }else if(y == 1){
                dap -= arr[depth];
            }else if(y == 2){
                dap *= arr[depth];
            }else{
                dap = dap / arr[depth];
            }
        }
        if(depth == n-1){
            min = Math.min(min,dap);
            max = Math.max(max,dap);
            return;
        }
        for(int i = 0; i<4; i++){
            if(gi[i] == 0){
                continue;
            }
            gi[i]--;
            find(dap,i,depth+1);
            gi[i]++;
            }
        }

    }
