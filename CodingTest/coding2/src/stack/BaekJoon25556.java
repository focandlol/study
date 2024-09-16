package stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class BaekJoon25556 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        Stack<Integer>[] stack = new Stack[4];
        for(int i = 0; i < 4; i++) {
            stack[i] = new Stack<>();
        }

        int num = 0;
        while(st.hasMoreTokens()){
            num = Integer.parseInt(st.nextToken());
            for(int i = 0; i < 4; i++) {
                if(stack[i].isEmpty() || stack[i].peek() < num){
                    stack[i].push(num);
                    break;
                }else if(i == 3 && stack[i].peek() > num){
                    System.out.println("NO");
                    return;
                }
            }
        }
        System.out.println("YES");
    }
}
