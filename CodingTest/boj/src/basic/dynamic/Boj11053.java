package basic.dynamic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj11053 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int[] arr = new int[n];
        int[] len = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        len[0] = 1;

        for(int i=1; i<n; i++) {
            len[i] = 1;
            for(int j = 0; j<i; j++){
                if(arr[i] > arr[j]) {
                    len[i] = Math.max(len[i], len[j]+1);
                }
            }
        }

        int max = len[0];
        int maxIndex = 0;
        for(int i=1; i<n; i++) {
            if(len[i] > max) {
                max = len[i];
                maxIndex = i;
            }
        }
        System.out.println(max);

        int[] aa = new int[arr[maxIndex] + 1];
        StringBuilder sb = new StringBuilder();
        Stack<Integer> stack = new Stack<>();
        for(int i=maxIndex; i>=0; i--){
            if(len[i] == max){
                stack.push(arr[i]);
                max--;
            }
        }

        while(!stack.isEmpty()){
            sb.append(stack.pop()).append(" ");
        }
        System.out.println(sb);
    }
}
