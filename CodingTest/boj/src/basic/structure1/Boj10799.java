package basic.structure1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Boj10799 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        Stack<Character> stack = new Stack<Character>();
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == ')') {
                stack.pop();
                if(s.charAt(i-1) == '('){
                    count += stack.size();
                }else{
                    count++;
                }
                continue;
            }
            stack.push(s.charAt(i));
        }

        System.out.println(count);
    }
}
